document.addEventListener("DOMContentLoaded", function() {
    const loader = document.querySelector(".loader");
    const content = document.querySelector(".content");

    // Показать loader при загрузке страницы
    document.body.classList.add("load");

    // Скрыть loader и показать контент после полной загрузки страницы
    window.addEventListener("load", function() {
        document.body.classList.remove("load");
        // content.style.display = "block";
    });
});
