import React from "react";
import { Box, Typography } from "@mui/material";
import PropTypes from "prop-types";
import MKButton from "components/MKButton";

const DisplayScreen = ({ nextPerson, onPassLeft, onPassRight }) => {
  return (
    <Box
      sx={{
        width: "30%",
        height: "50%", // Set height to 100%
        position: "fixed", // Position fixed to make it stay on the right side
        top: 85, // Align to the top
        right: 300, // Align to the right
        bottom: 500, // Align to the bottom
        border: "1px solid #ccc",
        borderRadius: "8px",
        padding: "16px",
        backgroundColor: "#f9f9f9",
        overflow: "auto", // Enable scrolling if content overflows
      }}
    >
      <Typography variant="h6">Next Person</Typography>
      <Box sx={{ display: "flex", justifyContent: "space-between", marginTop: "16px" }}>
        <Box
          sx={{
            position: "absolute",
            left: "100px",
            display: "flex",
          }}
        >
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Typography variant="subtitle1">Balcony</Typography>
            <Typography variant="body1">{nextPerson.balconyNumber}</Typography>
            <Typography variant="body1">{nextPerson.balconyNumber}</Typography>
            <Typography variant="body1">{nextPerson.balconyNumber}</Typography>
            <Typography variant="body1">{nextPerson.balconyNumber}</Typography>
            <Typography variant="body1">{nextPerson.balconyNumber}</Typography>
          </div>
        </Box>
        <Box
          sx={{
            position: "absolute",
            right: "125px",
            display: "flex",
          }}
        >
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Typography variant="subtitle1">Consult</Typography>
            <Typography variant="body1">{nextPerson.consultNumber}</Typography>
            <Typography variant="body1">{nextPerson.consultNumber}</Typography>
            <Typography variant="body1">{nextPerson.consultNumber}</Typography>
            <Typography variant="body1">{nextPerson.consultNumber}</Typography>
            <Typography variant="body1">{nextPerson.consultNumber}</Typography>
          </div>
        </Box>
      </Box>
      <Box
        sx={{
          position: "absolute",
          bottom: "16px",
          left: "50px",
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <MKButton variant="contained" color="dark" onClick={onPassLeft}>
          Pass Balcony Number
        </MKButton>
      </Box>
      <Box
        sx={{
          position: "absolute",
          bottom: "16px",
          right: "50px",
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <MKButton variant="contained" color="dark" onClick={onPassRight}>
          Pass Consult Number
        </MKButton>
      </Box>
    </Box>
  );
};

DisplayScreen.propTypes = {
  nextPerson: PropTypes.shape({
    balconyNumber: PropTypes.number.isRequired,
    consultNumber: PropTypes.number.isRequired,
  }).isRequired,
  onPassLeft: PropTypes.func.isRequired,
  onPassRight: PropTypes.func.isRequired,
};

export default DisplayScreen;
