import { getByQuerySelector, moveTo } from "./utils.mjs";
import "./header.module.mjs";
import "./loader.module.mjs";

const registrationButtons = [
    [getByQuerySelector(".registration-button"), "/face/users/registration"],
    [getByQuerySelector(".social-login-button"), "/oauth2/authorization/google"]
];

registrationButtons.forEach(([button, href]) => {
    button.addEventListener("click", () => moveTo(href))
})


