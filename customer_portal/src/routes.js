import React from "react";
import Icon from "@mui/material/Icon";

// Pages
import Presentation from "pages/Presentation";
import Schedule from "pages/Schedule";

const routes = [
  {
    name: "Costumer Portal",
    icon: <Icon>dashboard</Icon>, // Use an appropriate icon for your route
    route: "/",
    component: <Presentation />,
  },
  {
    route: "/schedule",
    component: <Schedule />,
  },
];

export default routes;
