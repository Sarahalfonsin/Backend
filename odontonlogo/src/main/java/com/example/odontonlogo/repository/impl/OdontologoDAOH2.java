package com.example.odontonlogo.repository.impl;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDAOH2 implements IDao<Odontologo> {
    private static  final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL= "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";



    @Override
    public Odontologo guardar(Odontologo odontologo) {
        //PASOS PARA GUARDAR EN BD
        //1° Crea Conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2°Levantar driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3° Crear sentencias
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(numeroMatricula,nombre,apellido) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);

            //4° Seteamos
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            //5° ejecutar y obtener
            preparedStatement.executeUpdate();

            logger.info("Se cargo el odontolog" + odontologo.getNombre());
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                odontologo.setId(keys.getLong(1));
            }
            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE ID = ?");

            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("Odontologo Eliminado");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Odontologo buscarId(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE ID = ?");

            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            //6 evaluar
            while (resultSet.next()){
                Long idOdontologo = resultSet.getLong("id");
                int numeroMatricula = resultSet.getInt("numeroMatricula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");

                //7 si encuentra
                odontologo = new Odontologo(numeroMatricula,nombre,apellido);
                odontologo.setId(idOdontologo);

                logger.info(numeroMatricula+nombre+apellido);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  odontologo;
    }

    @Override
    public Odontologo buscar(String email) {
        return null;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement =connection.prepareStatement("SELECT * FROM odontologos");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idOdontologo = resultSet.getLong("id");
                int numeroMatricula = resultSet.getInt("numeroMatricula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");

                Odontologo odontologo = new Odontologo(numeroMatricula,nombre,apellido);
                odontologo.setId(idOdontologo);

                //8 guardo en array
                odontologos.add(odontologo);
                logger.info("Odontologo:" + idOdontologo + " " + nombre + " "+apellido+ " numero matricula " + numeroMatricula+ " Fue encontrado");

            }
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  odontologos;

    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("UPDATE odontologos SET numeroMatricula=?,nombre=?,apellido=? WHERE id=?");
            //preparedStatement.setInt(1,medicamento.getId());
            preparedStatement.setInt(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.setLong(4,odontologo.getId());


            preparedStatement.executeUpdate();

            System.out.println("Se modificoo  "+ odontologo.getId());
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
        }
        return odontologo;
    }

}
