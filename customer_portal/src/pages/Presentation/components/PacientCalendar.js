import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css"; // Estilos padrão da biblioteca
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import axios from "axios";
import { useAppointment } from "contexts/AppointmentContext";
import CardAppointment from "pages/Presentation/components/CardAppointment"; // Certifique-se de que este caminho está correto

function PatientCalendar() {
  const [date, setDate] = useState(new Date());
  const [appointments, setAppointments] = useState([]);
  const [selectedAppointment, setSelectedAppointment] = useState(null); // Novo estado para o compromisso selecionado
  const { appointmentData } = useAppointment();

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const patientId = appointmentData.patientId;
        const response = await axios.get(
          `http://localhost:8080/appointments/patient/${patientId}`
        );
        const appointmentDates = response.data.map((appointment) => ({
          date: new Date(appointment.date),
          details: appointment,
        }));
        setAppointments(appointmentDates);
      } catch (error) {
        console.error("Erro ao buscar as consultas:", error);
      }
    };

    if (appointmentData.patientId) {
      fetchAppointments();
    }
  }, [appointmentData]);

  const handleDateChange = (selectedDate) => {
    setDate(selectedDate);
    const appointment = appointments.find(
      (appointment) =>
        appointment.date.getFullYear() === selectedDate.getFullYear() &&
        appointment.date.getMonth() === selectedDate.getMonth() &&
        appointment.date.getDate() === selectedDate.getDate()
    );
    if (appointment) {
      if (
        selectedAppointment &&
        selectedAppointment.id === appointment.details.id
      ) {
        setSelectedAppointment(null); // Desmarque o compromisso se já estiver selecionado
      } else {
        setSelectedAppointment(appointment.details); // Marque o compromisso se não estiver selecionado
      }
    } else {
      setSelectedAppointment(null); // Limpe o compromisso selecionado se não houver nenhum
    }
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
    if (view === "month" && isAppointmentDay(date)) {
      return <div className="appointment-indicator" />;
    }
  };

  return (
    <MKBox
      display="flex"
      flexDirection="column"
      alignItems="center"
      width="100%"
    >
      <MKBox
        className="patient-calendar"
        width="100%"
        sx={{
          "& .react-calendar": {
            width: "100%",
            maxWidth: "100%",
            border: "none",
            boxShadow: "none",
          },
          "& .appointment-indicator": {
            backgroundColor: "#FF5733",
            borderRadius: "50%",
            width: "10px",
            height: "10px",
            margin: "auto",
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
