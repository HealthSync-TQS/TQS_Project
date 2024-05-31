import { useState } from "react";
import { Container, Grid, Modal, Divider, Slide } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import MKBox from "components/MKBox";
import MKButton from "components/MKButton";
import MKTypography from "components/MKTypography";
import axios from "axios";

function BalconyModal() {
  const [show, setShow] = useState(false);
  const [inputNextCheckInValue, setInputValue] = useState("");

  const toggleModal = () => setShow(!show);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const callNext = () => {
    axios
      .get(`http://localhost:8080/nextCheckInTicket?balcony=${inputNextCheckInValue}`)
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
          <MKButton variant="gradient" color="dark" onClick={toggleModal}>
            Next Check-In
          </MKButton>
        </Grid>
        <Modal open={show} onClose={toggleModal} sx={{ display: "grid", placeItems: "center" }}>
          <Slide direction="down" in={show} timeout={500}>
            <MKBox
              position="relative"
              width="400px"
              display="flex"
              flexDirection="column"
              borderRadius="xl"
              bgColor="white"
              shadow="xl"
              p={3}
            >
              <MKBox display="flex" alignItems="center" justifyContent="space-between">
                <MKTypography variant="h5">Balcony Number</MKTypography>
                <CloseIcon fontSize="medium" sx={{ cursor: "pointer" }} onClick={toggleModal} />
              </MKBox>
              <Divider sx={{ my: 2 }} />
              <input
                type="text"
                value={inputNextCheckInValue}
                onChange={handleInputChange}
                placeholder="Enter Balcony Number"
                style={{
                  alignSelf: "center",
                  width: "80%",
                  padding: "10px",
                  borderRadius: "8px",
                  border: "1px solid #ccc",
                  marginBottom: "16px",
                }}
              />
              <MKBox display="flex" justifyContent="space-between">
                <MKButton variant="gradient" color="dark" onClick={toggleModal}>
                  Close
                </MKButton>
                <MKButton variant="gradient" color="info" onClick={callNext}>
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
