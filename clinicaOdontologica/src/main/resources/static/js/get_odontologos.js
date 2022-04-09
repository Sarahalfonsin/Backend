window.onload = () => {
  fetch("/odontologos/")
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
            <td>${odontologo.id}</td>
            <td>${odontologo.nombre}</td>
            <td>${odontologo.apellido}</td>
            <td>${odontologo.numeroMatricula}</td>
            <td><button > Actualizar</button></td>
            <td><button>Eliminar</button></td>
        </tr>
    `;
    });
}