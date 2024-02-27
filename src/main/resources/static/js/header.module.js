const userEmail = document.querySelector(".logout-form__email");
const logoutButton = document.querySelector(".logout-form__button");
const profileButton = document.querySelector(".button__profile");
const loginButton = document.querySelector(".button__login");

const isNotAuth = userEmail.textContent === "anonymousUser";

const isLoginPage = window.location.pathname === "/face/login";
const isProfilePage = window.location.pathname === "/face/profile";

const removeElement = element => {
    if (element) {
        element.remove();
    }
};

if (isNotAuth) {
    removeElement(userEmail);
    removeElement(logoutButton);
    removeElement(profileButton);
} else if (isLoginPage || isProfilePage) {
    removeElement(loginButton);
}

if (isProfilePage) {
    removeElement(profileButton);
}


