const API_URL = "http://localhost:8080/face/users";

export async function getAllUsers() {
  const response = await fetch(API_URL);
  const data = await response.json();
  return {
      data,
      ok: response.ok
  };
}


export async function getUserById(id) {
  const response = await fetch(`${API_URL}/${id}`);
  return await response.json();
}


export async function updateUser(id, user) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PATCH',
    body: user,
  });
  const data = await response.json();
  return {
      data,
      ok: response.ok
  };
}


export async function deleteUser(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  });

  return {
      isDelete: response.ok
  }
}

export async function getUserSession(){
    try{
        const response = await fetch("http://localhost:8080/api/session");
        return response.ok ? response.json() : null;
    }
    catch (error) {
        return null;
    }
}