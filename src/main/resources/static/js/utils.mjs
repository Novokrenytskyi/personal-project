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