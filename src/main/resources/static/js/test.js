var myApp = myApp || {};

myApp.showAlert = function() {
    alert('Hello from test.js!');
};

document.addEventListener('DOMContentLoaded', function() {
    console.log('Page is fully loaded. You can perform additional actions here.');

    // Вызываем функцию showAlert после загрузки страницы
    myApp.showAlert();
});