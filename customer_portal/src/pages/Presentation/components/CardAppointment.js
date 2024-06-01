import React from "react";
import FilledInfoCard from "examples/Cards/InfoCards/FilledInfoCard";

function CardAppointment({ appointment }) {
  if (!appointment) return null;

  const { date, time, healthcareUnit, medicalSpeciality, doctorName } = appointment;

  return (
    <FilledInfoCard
      icon="event"
      title="Appointment Details"
      description={
        <>
          <p><strong>Date:</strong> {new Date(date).toLocaleDateString()}</p>
          <p><strong>Time:</strong> {time}</p>
          <p><strong>Healthcare Unit:</strong> {healthcareUnit}</p>
          <p><strong>Medical Speciality:</strong> {medicalSpeciality}</p>
          <p><strong>Doctor's Name:</strong> {doctorName}</p>
        </>
      }
    />
  );
}

export default CardAppointment;
