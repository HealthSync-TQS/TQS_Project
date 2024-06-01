import React from "react";
import Icon from "@mui/material/Icon";

// Pages
import Presentation from "pages/Presentation";
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
    name: "Sign In",
    icon: <Icon>locker</Icon>,
    route: "/signIn",
    component: <SignIn />,
  },
  {
    name: "Sign Out",
    icon: <Icon>logout</Icon>,
    route: "/signOut",
  }
];

export default routes;
