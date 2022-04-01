package com.company.daos;

import com.company.entidades.Odontologo;


import org.apache.log4j.Logger;
import org.h2.command.Prepared;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_odontologos;INIT=RUNSCRIPT FROM 'src/com/company/create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        //1
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(numeroMatricula,nombre,apellido) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //4 instancias
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            logger.debug("------------- CARGANDO ODONTOLOGO ------------------");
            //5 ejecutar y obtener
            preparedStatement.executeUpdate();


            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getLong(1));
            logger.info("Se cargo el odontologo " + odontologo.getNombre() + " " +odontologo.getApellido()+ " con id "+ odontologo.getId());

            preparedStatement.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        //1 conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //2
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE ID = ?");
            logger.debug("------------- BUSCANDO ODONTOLOGO ------------------");
            //4 SET
            preparedStatement.setLong(1, id);
            //5 ejecuto
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("------------- ODONTOLOGO ELIMINADO ------------------");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
    }

    @Override
    public Odontologo buscar(Long id) {
        //1 conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        //2
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE ID = ?");

            //4 seteo
            preparedStatement.setLong(1, id);

            //5 ejecuto
            ResultSet resultado = preparedStatement.executeQuery();

            //6 evaluamos
            while (resultado.next()) {
                Long idOdontologo = resultado.getLong("id");
                int numeroMatricula = resultado.getInt("numeroMatricula");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");

                //7 si encuentra
                odontologo = new Odontologo(numeroMatricula, nombre, apellido);
                logger.info("ODONTOLOGO ENCONTRADO");
                logger.info("El Odontologo:" + idOdontologo + " " + nombre + " "+apellido+ " con numero de matricula " + numeroMatricula+ " Fue encontrado");

            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            logger.error(throwables);
            throwables.printStackTrace();

        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        //1 conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        //2
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos ");

            //4 ejecuto
            ResultSet resultado = preparedStatement.executeQuery();
            logger.info("Odontologos encontrados con exito");
            //5 evaluo
            while (resultado.next()) {
                Long idOdontologo = resultado.getLong("id");
                int numeroMatricula = resultado.getInt("numeroMatricula");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");

                //7 si encuentra
                Odontologo odontologo = new Odontologo(numeroMatricula, nombre, apellido);


                //8 guardo en array
                odontologos.add(odontologo);

                logger.info("Odontologo:" + idOdontologo + " " + nombre + " "+apellido+ " numero matricula " + numeroMatricula+ " Fue encontrado");

            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
        return odontologos;
    }
}
