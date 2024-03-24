import "./header.module.js";
import "./loader.module.js";
import {getByQuerySelector, moveTo} from "./utils.js";
import {getUserSession} from "./user.api.js";

const editButton = getByQuerySelector(".edit-button");
const shoppingCartButton = getByQuerySelector(".shopping-cart-button");

const buttons = [
    [
        editButton, "/face/edit"
    ],
    [
        shoppingCartButton, ""
    ]
]

try {
    const data = await getUserSession();
    if(!data) {
        throw new Error("Something went wrong!")
    }

    buttons.forEach(([button, href]) => {
        button.addEventListener("click", () => {
            const localHref = href === "/face/edit" ? `${href}?id=${data.id}` : href;
            moveTo(localHref);
        })
    })

} catch (error) {
    moveTo("face/");
    console.error('Error occurred:', error);
}