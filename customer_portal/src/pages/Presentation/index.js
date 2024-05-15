import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import PatientCalendar from "pages/Presentation/components/PacientCalendar";

import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import MKButton from "components/MKButton";

import { AppointmentProvider } from "AppointmentContext";

function Presentation() {
  const navigate = useNavigate();
  const { setAppointmentData } = useContext(AppointmentProvider);

  const [formData, setFormData] = useState({
    patientId: "",
    fullName: "",
    email: "",
    phoneNumber: "",
    healthcareUnit: "",
    specialty: ""
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (Object.values(formData).some(x => x === "")) {
      alert("Please fill in all fields");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/appointments", formData);
      setAppointmentData(response.data);
      navigate("/schedule");
    } catch (error) {
      console.error("Error:", error);
      alert("Error sending data.");
    }
  };

  return (
    <>
      <DefaultNavbar routes={routes} sticky />
      <MKBox mt={8}>
        <Container>
          <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
              <MKBox
                display="flex"
                flexDirection="column"
                justifyContent="center"
                pt={10}
                pb={8}
                px={{ xs: 2, lg: 3 }}
                borderRadius="xl"
                shadow="md"
                bgColor="white"
                minHeight="650px"
              >
                <MKBox textAlign="center">
                  <MKTypography variant="h4">
                    Patient Calendar
                  </MKTypography>
                  <MKTypography variant="body1" fontWeight={400} mb={3}>
                    View your upcoming consultations and appointments here.
                  </MKTypography>
                  <PatientCalendar />
                </MKBox>
              </MKBox>
            </Grid>
            <Grid item xs={12} md={6}>
              <MKBox
                display="flex"
                flexDirection="column"
                justifyContent="center"
                pt={10}
                pb={8}
                px={{ xs: 2, lg: 3 }}
                borderRadius="xl"
                shadow="md"
                bgColor="white"
                maxHeight="650px"
              >
                <MKBox>
                  <MKTypography variant="h4" textAlign="center">
                    Schedule Appointment
                  </MKTypography>
                  <MKTypography
                    variant="body1"
                    textAlign="center"
                    fontWeight={400}
                    mb={3}
                  >
                    Easily schedule your appointments.
                  </MKTypography>
                  <MKBox
                    color="white"
                    bgColor="light"
                    variant="contained"
                    borderRadius="lg"
                    shadow="lg"
                    opacity={1}
                    p={2}
                  >
                    <Grid container spacing={2}>
                      <Grid item xs={12}>
                        <TextField
                          value={formData.patientId}
                          onChange={handleChange}
                          name="patientId"
                          label="Patient Number"
                          variant="outlined"
                          fullWidth
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField
                          value={formData.fullName}
                          onChange={handleChange}
                          name="fullName"
                          label="Full Name"
                          variant="outlined"
                          fullWidth
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField
                          value={formData.email}
                          onChange={handleChange}
                          name="email"
                          label="Email"
                          type="email"
                          variant="outlined"
                          fullWidth
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <FormControl fullWidth variant="outlined">
                          <InputLabel>Healthcare Unit</InputLabel>
                          <Select
                            value={formData.healthcareUnit}
                            onChange={handleChange}
                            name="healthcareUnit"
                            label="Healthcare Unit"
                          >
                            <MenuItem value="USF Alfa">USF Alfa</MenuItem>
                            <MenuItem value="USF Beta">USF Beta</MenuItem>
                            <MenuItem value="USF Gama">USF Gama</MenuItem>
                            <MenuItem value="Centro de Saúde Delta">Centro de Saúde Delta</MenuItem>
                          </Select>
                        </FormControl>
                      </Grid>
                      <Grid item xs={12}>
                        <FormControl fullWidth variant="outlined">
                          <InputLabel>Specialty</InputLabel>
                          <Select
                            value={formData.specialty}
                            onChange={handleChange}
                            name="specialty"
                            label="Specialty"
                          >
                            <MenuItem value="Cardiology">Cardiology</MenuItem>
                            <MenuItem value="Dermatology">Dermatology</MenuItem>
                            <MenuItem value="Pediatrics">Pediatrics</MenuItem>
                            <MenuItem value="Gynecology">Gynecology</MenuItem>
                          </Select>
                        </FormControl>
                      </Grid>
                      <Grid item xs={12}>
                        <MKButton
                          fullWidth
                          color="info"
                          variant="gradient"
                          sx={{ mt: 3 }}
                          onClick={handleSubmit}
                        >
                          Search Times
                        </MKButton>
                      </Grid>
                    </Grid>
                  </MKBox>
                </MKBox>
              </MKBox>
            </Grid>
          </Grid>
        </Container>
      </MKBox>
      <MKBox mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}

export default Presentation;
