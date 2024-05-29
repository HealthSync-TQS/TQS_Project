import { useState } from "react";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Modal from "@mui/material/Modal";
import Divider from "@mui/material/Divider";
import Slide from "@mui/material/Slide";
import CloseIcon from "@mui/icons-material/Close";
import PropTypes from "prop-types";
import MKBox from "components/MKBox";
import MKButton from "components/MKButton";
import MKTypography from "components/MKTypography";
import axios from "axios";

function SimpleModal({ id, onPaymentConfirmed }) {
  const [show, setShow] = useState(false);
  const toggleModal = () => setShow(!show);

  const handlePayment = () => {
    console.log("Id2: ", id);
    axios
      .put(`http://localhost:8080/appointment/setPaymentDone?appointmentId=${id}`)
      .then((response) => {
        console.log("Response: ", response);
        toggleModal();
        onPaymentConfirmed(); // Call the callback to update the payment status
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  return (
    <MKBox component="section" py={6}>
      <Container>
        <Grid container item xs={12} lg={10} justifyContent="center" mx="auto">
          <MKButton id="payButton" variant={"contained"} color={"primary"} onClick={toggleModal}>
            To Be Paid
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
                <MKTypography variant="h5">Payment</MKTypography>
                <CloseIcon fontSize="medium" sx={{ cursor: "pointer" }} onClick={toggleModal} />
              </MKBox>
              <Divider sx={{ my: 0 }} />
              <MKBox p={2}>
                <MKTypography variant="body2" color="secondary" fontWeight="regular">
                  Society has put up so many boundaries, so many limitations on what&apos;s right
                  and wrong that it&apos;s almost impossible to get a pure thought out.
                  <br />
                  <br />
                  It&apos;s like a little kid, a little boy, looking at colors, and no one told him
                  what colors are good, before somebody tells you you shouldn&apos;t like pink
                  because that&apos;s for girls, or you&apos;d instantly become a gay two-year-old.
                </MKTypography>
              </MKBox>
              <Divider sx={{ my: 0 }} />
              <MKBox display="flex" justifyContent="space-between" p={1.5}>
                <MKButton variant="gradient" color="dark" onClick={toggleModal}>
                  Close
                </MKButton>
                <MKButton
                  id="confirmPaymentButton"
                  variant="gradient"
                  color="success"
                  onClick={handlePayment}
                >
                  Confirm Payment
                </MKButton>
              </MKBox>
            </MKBox>
          </Slide>
        </Modal>
      </Container>
    </MKBox>
  );
}

SimpleModal.propTypes = {
  id: PropTypes.number.isRequired,
  onPaymentConfirmed: PropTypes.func.isRequired,
};

export default SimpleModal;
