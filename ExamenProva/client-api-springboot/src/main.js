import axios from 'axios';
import {jwtDecode} from "jwt-decode";

// Función para obtener los roles desde el token
function getUserRoles() {
    const token = getToken();
    if (!token) return [];

    try {
        const decoded = jwtDecode(token);
        return decoded.roles || [];  // Asegurar que devuelve un array
    } catch (error) {
        console.error('Error al decodificar el token:', error);
        return [];
    }
}

// Función para verificar si el usuario tiene un rol específico
function hasRole(role) {
    return getUserRoles().includes(role);
}

// Función para obtener el token de autenticación (si existe)
function getToken() {
    return localStorage.getItem('authToken');
}

// Función para comprobar si hay un token de autenticación
function isAuthenticated() {
    return !!getToken();
}

// Función para manejar el logout
function logout() {
    localStorage.removeItem('authToken');
    window.location.hash = '#/bocatas';  // Redirige a la lista de bocatas
}

// Función para manejar la página de bocatas
async function renderBocatas() {
    const contentDiv = document.getElementById('content');
    contentDiv.innerHTML = '<h2>Llista de Bocatas</h2>';

    try {
        const response = await axios.get('http://localhost:8080/api/bocata');
        const bocatas = response.data;

        if (bocatas.length === 0) {
            contentDiv.innerHTML += '<p>No hi ha bocatas disponibles.</p>';
            return;
        }

        // Generar la lista de bocatas sin mostrar la ID
        const bocataList = bocatas.map(bocata => `
            <li>
                <strong>Nom:</strong> ${bocata.name} <br>
                <strong>Preu:</strong> ${bocata.price.toFixed(2)}€ <br>
                <strong>Bread:</strong> ${bocata.breadName}
            </li>
            <hr>
        `).join('');

        contentDiv.innerHTML += `<ul>${bocataList}</ul>`;
    } catch (error) {
        contentDiv.innerHTML += `<p>Error al obtenir els bocatas.</p>`;
    }
}


// Función para manejar la página de login
function renderLogin() {
    const contentDiv = document.getElementById('content');

    contentDiv.innerHTML = `
        <h2>Iniciar sessió</h2>
        <form id="login-form">
            <input type="text" id="username" placeholder="Nom usuari" required />
            <input type="password" id="password" placeholder="Contrasenya" required />
            <button type="submit">Iniciar sessió</button>
        </form>
    `;

    const form = document.getElementById('login-form');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', { username, password });
            localStorage.setItem('authToken', response.data.token);
            alert('Has iniciat sessió correctament');
            window.location.hash = '#/bocatas';  // Redirigir a bocatas después de login
            // renderPage();
        } catch (error) {
            alert('Credencials incorrectas');
        }
    });
}

// Función para manejar la página de breads (requiere autenticación)
async function renderBreads() {
    const contentDiv = document.getElementById('content');

    if (!isAuthenticated()) {
        contentDiv.innerHTML = '<p>Inicia sessió per veure aquest apartat</p>';
        return;
    }

    contentDiv.innerHTML = '<h2>Llista de Breads</h2>';
    try {
        const response = await axios.get('http://localhost:8080/api/bread', {
            headers: { Authorization: `Bearer ${getToken()}` }
        });
        const breads = response.data;
        const breadList = breads.map(bread => `<li>${bread.name} - ${bread.glutenFree ? 'Sense gluten' : 'Amb gluten'}</li>`).join('');
        contentDiv.innerHTML += `<ul>${breadList}</ul>`;
    } catch (error) {
        contentDiv.innerHTML += `<p>Error al obtenir els breads.</p>`;
    }
}

// Función para manejar la creación de bocatas (requiere autenticación)
async function renderCreateBocata() {
    const contentDiv = document.getElementById('content');

    if (!isAuthenticated()) {
        contentDiv.innerHTML = '<p>Inicia sessió per a crear bocatas</p>';
        return;
    }

    contentDiv.innerHTML = '<h2>Crear Bocata</h2>';

    try {
        // Obtener la lista de breads desde la API
        const response = await axios.get('http://localhost:8080/api/bread', {
            headers: { Authorization: `Bearer ${getToken()}` }
        });

        const breads = response.data;

        // Generar opciones de selección de breads
        const breadOptions = breads.map(bread => `<option value="${bread.id}">${bread.name}</option>`).join('');

        contentDiv.innerHTML += `
            <form id="create-bocata-form">
                <input type="text" id="bocata-name" placeholder="Nom del bocata" required />
                <input type="number" id="bocata-price" placeholder="PREU" required />
                <select id="bocata-bread" required>
                    <option value="" disabled selected>Selecciona un bread</option>
                    ${breadOptions}
                </select>
                <button type="submit">Crear</button>
            </form>
        `;

        const form = document.getElementById('create-bocata-form');
        form.addEventListener('submit', async (e) => {
            e.preventDefault();

            const name = document.getElementById('bocata-name').value;
            const price = document.getElementById('bocata-price').value;
            const breadId = document.getElementById('bocata-bread').value;

            try {
                await axios.post('http://localhost:8080/api/bocata',
                    { name, price, breadId },
                    { headers: { Authorization: `Bearer ${getToken()}` } }
                );
                alert('Bocata creat exitosamente');
                window.location.hash = '#/bocatas';
                renderPage();
            } catch (error) {
                alert('Error al crear el bocata');
            }
        });

    } catch (error) {
        contentDiv.innerHTML += '<p>Error al carregar els breads</p>';
    }
}

// Función principal de renderizado, que gestiona las rutas
function renderPage() {
    const path = window.location.hash;

    console.log(path);

    switch (path) {
        case '#/bocatas':
            renderBocatas(); // Ahora accesible sin autenticación
            break;
        case '#/breads':
            if (isAuthenticated()) {
                renderBreads();
            } else {
                document.getElementById('content').innerHTML = '<p>Has d\'iniciar sessió per veure aquesta pàgina.</p>';
            }
            break;
        case '#/create-bocata':
            if (isAuthenticated()) {
                renderCreateBocata();
            } else {
                document.getElementById('content').innerHTML = '<p>Has d\'iniciar sessió per veure aquesta pàgina</p>';
            }
            break;
        case '#/login':
            renderLogin();
            break;
        default:
            renderBocatas();
    }
}


// Rutas para cambiar entre páginas
document.getElementById('nav-bocatas').addEventListener('click', () => { window.location.hash = '#/bocatas'; });
document.getElementById('nav-breads').addEventListener('click', () => { window.location.hash = '#/breads'; });
document.getElementById('nav-create-bocata').addEventListener('click', () => { window.location.hash = '#/create-bocata'; });
document.getElementById('nav-login').addEventListener('click', () => { window.location.hash = '#/login'; });
document.getElementById('logout').addEventListener('click', logout);

function updateNav() {
    const isOwner = hasRole('PROPIETARI');

    document.getElementById('nav-create-bocata').style.display = isOwner ? 'inline' : 'none';
    document.getElementById('nav-breads').style.display = isAuthenticated() ? 'inline' : 'none';
}

window.addEventListener('hashchange', () => {
    updateNav();
    renderPage();
});

// Llamar a updateNav al cargar la página
updateNav();
renderPage();
