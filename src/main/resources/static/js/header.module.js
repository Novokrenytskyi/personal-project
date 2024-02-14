const userEmail = document.querySelector(".logout-form__email");
const isNotAuth = userEmail.textContent === "anonymousUser";
const logoutButton = document.querySelector(".logout-form__button");
const profileButton = document.querySelector(".button__profile");
const loginButton = document.querySelector(".button__login");

const isLoginPage = window.location.pathname === "/face/login";
const isProfilePage = window.location.pathname === "/face/profile";

if (isNotAuth) {
    userEmail.innerText = "";
    logoutButton.remove();
    profileButton.remove();
}

if (!isNotAuth || isLoginPage) {
    loginButton.remove();
}

if (isProfilePage) {
    profileButton.remove();
}


