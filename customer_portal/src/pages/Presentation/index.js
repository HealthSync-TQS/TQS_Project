/*
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================
*/

import React, { useState } from "react";
import routes from "routes";
import footerRoutes from "footer.routes";
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import MKTypography from "components/MKTypography";
import PatientCalendar from "pages/Presentation/components/PacientCalendar";
import TextField from "@mui/material/TextField";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import MKButton from "components/MKButton";

function Presentation() {
  const [unidadeSaude, setUnidadeSaude] = useState("");
  const [especialidade, setEspecialidade] = useState("");

  const handleUnidadeSaudeChange = (event) => {
    setUnidadeSaude(event.target.value);
  };

  const handleEspecialidadeChange = (event) => {
    setEspecialidade(event.target.value);
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
                  <MKTypography variant="h4">Calendário do Paciente</MKTypography>
                  <MKTypography variant="body1" fontWeight={400} mb={3}>
                    Veja suas próximas consultas e compromissos aqui.
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
                    Marcar Consulta
                  </MKTypography>
                  <MKTypography variant="body1" textAlign="center" fontWeight={400} mb={3}>
                    Agende suas consultas facilmente.
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
                        <TextField fullWidth label="Nº de Utente" variant="outlined" required />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField fullWidth label="Nome Completo" variant="outlined" required />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField
                          fullWidth
                          label="Email"
                          type="email"
                          variant="outlined"
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField
                          fullWidth
                          label="Telemóvel"
                          type="tel"
                          variant="outlined"
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <FormControl fullWidth variant="outlined">
                          <InputLabel>Unidade de Saúde</InputLabel>
                          <Select
                            value={unidadeSaude}
                            onChange={handleUnidadeSaudeChange}
                            label="Unidade de Saúde"
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
                          <InputLabel>Especialidade</InputLabel>
                          <Select
                            value={especialidade}
                            onChange={handleEspecialidadeChange}
                            label="Especialidade"
                          >
                            <MenuItem value="Cardiologia">Cardiologia</MenuItem>
                            <MenuItem value="Dermatologia">Dermatologia</MenuItem>
                            <MenuItem value="Pediatria">Pediatria</MenuItem>
                            <MenuItem value="Ginecologia">Ginecologia</MenuItem>
                          </Select>
                        </FormControl>
                      </Grid>
                      <Grid item xs={12}>
                        <MKButton fullWidth color="info" variant="gradient" sx={{ mt: 3 }}>
                          Procurar Horários
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
