// Escuchar el evento DOMContentLoaded
document.addEventListener("DOMContentLoaded", () => {
    // Obtener el formulario
    const form = document.getElementById("formContacto");
    // Validar si el formulario existe
    if (form) {
        // Escuchar el evento submit
        form.addEventListener("submit", function (e) {
            e.preventDefault(); // Para evitar recargar la página
            // Obtener los valores del formulario
            const data = {
                nombre: document.getElementById("nombre").value,
                correo: document.getElementById("correo").value,
                telefono: document.getElementById("telefono").value,
                asunto: document.getElementById("asunto").value,
                mensaje: document.getElementById("mensaje").value
            }
            // Enviar los datos al servidor
            fetch("/api/contacto", {
                // Indicar que se va a enviar datos
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            // Procesar la respuesta
            .then(res => res.text())
            // Mostrar la respuesta
            .then(respuesta => {
                document.getElementById("respuesta").innerText = respuesta;
                form.reset();
            })
            // Manejar errores
            .catch(err => {
                // Mostrar error
                document.getElementById("respuesta").innerText = "Error al enviar el mensaje";
                console.error(err);
            })
        })
    }
})