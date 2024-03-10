export function getByQuerySelector(selector) {
    if (!selector || typeof selector !== "string") return null;
    return document.querySelector(selector);
}


export function fetchData(url) {
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}


export function moveTo(url) {
    window.location.href = url;
}


export function showNotification(text, status) {
    const notificationElement = document.querySelector('.notification');
    const notificationText = document.querySelector('.notification__text');

    notificationText.textContent = text;
    notificationElement.classList.add(status ?? "");
    notificationElement.classList.remove('hidden');


    notificationElement.style.animation = 'notification-appear 1s ease-in-out';

    setTimeout(() => {
        notificationElement.classList.add('hidden');
        notificationElement.classList.remove(status);
        notificationText.textContent = '';
        notificationElement.style.animation = '';
    }, 3000);

}