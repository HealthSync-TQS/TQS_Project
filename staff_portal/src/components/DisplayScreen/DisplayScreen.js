import React, { useState } from "react";
import SockJsClient from "react-stomp";
import { Box, Typography } from "@mui/material";
import ConsultModal from "../../layouts/sections/attention-catchers/modals/components/ConsultModal";
import BalconyModal from "../../layouts/sections/attention-catchers/modals/components/BalconyModal";

const DisplayScreen = () => {
  const [checkinQueue, setCheckinQueue] = useState([]);
  const [appointmentQueue, setAppointmentQueue] = useState([]);

  const handleQueueData = (msg) => {
    console.log("Message: ", msg);
    if (msg.checkin) {
      setCheckinQueue(msg.checkin);
    }
    if (msg.appointments) {
      setAppointmentQueue(msg.appointments);
    }
  };

  return (
    <Box
      sx={{
        height: "80%",
        position: "relative",
        border: "1px solid #ccc",
        borderRadius: "8px",
        padding: "24px",
        backgroundColor: "#f5f5f5",
        overflow: "auto",
        marginLeft: "25px",
        marginTop: "100px",
        boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
      }}
    >
      <SockJsClient
        url="http://localhost:8080/ws"
        topics={["/queue"]}
        onMessage={handleQueueData}
      />
      <Typography variant="h5" sx={{ textAlign: "center", marginBottom: "20px" }}>
        Queue Status
      </Typography>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          marginBottom: "24px",
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            padding: "16px",
            borderRadius: "8px",
            backgroundColor: "#fff",
            boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
          }}
        >
          <Typography variant="h6">Check-in</Typography>
          <Typography variant="body1">
            {checkinQueue.length} {checkinQueue.length === 1 ? "person" : "people"} in queue
          </Typography>
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            padding: "16px",
            borderRadius: "8px",
            backgroundColor: "#fff",
            boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
          }}
        >
          <Typography variant="h6">Appointments</Typography>
          <Typography variant="body1">
            {appointmentQueue.length} {appointmentQueue.length === 1 ? "person" : "people"} in queue
          </Typography>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <BalconyModal />
        <ConsultModal />
      </Box>
    </Box>
  );
};

export default DisplayScreen;
