import "./header.module.js";
import "./loader.module.js";
import {fetchData, getByQuerySelector, moveTo} from "./utils.js";

const editButton = getByQuerySelector(".edit-button");
const deleteButton = getByQuerySelector(".delete-button");
const usersButton = getByQuerySelector(".users-button");
const shoppingCartButton = getByQuerySelector(".shopping-cart-button");

const buttons = [
    [
        editButton, ""
    ],
    [
        deleteButton, ""
    ],
    [
        usersButton, "/users",
    ],
    [
        shoppingCartButton, ""
    ]
]

async function getUserRole() {
    try {
        const data = await fetchData('http://localhost:8080/api/session');
        if (data.userRole !== "[ADMIN]") {
            deleteButton.remove();
            usersButton.remove();
        }

        buttons.forEach(([button, href]) => {
            button.addEventListener("click", () => {
                moveTo(href);
            })
        })

    } catch (error) {
        console.error('Error occurred:', error);
    }
}

getUserRole();
