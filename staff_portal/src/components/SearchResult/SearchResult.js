import React, { useState, useEffect } from "react";
import { Typography, IconButton, Box } from "@mui/material";
import PropTypes from "prop-types";
import CloseIcon from "@mui/icons-material/Close";
import SimpleModal from "layouts/sections/attention-catchers/modals/components/SimpleModal";
import MKButton from "components/MKButton";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import axios from "axios";

const SearchResult = ({ data }) => {
  const [isVisible, setIsVisible] = useState(true);
  const [checkInConfirmed, setCheckInConfirmed] = useState(data.checkedIn);
  const [paid, setPaid] = useState(data.paid); // Create a new state for payment status

  useEffect(() => {
    // Set initial visibility when data changes
    setIsVisible(true);
    setPaid(data.paid); // Update the paid state when data changes
  }, [data]);

  const handleGetTicket = () => {
    axios
      .post(`http://localhost:8080/newAppointmentTicket?specialty=${data.medicalSpecialty}`)
      .then((response) => {
        console.log("Check-in response: ", response);
        setIsVisible(false);
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  const handleClose = () => {
    setIsVisible(false);
  };

  const handleCheckIn = () => {
    axios
      .put(`http://localhost:8080/appointment/setCheckInDone?appointmentId=${data.id}`)
      .then((response) => {
        console.log("Response: ", response);
        setCheckInConfirmed(true);
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  const handlePaymentConfirmed = () => {
    setPaid(true);
  };

  return (
    <>
      {isVisible && (
        <Box
          style={{
            position: "relative",
            border: "1px solid #e0e0e0",
            borderRadius: "8px",
            padding: "10px",
            marginBottom: "16px",
            backgroundColor: "#ffffff",
            boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
          }}
        >
          <IconButton
            style={{ position: "absolute", top: "8px", right: "8px" }}
            onClick={handleClose}
          >
            <CloseIcon />
          </IconButton>
          <Typography variant="h6">Consult Details</Typography>
          {data.patient ? (
            <>
              <Typography variant="subtitle1">Patient: {data.patient.name}</Typography>
              <Typography variant="subtitle1">NºUtente: {data.patient.numUtente}</Typography>
            </>
          ) : (
            <Typography variant="subtitle1">No patient assigned.</Typography>
          )}
          <Typography variant="body2">Speciality: {data.medicalSpecialty}</Typography>
          <Typography variant="body2">Doctor: {data.doctorName}</Typography>
          <Typography variant="body2">Date: {data.date}</Typography>
          <Typography variant="body2">Local: {data.healthcareUnit}</Typography>
          <Typography variant="body2">Price: {data.price}€</Typography>
          {data.patient ? (
            paid ? (
              checkInConfirmed ? (
                <MKButton
                  variant="outlined"
                  color="success"
                  startIcon={<CheckCircleOutlineIcon />}
                  onClick={handleGetTicket}
                >
                  Get Ticket
                </MKButton>
              ) : (
                <>
                  <MKButton
                    variant="outlined"
                    color="success"
                    startIcon={<CheckCircleOutlineIcon />}
                  >
                    Paid
                  </MKButton>
                  <MKButton variant="contained" color="primary" onClick={handleCheckIn}>
                    Check-in
                  </MKButton>
                </>
              )
            ) : (
              <SimpleModal id={data.id} onPaymentConfirmed={handlePaymentConfirmed} />
            )
          ) : null}
        </Box>
      )}
    </>
  );
};

SearchResult.propTypes = {
  data: PropTypes.shape({
    patient: PropTypes.shape({
      name: PropTypes.string,
      numUtente: PropTypes.number,
    }),
    id: PropTypes.number,
    medicalSpecialty: PropTypes.string,
    doctorName: PropTypes.string,
    healthcareUnit: PropTypes.string,
    time: PropTypes.string,
    date: PropTypes.string,
    price: PropTypes.number,
    paid: PropTypes.bool,
    checkedIn: PropTypes.bool,
  }).isRequired,
};

export default SearchResult;
