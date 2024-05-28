import React from "react";
import { Box, Typography } from "@mui/material";
import PropTypes from "prop-types";
import BalconyModal from "layouts/sections/attention-catchers/modals/components/BalconyModal";
import ConsultModal from "layouts/sections/attention-catchers/modals/components/ConsultModal";

const DisplayScreen = ({ balconyNumber, consultNumber }) => {
  return (
    <Box
      sx={{
        height: "80%",
        position: "relative",
        border: "1px solid #ccc",
        borderRadius: "8px",
        padding: "16px",
        backgroundColor: "#f9f9f9",
        overflow: "auto", // Enable scrolling if content overflows
        marginLeft: "25px",
        marginTop: "100px",
      }}
    >
      <Typography variant="h6">Next Person</Typography>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          marginTop: "16px",
          marginBottom: "16px",
        }}
      >
        <Box
          sx={{
            left: "100px",
            display: "flex",
          }}
        >
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Typography variant="subtitle1">Balcony</Typography>
            <Typography variant="body1">{balconyNumber}</Typography>
            <Typography variant="body1">{balconyNumber}</Typography>
            <Typography variant="body1">{balconyNumber}</Typography>
            <Typography variant="body1">{balconyNumber}</Typography>
            <Typography variant="body1">{balconyNumber}</Typography>
          </div>
        </Box>
        <Box
          sx={{
            right: "125px",
            display: "flex",
          }}
        >
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Typography variant="subtitle1">Clinic</Typography>
            <Typography variant="body1">{consultNumber}</Typography>
            <Typography variant="body1">{consultNumber}</Typography>
            <Typography variant="body1">{consultNumber}</Typography>
            <Typography variant="body1">{consultNumber}</Typography>
            <Typography variant="body1">{consultNumber}</Typography>
          </div>
        </Box>
      </Box>
      <Box
        sx={{
          bottom: "50px",
          left: "50px",
          display: "flex",
          justifyContent: "space-between",
          marginBottom: "16px",
        }}
      >
        <BalconyModal />
        <ConsultModal />
      </Box>
    </Box>
  );
};

DisplayScreen.propTypes = {
  balconyNumber: PropTypes.string.isRequired,
  consultNumber: PropTypes.string.isRequired,
  onPassLeft: PropTypes.func.isRequired,
  onPassRight: PropTypes.func.isRequired,
};

export default DisplayScreen;
