import "./header.module.js";
import {updateUser, getUserById} from "./user.api.js";
import {showNotification, setLoader, getByQuerySelector} from "./utils.js";

const edit_form = getByQuerySelector(".edit-form");

const url = window.location.href;
const id = new URL(url).searchParams.get("id");
const inputIds = [
    'email',
    'firstName',
    'lastName',
    'birthDate',
    'gender',
];

try {
    setLoader(true);
    const user = await getUserById(id);
    setLoader(false);
    fillForm(user);
} catch (error) {
    showNotification("Something went wrong!", "error");
}

function getFields() {
    let inputsObject = {};
    inputIds.map((input) => [input, document.getElementById(input)]).forEach(([key, value]) => {
        inputsObject[key] = value;
    });
    return inputsObject;
}

function fillForm(user) {

    const fields = getFields();

    for (const fieldsKey in fields) {
        fields[fieldsKey].value = user[fieldsKey];
    }
}

edit_form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = Object.fromEntries(inputIds.map((input) => {
        const element = getByQuerySelector(`#${input}`);
        return [input, element.value];
    }));

    const formData = new FormData();

    for (const input in data) {
        formData.append(input, data[input]);
    }


    try {
        setLoader(true);

        const response = await updateUser(id, formData);

        if (!response.ok) {
            throw new Error("Data could not be updated!");
        }
        showNotification("Data successfully updated!", "success")
    } catch (error) {
        showNotification(error.message, "error")
    } finally {
        setLoader(false);
    }
})