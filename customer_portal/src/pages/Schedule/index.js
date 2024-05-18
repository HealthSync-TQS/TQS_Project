/*
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================
*/

// @mui material components
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Divider from "@mui/material/Divider";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

// Material Kit 2 React examples
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Imagens
import bgImage from "assets/images/bgImage.jpg";

// Pacotes de calendário
import { StaticDatePicker } from "@mui/x-date-pickers/StaticDatePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import TextField from "@mui/material/TextField";
import dayjs from "dayjs";
import React, { useState } from "react";

import { useLocation } from "react-router-dom";

function Schedule() {
  const location = useLocation();
  const { utente, nome, hospital, especialidade } = location.state;

  const [selectedDate, setSelectedDate] = useState(dayjs());
  const [showDetails, setShowDetails] = useState(false);

  const handleDateChange = (newDate) => {
    setSelectedDate(newDate);
    setShowDetails(true); // Mostrar a seção quando uma data é selecionada
  };

  return (
    <>
      <DefaultNavbar routes={routes} />
      <MKBox
        minHeight="75vh"
        width="100%"
        sx={{
          backgroundImage: ({ functions: { linearGradient, rgba }, palette: { gradients } }) =>
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
              Encontra o teu horário ideal!
            </MKTypography>
            <MKTypography variant="body1" color="white" opacity={0.8} mt={1} mb={3}>
              Navega entre os vários dias apresentados e agenda a consulta no bloco que mais te
              agrada!
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
              boxShadow: "lg",
              borderRadius: "lg",
            }}
          >
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={12} md={4}>
                <MKTypography variant="h5" fontWeight="bold" sx={{ textAlign: "left", mb: 2 }}>
                  Detalhes do Utente
                </MKTypography>
                <Divider sx={{ mb: 3 }} />
                <Grid container spacing={2} direction="column">
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Nº de Utente:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {utente}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Nome do Utente:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {nome}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Nome do Hospital:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {hospital}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Especialidade:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        {especialidade}
                      </MKTypography>
                    </MKBox>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item xs={1}>
                <Divider orientation="vertical" flexItem />
              </Grid>
              <Grid item xs={12} md={7}>
                <MKTypography variant="h5" fontWeight="bold" sx={{ textAlign: "left", mb: 2 }}>
                  Selecione uma Data
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
                {showDetails && (
                  <MKBox mt={2} sx={{ textAlign: "left" }}>
                    <Divider sx={{ mb: 3 }} />
                    <MKTypography variant="h5" fontWeight="bold" mb={2}>
                      Detalhes da Consulta
                    </MKTypography>
                    <MKTypography variant="body1">
                      Data Selecionada: {selectedDate.format("DD/MM/YYYY")}
                    </MKTypography>
                    {/* Adicionar aqui mais detalhes relevantes */}
                  </MKBox>
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
