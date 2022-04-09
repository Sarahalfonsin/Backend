window.onload = () => {
  //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
  //los datos que el usuario cargará de la nueva pelicula
  const formulario = document.getElementById("nuevoOdontologo");

  //Ante un submit del formulario se ejecutará la siguiente funcion
   formulario.addEventListener("submit", event => {
   event.preventDefault();
           agregarOdontologo();
       });
       }

function agregarOdontologo() {
     const nombre= document.getElementById("nombre");
     const apellido= document.getElementById("apellido");
     const matricula= document.getElementById("matricula");

    const formData = {
      nombre: nombre.value,
      apellido: apellido.value,
      numeroMatricula:matricula.value,
    };
    //invocamos utilizando la función fetch la API odontologos con el método POST que guardará
    //los odontologos que enviaremos en formato JSON
    const url = "/odontologos";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings)
      .then((response) => response.json())
      .then(() => alert("Se creó el odontólogo"))
      .finally(() => {
                nombre.value = "";
                apellido.value = "";
                matricula.value = "";
            })
            }




