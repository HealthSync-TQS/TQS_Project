import React, { useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css"; // Estilos padrão da biblioteca
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

function PatientCalendar() {
  const [date, setDate] = useState(new Date());

  const handleDateChange = (selectedDate) => {
    setDate(selectedDate);
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
        }}
      >
        <Calendar onChange={handleDateChange} value={date} />
      </MKBox>
      <MKTypography variant="body1" mt={2}>
        <strong>Data selecionada:</strong> {date.toDateString()}
      </MKTypography>
    </MKBox>
  );
}

export default PatientCalendar;
