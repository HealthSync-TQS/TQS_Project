import { useState } from "react";

// @mui material components
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Modal from "@mui/material/Modal";
import Divider from "@mui/material/Divider";
import Slide from "@mui/material/Slide";

// @mui icons
import CloseIcon from "@mui/icons-material/Close";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKButton from "components/MKButton";
import MKTypography from "components/MKTypography";
import axios from "axios";

function BalconyModal() {
  const [show, setShow] = useState(false);
  const [inputNextAppointmentValue, setInputValue] = useState(""); // Estado para armazenar o valor do input

  const toggleModal = () => setShow(!show);

  // Função para lidar com mudanças no input
  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  // Função que será chamada ao clicar em "save changes"
  const callNext = () => {
    axios
      .get(`http://localhost:8080/nextAppointmentTicket?clinic=${inputNextAppointmentValue}`)
      .then((response) => {
        console.log("Response: ", response);
        toggleModal();
      })
      .catch((error) => {
        console.error("Error: ", error);
        alert("Error: " + error);
      });
  };

  return (
    <MKBox component="section" py={6}>
      <Container>
        <Grid container item xs={12} lg={10} justifyContent="center" mx="auto">
          <MKButton
            id="CallNextAppointmentTicket"
            variant="gradient"
            color="dark"
            onClick={toggleModal}
          >
            NEXT APPOINTMENT
          </MKButton>
        </Grid>
        <Modal open={show} onClose={toggleModal} sx={{ display: "grid", placeItems: "center" }}>
          <Slide direction="down" in={show} timeout={500}>
            <MKBox
              position="relative"
              width="500px"
              display="flex"
              flexDirection="column"
              borderRadius="xl"
              bgColor="white"
              shadow="xl"
            >
              <MKBox display="flex" alignItems="center" justifyContent="space-between" p={2}>
                <MKTypography variant="h5">Clinic</MKTypography>
                <CloseIcon fontSize="medium" sx={{ cursor: "pointer" }} onClick={toggleModal} />
              </MKBox>
              <Divider sx={{ my: 0 }} />
              <input
                id="clinicInput"
                type="text"
                value={inputNextAppointmentValue}
                onChange={handleInputChange}
                placeholder="Enter clinic number"
                style={{
                  alignSelf: "center",
                  width: "50%",
                  padding: "8px",
                  borderRadius: "4px",
                  border: "1px solid #ccc",
                }}
              />
              <Divider sx={{ my: 0 }} />
              <MKBox display="flex" justifyContent="space-between" p={1.5}>
                <MKButton variant="gradient" color="dark" onClick={toggleModal}>
                  close
                </MKButton>
                <MKButton id="nextAppointment" variant="gradient" color="info" onClick={callNext}>
                  Next
                </MKButton>
              </MKBox>
            </MKBox>
          </Slide>
        </Modal>
      </Container>
    </MKBox>
  );
}

export default BalconyModal;
