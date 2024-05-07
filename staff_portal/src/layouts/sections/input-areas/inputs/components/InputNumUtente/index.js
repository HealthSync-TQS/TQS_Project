/* eslint-disable no-param-reassign */
/**
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
import Icon from "@mui/material/Icon";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";

function InputNumUtente() {
  return (
    <MKBox
      component="section"
      display="flex"
      justifyContent="space-between"
      alignItems="center"
      py={12}
    >
      <Container>
        <Grid container spacing={2}>
          <Grid item xs={12} lg={4}>
            <MKInput variant="standard" label="Nº Utente" />
          </Grid>
          <Grid item xs={12} lg={4}>
            <MKInput variant="standard" label="Id Consulta" />
          </Grid>
          <Grid item xs={12} lg={4}>
            <MKButton color="info" size="small">
              <Icon sx={{ mr: 1 }}>search</Icon>
              Search
            </MKButton>
          </Grid>
        </Grid>
      </Container>
    </MKBox>
  );
}

export default InputNumUtente;
