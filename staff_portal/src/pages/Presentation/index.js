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
import { useState } from "react";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Data
const balconyNumber = "A1";
const consultNumber = "C1";

// Data: Array of consults
const consults = [
  {
    id: 1,
    patientUtente: "123456",
    patientName: "John Doe",
    patientBI: "123456789",
    consultAbout: "Headache",
    doctorName: "Dr. Smith",
    date: "2023-10-01, 10:00 AM",
  },
  {
    id: 2,
    patientUtente: "987654",
    patientName: "Jane Doe",
    patientBI: "987654321",
    consultAbout: "Fever",
    doctorName: "Dr. Brown",
    date: "2023-10-02, 11:00 AM",
  },
  // Add more consults as needed
];

function Presentation() {
  const [searchResult, setSearchResult] = useState(consults[0]);
  console.log("Search Results: ", searchResult);
  console.log("Consult: ", consults[0]);

  // Functions
  const handlePassLeft = () => {
    // Handle pass left action
  };

  const handlePassRight = () => {
    // Handle pass right action
  };

  const handleSearch = (numUtente, id) => {
    // Search for the patient and consult in consults array
    console.log("Num Utente: ", numUtente);
    console.log("Id: ", id);

    id = parseInt(id);

    const foundConsult = consults.find(
      (consult) => consult.id === id && consult.patientUtente === numUtente
    );
    console.log("Found Consult: ", foundConsult);
    setSearchResult(foundConsult);
    console.log("Search Results: ", searchResult);
    //flag=true;
  };

  return (
    <>
      <DefaultNavbar routes={routes} sticky />
      <Container>
        <Grid container>
          <Grid item xs={12} lg={4}>
            <InputNumUtente onSearch={handleSearch} />
          </Grid>
          <Grid item xs={12} lg={12}></Grid>
          <Grid item xs={12} lg={6}>
            <SearchResult data={searchResult} />
          </Grid>
          <Grid item xs={12} lg={6}>
            <DisplayScreen
              balconyNumber={balconyNumber}
              consultNumber={consultNumber}
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
