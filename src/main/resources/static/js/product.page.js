import "./loader.module.js";
import "./header.module.js";
import {showNotification} from "./utils.js";

const form = document.querySelector('.product-form');
const imagePreview = document.getElementById("image-preview");

const inputName = document.querySelector('#name');
const inputDescription = document.querySelector('#description');
const inputImage = document.querySelector('#image');
const inputProductType = document.querySelector('#productType');
const inputPrice = document.querySelector('#price');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const imageFile = inputImage.files[0];
    if (!imageFile.type.startsWith('image/')) {
        showNotification("Please select an image file", "error");
        return;
    }

    if (imageFile.size > 5 * 1024 * 1024) { // 5 MB limit
        showNotification("Image file size too large", "error");
        return;
    }


    const product = {
        name: inputName.value,
        description: inputDescription.value,
        image: imageFile,
        productType: inputProductType.value,
        price: Number(inputPrice.value),
    };

    try {
        const formData = new FormData();

        for (const key in product) {
            formData.append(key, product[key]);
        }

        const response = await submitForm(API_URL, formData);
        if (response.ok) {
            showNotification("Product created successfully!", "success");
        } else {
            throw new Error('Error creating product');
        }
    } catch (error) {
        showNotification(error.message, "error");
    } finally {
        clearForm();
    }
});

async function submitForm(url, data) {
    const config = {
        method: 'POST',
        body: data,
    };

    try {
        document.body.classList.add("load");
        const response = await fetch(url, config);
        document.body.classList.remove("load");
        return response;
    } catch (error) {
        throw new Error('Error submitting form');
    }

}

inputImage.addEventListener("change", function() {
    const file = this.files[0];

    if (file) {
        const reader = new FileReader();

        reader.addEventListener("load", function() {
            imagePreview.src = this.result;
            imagePreview.style.display = "block";
        });

        reader.readAsDataURL(file);
    } else {
        imagePreview.src = "";
        imagePreview.style.display = "none";
    }
});

function clearForm() {
    inputName.value = '';
    inputDescription.value = '';
    inputImage.value = '';
    inputProductType.selectedIndex = 0;
    inputPrice.value = '';
}

const API_URL = 'http://localhost:8080/api/products';

