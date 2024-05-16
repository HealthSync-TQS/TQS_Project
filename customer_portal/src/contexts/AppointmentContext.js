import React, { createContext, useContext, useState } from 'react';

const AppointmentContext = createContext();

export const useAppointment = () => useContext(AppointmentContext);

export const AppointmentProvider = ({ children }) => {
  const [appointmentData, setAppointmentData] = useState({
      patientId: '',
      fullName: '',
      email: '',
      healthcareUnit: '',
      medicalSpeciality: ''
  });

  const value = { appointmentData, setAppointmentData };

  return (
    <AppointmentContext.Provider value={value}>
      {children}
    </AppointmentContext.Provider>
  );
};
