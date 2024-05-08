import React, { useState } from "react";
import { Typography, IconButton, Box } from "@mui/material";
import PropTypes from "prop-types";
import CloseIcon from "@mui/icons-material/Close";
import MKButton from "components/MKButton";

const SearchResult = ({ data }) => {
  const [isVisible, setIsVisible] = useState(true);

  const handleButtonClick = () => {
    // Handle button click action
  };

  const handleClose = () => {
    setIsVisible(false);
  };

  if (!data) {
    return <Typography variant="body1"> No search result found. </Typography>;
  }

  return (
    <>
      {isVisible && ( // Render container only if isVisible state is true
        <Box
          style={{
            position: "relative",
            border: "1px solid #e0e0e0",
            borderRadius: "8px",
            padding: "16px",
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
          <Typography variant="subtitle1">Patient: {data.patientName}</Typography>
          <Typography variant="subtitle2">Patient BI: {data.patientBI}</Typography>
          <Typography variant="body1">Consult About: {data.consultAbout}</Typography>
          <Typography variant="body2">Doctor: {data.doctorName}</Typography>
          <Typography variant="body2">Date: {data.date}</Typography>
          <div
            style={{
              display: "flex",
              justifyContent: "flex-end",
              marginTop: "16px",
            }}
          >
            <MKButton variant="contained" color="primary" onClick={handleButtonClick}>
              Por Pagar
            </MKButton>
          </div>
        </Box>
      )}
    </>
  );
};

SearchResult.propTypes = {
  data: PropTypes.shape({
    patientName: PropTypes.string.isRequired,
    patientBI: PropTypes.string.isRequired,
    consultAbout: PropTypes.string.isRequired,
    doctorName: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
  }).isRequired,
};

export default SearchResult;
