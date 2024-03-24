import {setLoader} from "./utils.js";
import {getUserSession} from "./user.api.js"

let user;
try {
    setLoader(true);
    user = await getUserSession();

} catch (error) {
    new Error("Something went wrong!")
} finally {
    setLoader(false);
}

function setLoggedInState() {
    document.querySelector(".logout-form__email").classList.remove('hidden');
    document.querySelector(".logout-form__email").textContent = user.email;
    document.querySelector(".logout-form__button").classList.remove('hidden');
    document.querySelector(".button__profile").classList.remove('hidden');
    document.querySelector(".button__login").classList.add('hidden');
}

function setLoggedOutState() {
    document.querySelector(".logout-form__email").classList.add('hidden');
    document.querySelector(".logout-form__button").classList.add('hidden');
    document.querySelector(".button__profile").classList.add('hidden');
    document.querySelector(".button__login").classList.remove('hidden');
}


function setProfilePageState() {
    document.querySelector(".button__profile").classList.add('hidden');
}


if (!user) {
    setLoggedOutState();
} else {
    setLoggedInState();
}

if (window.location.pathname === "/face/profile") {
    setProfilePageState();
}
