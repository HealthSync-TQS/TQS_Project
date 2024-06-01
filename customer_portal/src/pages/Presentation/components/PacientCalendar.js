import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css"; // Estilos padrão da biblioteca
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import axios from "axios";
import { useAppointment } from "contexts/AppointmentContext";

function PatientCalendar() {
  const [date, setDate] = useState(new Date());
  const [appointments, setAppointments] = useState([]);
  const { appointmentData } = useAppointment();

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const patientId = appointmentData.patientId;
        const response = await axios.get(`http://localhost:8080/appointments/patient/${patientId}`);
        const appointmentDates = response.data.map((appointment) => new Date(appointment.date));
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
  };

  const isAppointmentDay = (date) => {
    return appointments.some(
      (appointment) =>
        appointment.getFullYear() === date.getFullYear() &&
        appointment.getMonth() === date.getMonth() &&
        appointment.getDate() === date.getDate()
    );
  };

  const tileContent = ({ date, view }) => {
    if (view === "month" && isAppointmentDay(date)) {
      return <div className="appointment-indicator" />;
    }
  };

  return (
    <MKBox display="flex" flexDirection="column" alignItems="center" width="100%">
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
    </MKBox>
  );
}

export default PatientCalendar;