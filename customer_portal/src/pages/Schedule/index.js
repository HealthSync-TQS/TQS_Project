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

// Images
import bgImage from "assets/images/bgImage.jpg";

function Schedule() {
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
              <Grid item xs={12} md={5}>
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
                        123456789
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Nome do Utente:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        João Silva
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Nome do Hospital:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        Hospital Central
                      </MKTypography>
                    </MKBox>
                  </Grid>
                  <Grid item>
                    <MKBox textAlign="left">
                      <MKTypography variant="h6" fontWeight="medium">
                        Especialidade:
                      </MKTypography>
                      <MKTypography variant="body1" color="text">
                        Cardiologia
                      </MKTypography>
                    </MKBox>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item>
                <Divider orientation="vertical" flexItem />
              </Grid>
              <Grid item xs={12} md={6}>
                {/* Conteúdo ao lado do divider */}
                <MKTypography variant="h5" fontWeight="bold" sx={{ textAlign: "left", mb: 2 }}>
                  Selecione uma Data
                </MKTypography>
                <Divider sx={{ mb: 3 }} />
                {/* Conteúdo extra */}
                <MKTypography variant="body1" color="text">
                  Adicione aqui informações adicionais sobre o utente ou outras funcionalidades.
                </MKTypography>
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
