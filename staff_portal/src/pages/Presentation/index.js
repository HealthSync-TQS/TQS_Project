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

// imports
import axios from "axios";
import Typography from "@mui/material/Typography";

// Data
const balconyNumber = "A1";
const consultNumber = "C1";
function Presentation() {
  const [searchResult, setSearchResult] = useState(null);
  console.log("Search Results: ", searchResult);
  const handleSearch = (numUtente, id) => {
    // Search for the patient and consult in consults array

    const params = id ? { id } : { numUtente };

    const queryString = new URLSearchParams(params).toString();

    axios
      .get(`http://localhost:8080/appointment?${queryString}`)
      .then((response) => {
        console.log("Response: ", response);
        setSearchResult(response.data);
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  return (
    <>
      <DefaultNavbar routes={routes} sticky />
      <Container>
        <Grid container>
          <Grid item xs={12} lg={6}>
            <InputNumUtente onSearch={handleSearch} />
            {searchResult && searchResult.length > 0 ? (
              searchResult.map((result) => <SearchResult key={result.id} data={result} />)
            ) : (
              <Typography variant="body1">No search results found.</Typography>
            )}
          </Grid>
          <Grid item xs={12} lg={6}>
            <DisplayScreen balconyNumber={balconyNumber} consultNumber={consultNumber} />
          </Grid>
        </Grid>
      </Container>
      <DefaultFooter content={footerRoutes} />
    </>
  );
}

export default Presentation;
