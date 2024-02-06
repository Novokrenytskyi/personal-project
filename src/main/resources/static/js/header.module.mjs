export function headerModule() {
    const userEmail = document.querySelector(".logout-form__email");
    const isNotAuth = userEmail.textContent === "anonymousUser";
    const logoutButton = document.querySelector(".logout-form__button");
    const profileButton = document.querySelector(".button__profile");
    const loginButton = document.querySelector(".button__login");

    if (isNotAuth) {
        userEmail.innerText = "";
        logoutButton.remove();
        profileButton.remove();
    }

    if (!isNotAuth) {
        loginButton.remove();
    }

}

