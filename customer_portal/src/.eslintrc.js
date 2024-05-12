// .eslintrc.js
module.exports = {
    extends: [
      "react-app",
      "react-app/jest",
      "prettier" // Add this line to disable Prettier rules
    ],
    plugins: ["react", "react-hooks", "prettier"],
    rules: {
      "prettier/prettier": "off", // Disable Prettier rule enforcement
      "react/prop-types": "off", // Disable prop-types rule
    }
  };
  