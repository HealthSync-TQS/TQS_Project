import React, { useState } from "react";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import InputNumUtente from "layouts/sections/input-areas/inputs/components/InputNumUtente";
import SearchResult from "components/SearchResult/SearchResult";
import DisplayScreen from "components/DisplayScreen/DisplayScreen";
import routes from "routes";
import footerRoutes from "footer.routes";
import axios from "axios";
import Box from "@mui/material/Box";

function Presentation() {
  const [searchResult, setSearchResult] = useState(null);
  console.log("Search Results: ", searchResult);

  const handleSearch = (numUtente, id) => {
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
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        minHeight: "100vh",
      }}
    >
      <DefaultNavbar routes={routes} sticky />
      <Container sx={{ flex: 1 }}>
        <Grid container spacing={3}>
          <Grid item xs={12} lg={6}>
            <InputNumUtente onSearch={handleSearch} />
            {searchResult && searchResult.length > 0 ? (
              searchResult.map((result) => <SearchResult key={result.id} data={result} />)
            ) : (
              <Typography variant="body1">No search results found.</Typography>
            )}
          </Grid>
          <Grid item xs={12} lg={6}>
            <DisplayScreen />
          </Grid>
        </Grid>
      </Container>
      <DefaultFooter content={footerRoutes} />
    </Box>
  );
}

export default Presentation;
