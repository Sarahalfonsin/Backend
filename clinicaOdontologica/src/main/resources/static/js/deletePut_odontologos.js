function eliminarOdontologo(id) {
    const settings = {
        method: "DELETE"
    };

    fetch(`http://localhost:8080/odontologos/${id}`, settings)
    location.reload();
}

function actualizarOdontologo(id) {
    mostrarFormulario("Odontologo");
    document.getElementById("actualizarOdontologoBtn").addEventListener("click", event => {
        event.preventDefault();


       const nombre= document.getElementById("nombre");
            const apellido= document.getElementById("apellido");
            const matricula= document.getElementById("matricula");

           const formData = {
             id:id,
             nombre: nombre.value,
             apellido: apellido.value,
             numeroMatricula:matricula.value,
           };
        const settings = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch("http://localhost:8080/odontologos/actualizar", settings)
            .then((response) => response.json())
            .then(() => alert("Se actualizó el odontólogo"))
            .catch(() => ("No se pudo actualizar el odontólogo"))
            .finally(() => {
                ocultarFormulario("Odontologo");
                resetearCampos();
                location.reload();
            })
    });

    document.getElementById("resetearOdontologoBtn").addEventListener("click", event => {
        event.preventDefault();
        resetearCampos();
    });

    function resetearCampos() {
        document.getElementById("nombre").value = "";
        document.getElementById("apellido").value = "";
        document.getElementById("matricula").value = "";
    }



}

function mostrarFormulario(formulario) {
    document.getElementById(`actualizar${formulario}`).classList.remove("display-none");
}

function ocultarFormulario(formulario) {
    document.getElementById(`actualizar${formulario}`).classList.add("display-none");
}
function findBy(id) {
              const url = `http://localhost:8080/odontologos/${id}`
              const settings = {
                  method: 'GET'
              }
              fetch(url,settings)
              .then(response => response.json())
              .then(data => {
                  let odontologo = data;

                   document.getQuerySelector("nombre").value= odontologo.nombre;
                        document.getQuerySelector("apellido").value = odontologo.apellido;
                        document.getQuerySelector("matricula").value= odontologo.matricula;


              }).catch(() => ("No se pudo actualizar el odontólogo"))}



