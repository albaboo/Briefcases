// Obtén elementos del campo de texto, botón, mensaje y cuenta regresiva
const passwordInput = document.getElementById("passwordInput");
const submitButton = document.getElementById("submitButton");
const messageElement = document.getElementById("message");
const countdownElement = document.querySelector(".countdown");

// Define la clave correcta, la hora de finalización y el tiempo de penalización
const claveCorrecta = "10"; //ES AQUI DONDE CAMBIAR LA CLAVE
const horaFinalizacion = new Date();
horaFinalizacion.setMinutes(horaFinalizacion.getMinutes() + 45); // Tienen 45 minutos para acertar la constraseña, si quieres cambiarlo pon otro numero en lugar de 45
const penalizacionTiempo = 2 * 60 * 1000; // 2 minutos en milisegundos, cambia el 2 si quieres más minutos de penalización

let countdownTimer; // Variable para el temporizador

// Función para actualizar la cuenta regresiva
function updateCountdown() {
    const tiempoActual = new Date().getTime();
    const tiempoRestante = horaFinalizacion - tiempoActual;

    if (tiempoRestante <= 0) {
        clearInterval(countdownTimer);
        messageElement.textContent = "Te has quedado sin tiempo :(";
        countdownElement.innerHTML = "";
    } else {
        const minutos = Math.floor(tiempoRestante / (1000 * 60));
        const segundos = Math.floor((tiempoRestante % (1000 * 60)) / 1000);

        document.getElementById("minutes").innerText = String(minutos).padStart(2, "0");
        document.getElementById("seconds").innerText = String(segundos).padStart(2, "0");
    }
}

// Función para iniciar la cuenta regresiva al cargar la página
function startCountdown() {
    countdownTimer = setInterval(updateCountdown, 1000);
}

// Agrega un evento de clic al botón
submitButton.addEventListener("click", function() {
    const claveIngresada = passwordInput.value;

    if (claveIngresada === claveCorrecta) {
        messageElement.textContent = "Candado abierto ¡Enhorabuena! :)";
        clearInterval(countdownTimer);
    } else {
        messageElement.textContent = "Clave incorrecta (penalización de 2 minutos)";
        
        // Restar 2 minutos al tiempo de finalización
        horaFinalizacion.setTime(horaFinalizacion.getTime() - penalizacionTiempo);

        // Reiniciar la cuenta regresiva
        updateCountdown();
    }
});

// Iniciar la cuenta regresiva al cargar la página
window.onload = startCountdown;
