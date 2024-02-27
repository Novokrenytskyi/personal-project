import { getByQuerySelector, moveTo } from "./utils.js";
import "./header.module.js";
import "./loader.module.js";

const registrationButtons = [
    [getByQuerySelector(".registration-button"), "/face/users/registration"],
    [getByQuerySelector(".social-login-button"), "/oauth2/authorization/google"]
];

registrationButtons.forEach(([button, href]) => {
    button.addEventListener("click", () => moveTo(href))
})


