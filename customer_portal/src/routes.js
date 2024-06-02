import React from "react";
import Icon from "@mui/material/Icon";

// Pages
import Presentation from "pages/Presentation";
import SignIn from "pages/LandingPages/SignIn";
import Schedule from "pages/Schedule";

const routes = [
  {
    name: "Costumer Portal",
    icon: <Icon>dashboard</Icon>, // Use an appropriate icon for your route
    route: "/presentation",
    component: <Presentation />,
  },
  {
    name: "Sign In",
    icon: <Icon>locker</Icon>,
    route: "/",
    component: <SignIn />,
  },
  {
    name: "Sign Out",
    icon: <Icon>logout</Icon>,
    route: "/",
    component: <SignIn />,
  },
  {
    route: "/schedule",
    component: <Schedule />,
  }
];

export default routes;
