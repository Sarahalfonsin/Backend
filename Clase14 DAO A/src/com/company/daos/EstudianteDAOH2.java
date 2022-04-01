package com.company.daos;

import com.company.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOH2 implements IDao<Estudiante> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL=" jdbc:h2:~/db_estudiantes";
    private final static String DB_USER= "sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        //PASOS PARA GUARDAR EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO ESTUDIANTES VALUES(?,?,?)");
            //4*Seteamos
            preparedStatement.setLong(1,estudiante.getId());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setString(3, estudiante.getApellido());

            //4 Ejecutamos sentencias  solo se guarda
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiante;
    }

    @Override
    public void eliminar(Long id) {
        //PASOS PARA ELIMINAR (MISMOS QUE ANTERIOR CAMBIA EN DROP) EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM ESTUDIANTES WHERE ID = ?");
            //4*Seteamos
            preparedStatement.setLong(1,id);

            //4 Ejecutamos sentencias  solo se guarda
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public Estudiante buscar(Long id) {
        //PASOS PARA GUARDAR EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM ESTUDIANTES WHERE ID=?");

            //4*Seteamos
            preparedStatement.setLong(1,id);

            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()){
                Long idEstudiante = resultado.getLong("id");
                String nombre =resultado.getString("nombre");
                String apellido = resultado.getString("apellido");

                //6* Si se encuentra un estudiante

                estudiante = new Estudiante();
                estudiante.setApellido(apellido);
                estudiante.setNombre(nombre);
                estudiante.setId(idEstudiante);

            }
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiante;
    }


    @Override
    public List<Estudiante> buscarTodos() {
        //PASOS PARA GUARDAR EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Estudiante> estudiantes = new ArrayList<>();

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM ESTUDIANTES");

            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()){
                Long idEstudiante = resultado.getLong("id");
                String nombre =resultado.getString("nombre");
                String apellido = resultado.getString("apellido");

                //6* Si se encuentra un estudiante

                //Por cada registro creamos un estudiante por ser array
                Estudiante estudiante = new Estudiante();
                estudiante.setApellido(apellido);
                estudiante.setNombre(nombre);
                estudiante.setId(idEstudiante);

                //Lo guardamos en el arrylist
                estudiantes.add(estudiante);

            }
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiantes;
    }
}
