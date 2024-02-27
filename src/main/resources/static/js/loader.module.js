document.addEventListener("DOMContentLoaded", function() {
    const loader = document.querySelector(".loader");
    const content = document.querySelector(".content");

    document.body.classList.add("load");

    window.addEventListener("load", function() {
        document.body.classList.remove("load");
    });
});
