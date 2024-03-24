document.addEventListener("DOMContentLoaded", function() {
    document.body.classList.add("load");

    window.addEventListener("load", function() {
        document.body.classList.remove("load");
    });
});
