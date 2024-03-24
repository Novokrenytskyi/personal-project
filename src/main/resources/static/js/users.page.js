import "./header.module.js";
import {getAllUsers, deleteUser} from "./user.api.js"
import {setLoader, showNotification, getByQuerySelector, moveTo} from "./utils.js"



const fields = [
    "birthDate",
    "email",
    "firstName",
    "gender",
    "lastName",
    "role",
];

const table_body = getByQuerySelector(".users-table__body");
try {
    setLoader(true);
    const response = await getAllUsers();
    if (!response.ok) {
        throw new Error("Something went wrong!");
    }
    const users = generateUsersData(response.data);

    users.forEach((user) => {
        const row = setTableRow(user);
        table_body.appendChild(row);
    });
} catch (error) {
    showNotification(error.message, "error")
} finally {
    setLoader(false);
}

function generateUsersData(users){
    return  users.map((user) => {
        return {
            data: {
                name: `${user.firstName} ${user.lastName}`,
                email: user.email,
                gender: user.gender,
                birthDate: user.birthDate,
                role: user.role,
            },
            meta: {
                id: user.id
            }
        };
    });
}

function setTableRow(user) {
    const row = document.createElement("tr");
    row.setAttribute("data-user-id", user.meta.id);

    const keys = Object.keys(user.data);


    keys.forEach((key) => {
        const td = document.createElement("td");
        td.textContent = user.data[key];
        row.appendChild(td);
    });

    const tdActions = document.createElement("td");

    const btnDelete = document.createElement("button");
    btnDelete.classList.add("btn-delete");
    btnDelete.textContent = "Delete";
    btnDelete.addEventListener("click", async () => {
        await deleteAction(user.meta.id);
    })

    const btnEdit = document.createElement("button");
    btnEdit.classList.add("btn-edit");
    btnEdit.textContent = "Edit";
    btnEdit.addEventListener("click", () => editAction(user.meta.id))

    tdActions.appendChild(btnDelete);
    tdActions.appendChild(btnEdit);
    row.appendChild(tdActions);

    return row;
}


function editAction(id){
    moveTo(`/face/edit?id=${id}`);
}
async function deleteAction(id){

    if(!confirm("Do you really want to delete this user?")) return;

    try {
        setLoader(true);
        const response = await deleteUser(id);
        if(!response.isDelete){
            throw new Error("Failed to delete a user!")
        }

        const userRow = document.querySelector(`tr[data-user-id="${id}"]`);

        if (userRow) {
            table_body.removeChild(userRow);
        }

        showNotification("User successfully deleted!", "success");
    } catch (error) {
        showNotification(error.message, "error");
    } finally {
        setLoader(false);
    }
}

