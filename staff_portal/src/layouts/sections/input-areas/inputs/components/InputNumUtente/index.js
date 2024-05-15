import React, { useState } from "react";
import MKBox from "components/MKBox";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";
import PropTypes from "prop-types";

const InputNumUtente = ({ onSearch }) => {
  const [numUtente, setNumUtente] = useState(null);
  const [id, setId] = useState(null);

  const handleSearchClick = () => {
    // Call the onSearch function with numUtente and id as arguments
    onSearch(numUtente, id);
  };

  return (
    <MKBox
      component="section"
      display="flex"
      justifyContent="space-between"
      alignItems="center"
      py={12}
    >
      <div>
        <MKInput
          variant="standard"
          label="Nº Utente"
          value={numUtente}
          onChange={(e) => setNumUtente(e.target.value)}
        />
      </div>
      <div>
        <MKInput
          variant="standard"
          label="Id Consulta"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
      </div>
      <div>
        <MKButton color="info" size="small" onClick={handleSearchClick}>
          Search
        </MKButton>
      </div>
    </MKBox>
  );
};

InputNumUtente.propTypes = {
  onSearch: PropTypes.func,
};

export default InputNumUtente;
