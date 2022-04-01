package probando;

import probando.daos.EstudianteDAOH2;
import probando.entidades.Estudiante;
import probando.servicios.EstudianteService;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setId(1L);
        estudiante1.setNombre("Sarah");
        estudiante1.setApellido("Alfonsin");

        EstudianteService estudianteService = new EstudianteService();

        estudianteService.setEstudianteServiceIDao(new EstudianteDAOH2());

        estudianteService.guardarEstudiante(estudiante1);

    }
}
