import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css'; // Estilos padrão da biblioteca
import MKBox from 'components/MKBox';
import MKTypography from 'components/MKTypography';
import axios from 'axios';
import CardAppointment from 'pages/Presentation/components/CardAppointment';

function PatientCalendar() {
  const [date, setDate] = useState(new Date());
  const [appointments, setAppointments] = useState([]);
  const [selectedAppointment, setSelectedAppointment] = useState(null); // Novo estado para o compromisso selecionado
  const [selectedDate, setSelectedDate] = useState(null); // Novo estado para a data selecionada

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const numUtente = localStorage.getItem('numUtente');
        if (numUtente) {
          const response = await axios.get(`http://localhost:8080/appointments/patient/${numUtente}`);
          const appointmentDates = response.data.map((appointment) => ({
            date: new Date(appointment.date),
            details: appointment
          }));
          setAppointments(appointmentDates);
          console.log('Appointments fetched:', appointmentDates);
        } else {
          console.error('No numUtente found in local storage');
        }
      } catch (error) {
        console.error('Erro ao buscar as consultas:', error);
      }
    };

    fetchAppointments();
  }, []);

  useEffect(() => {
    return () => {
      // Clear state when the component unmounts
      setAppointments([]);
      setSelectedAppointment(null);
    };
  }, []);

  const handleDateChange = (selectedDate) => {
    setDate(selectedDate);
    setSelectedDate((prevSelectedDate) => {
      if (prevSelectedDate && prevSelectedDate.getTime() === selectedDate.getTime()) {
        setSelectedAppointment(null); // Desaparecer o card do compromisso se a mesma data for clicada novamente
        return null;
      }
      const appointment = appointments.find((appointment) =>
        appointment.date.getFullYear() === selectedDate.getFullYear() &&
        appointment.date.getMonth() === selectedDate.getMonth() &&
        appointment.date.getDate() === selectedDate.getDate()
      );
      setSelectedAppointment(appointment ? appointment.details : null);
      return selectedDate;
    });
  };

  const isAppointmentDay = (date) => {
    return appointments.some(
      (appointment) =>
        appointment.date.getFullYear() === date.getFullYear() &&
        appointment.date.getMonth() === date.getMonth() &&
        appointment.date.getDate() === date.getDate()
    );
  };

  const tileContent = ({ date, view }) => {
    if (view === 'month' && isAppointmentDay(date)) {
      return <div className="appointment-indicator" />;
    }
  };

  return (
    <MKBox display="flex" flexDirection="column" alignItems="center" width="100%">
      <MKBox
        className="patient-calendar"
        width="100%"
        sx={{
          '& .react-calendar': {
            width: '100%',
            maxWidth: '100%',
            border: 'none',
            boxShadow: 'none',
          },
          '& .appointment-indicator': {
            backgroundColor: '#FF5733',
            borderRadius: '50%',
            width: '10px',
            height: '10px',
            margin: 'auto',
          },
        }}
      >
        <Calendar
          onChange={handleDateChange}
          value={date}
          tileContent={tileContent}
        />
      </MKBox>
      <MKTypography variant="body1" mt={2}>
        <strong>Selected Date:</strong> {date.toDateString()}
      </MKTypography>
      {selectedAppointment && (
        <CardAppointment appointment={selectedAppointment} />
      )}
    </MKBox>
  );
}

export default PatientCalendar;
