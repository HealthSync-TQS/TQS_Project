import React from "react";
import Icon from "@mui/material/Icon";

// Pages
import Presentation from "pages/Presentation";
import Schedule from "pages/Schedule";
import SignIn from "pages/LandingPages/SignIn";
import SignUp from "pages/LandingPages/SignUp";

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
  {
    route: "/signIn",
    component: <SignIn />,
  },
  {
    route: "/signUp",
    component: <SignUp />,
  },
];

export default routes;
