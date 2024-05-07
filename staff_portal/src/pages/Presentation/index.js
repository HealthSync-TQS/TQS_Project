/*
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-kit-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

// @mui material components
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";

// Material Kit 2 React components
import MKBox from "components/MKBox";

// Material Kit 2 React examples
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import InputNumUtente from "layouts/sections/input-areas/inputs/components/InputNumUtente";
import SearchResult from "components/SearchResult/SearchResult";
import DisplayScreen from "components/DisplayScreen/DisplayScreen";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Data
const nextPerson = {
  balconyNumber: 1,
  consultNumber: 1,
};

// Functions
const handlePassLeft = () => {
  // Handle pass left action
};

const handlePassRight = () => {
  // Handle pass right action
};

function Presentation() {
  return (
    <>
      <DefaultNavbar routes={routes} sticky />
      <Container>
        <Grid container>
          <Grid item xs={12} lg={4}>
            <InputNumUtente />
          </Grid>
          <Grid item xs={12} lg={12}></Grid>
          <Grid item xs={12} lg={6}>
            <SearchResult
              data={{
                patientName: "John Doe",
                patientBI: "123456789",
                consultAbout: "Headache",
                doctorName: "Dr. Smith",
                date: "2023-10-01, 10:00 AM",
              }}
            />
          </Grid>
          <Grid item xs={12} lg={6}>
            {/* DisplayScreen stays here */}
            <DisplayScreen
              nextPerson={nextPerson}
              onPassLeft={handlePassLeft}
              onPassRight={handlePassRight}
            />
          </Grid>
        </Grid>
      </Container>
      <MKBox
        pt={6}
        px={1}
        mt={6}
        sx={{
          position: "fixed",
          bottom: -100,
          left: "50%",
          transform: "translateX(-50%)",
          zIndex: 1000,
        }}
      >
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}

export default Presentation;
