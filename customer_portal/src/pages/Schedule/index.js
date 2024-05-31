import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Divider from "@mui/material/Divider";
import TextField from "@mui/material/TextField";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import routes from "routes";
import footerRoutes from "footer.routes";
import bgImage from "assets/images/bgImage.jpg";
import { StaticDatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useAppointment } from "contexts/AppointmentContext";
import { CardActionArea } from "@mui/material";
import MKButton from "components/MKButton";
import { useNavigate } from "react-router-dom";

function Schedule() {
  const navigate = useNavigate();
  const { appointmentData } = useAppointment();
  const [selectedDate, setSelectedDate] = useState(dayjs());
  const [selectedTime, setSelectedTime] = useState(null);
  const [selectedTimeId, setSelectedTimeId] = useState(null);
  const [showDetails, setShowDetails] = useState(false);
  const [availableTimes, setAvailableTimes] = useState([]);
  const [showButton, setShowButton] = useState(false);

  useEffect(() => {
    setShowButton(selectedTime !== null);
  }, [selectedTime]);

  const handleTimeSelect = (timeId, time) => {
    if (selectedTimeId === timeId) {
      setSelectedTimeId(null);
      setSelectedTime(null);
    } else {
      setSelectedTimeId(timeId);
      setSelectedTime(time);
    }
  };

  const handleDateChange = (newDate) => {
    const dayjsDate = dayjs(newDate);
    setSelectedDate(dayjsDate);
    console.log("Selected date:", dayjsDate.format("YYYY-MM-DD"));
    setShowDetails(true);
    fetchAvailableTimes(
      dayjsDate,
      appointmentData.medicalSpeciality,
      appointmentData.healthcareUnit
    );
  };

  const handleScheduling = () => {
    const { patientId } = appointmentData;
    const appointmentId = selectedTimeId;

    if (!appointmentId) {
      console.error("No appointment ID selected");
      return;
    }

    const url = `http://localhost:8080/appointments/${appointmentId}/setPatient/${patientId}`;
    console.log(`Sending PUT request to URL: ${url}`);

    axios
      .put(url)
      .then((response) => {
        console.log("Appointment Scheduled", response.data);
        navigate("/");
      })
      .catch((error) => {
        console.error("Error scheduling:", error);
      });
  };

  const fetchAvailableTimes = (date, medicalSpeciality, healthcareUnit) => {
    if (!date.isValid()) {
      console.error("Invalid date provided");
      return;
    }
    const formattedDate = date.format("YYYY-MM-DD");
    const params = new URLSearchParams({
      medicalSpeciality,
      healthcareUnit,
    }).toString();

    axios
      .get(
        `http://localhost:8080/appointments/${formattedDate}?${params}`
      )
      .then((response) => {
        setAvailableTimes(response.data);
        console.log("Available times received:", response.data);
      })
      .catch((error) => {
        console.error("Error fetching times:", error);
      });
  };

  return (
    <>
      <DefaultNavbar routes={routes} />
      <MKBox
        minHeight="75vh"
        width="100%"
        sx={{
          backgroundImage: ({
            functions: { linearGradient, rgba },
            palette: { gradients },
          }) =>
            `${linearGradient(
              rgba(gradients.dark.main, 0.6),
              rgba(gradients.dark.state, 0.6)
            )}, url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          display: "grid",
          placeItems: "center",
        }}
      >
        <Container>
          <Grid
            container
            item
            xs={12}
            lg={8}
            justifyContent="center"
            alignItems="center"
            flexDirection="column"
            sx={{ mx: "auto", textAlign: "center" }}
          >
            <MKTypography
              variant="h1"
              color="white"
              sx={({ breakpoints, typography: { size } }) => ({
                [breakpoints.down("md")]: {
                  fontSize: size["3xl"],
                },
              })}
            >
              Find the perfect schedule for you!
            </MKTypography>
            <MKTypography
              variant="body1"
              color="white"
              opacity={0.8}
              mt={1}
              mb={3}
            >
              Navigate between the various days presented and schedule your
              appointment on the block that you like the most!
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>
      <Card
        sx={{
          p: 2,
          mx: { xs: 2, lg: 3 },
          mt: -8,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container maxWidth="md">
          <Card
            sx={{
              p: 3,
              mt: 5,
              boxShadow: "none",
              borderRadius: "lg",
            }}
          >
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={12} md={4}>
                <MKTypography
                  variant="h5"
                  fontWeight="bold"
                  sx={{ textAlign: "left", mb: 2 }}
                >
                  Patient Details
                </MKTypography>
                <Divider sx={{ mb: 3 }} />
                <Grid container spacing={2} direction="column">
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Patient Id:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {appointmentData.patientId}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Patient Name:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {appointmentData.fullName}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Healthcare Unit:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {appointmentData.healthcareUnit}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Speciality:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {appointmentData.medicalSpeciality}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item xs={1}>
                <Divider orientation="vertical" flexItem />
              </Grid>
              <Grid item xs={12} md={7}>
                <MKTypography
                  variant="h5"
                  fontWeight="bold"
                  sx={{ textAlign: "left", mb: 2 }}
                >
                  Select a Date
                </MKTypography>
                <Divider sx={{ mb: 3 }} />
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <StaticDatePicker
                    displayStaticWrapperAs="desktop"
                    orientation="landscape"
                    value={selectedDate}
                    onChange={handleDateChange}
                    renderInput={(params) => <TextField {...params} />}
                  />
                </LocalizationProvider>
                {showDetails && Object.keys(availableTimes).length > 0 && (
                  <>
                    <MKTypography
                      variant="h5"
                      fontWeight="bold"
                      sx={{ textAlign: "left", mt: 4, mb: 2 }}
                    >
                      Choose a slot
                    </MKTypography>
                    <Divider sx={{ mb: 3 }} />
                    <Grid container spacing={2}>
                      {Object.entries(availableTimes).map(([id, time]) => (
                        <Grid item xs={12} sm={6} md={3} key={id}>
                          <CardActionArea
                            onClick={() => handleTimeSelect(id, time)}
                          >
                            <Card
                              raised
                              sx={{
                                p: 2,
                                backgroundColor:
                                  id === selectedTimeId ? "#cfe8fc" : "#fff",
                              }}
                            >
                              <MKTypography variant="h6">{time}</MKTypography>
                            </Card>
                          </CardActionArea>
                        </Grid>
                      ))}
                    </Grid>
                  </>
                )}
                {showButton && (
                  <Grid container mt={2}>
                    <MKButton
                      size="medium"
                      color="primary"
                      onClick={handleScheduling}
                    >
                      Schedule Appointment
                    </MKButton>
                  </Grid>
                )}
              </Grid>
            </Grid>
          </Card>
        </Container>
      </Card>
      <MKBox pt={6} px={1} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}

export default Schedule;
