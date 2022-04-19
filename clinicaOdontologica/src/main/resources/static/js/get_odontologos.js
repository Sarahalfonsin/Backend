window.onload = () => {
  fetch("http://localhost:8080/odontologos/")
      .then((response) => response.json())
      .then((data) => cargarInformacionOdontologos(data));
};

function cargarInformacionOdontologos(odontologos) {
  const tablaOdontologos = document
    .getElementById("tablaOdontologos")
    .getElementsByTagName("tbody")[0];

    odontologos.forEach((odontologo) => {
      tablaOdontologos.innerHTML += `
        <tr>
            <td class= "id">${odontologo.id}</td>
            <td class= "nombre">${odontologo.nombre}</td>
            <td class= "apellido">${odontologo.apellido}</td>
            <td class= "matricula">${odontologo.numeroMatricula}</td>
            <td><button class="btn btn-primary" onClick="actualizarOdontologo(${odontologo.id})">Actualizar</button></td>
            <td><button class="btn btn-primary" onClick="eliminarOdontologo(${odontologo.id})">Eliminar</button></td>
        </tr>
    `;
    });
}