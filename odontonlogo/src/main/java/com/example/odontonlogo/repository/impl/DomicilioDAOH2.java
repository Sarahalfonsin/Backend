package com.example.odontonlogo.repository.impl;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.List;
@Repository
public class DomicilioDAOH2 implements IDao<Domicilio> {
    private static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        //PASOS PARA GUARDAR EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO domicilios(calle,numero,localidad,provincia) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                domicilio.setId(keys.getLong(1));
            //logger.info("Se creó la dirección con id ");

            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return domicilio;
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
            preparedStatement = connection.prepareStatement("DELETE FROM domicilios WHERE ID = ?");
            logger.debug("------------- BUSCANDO domiclio ------------------");
            //4 SET
            preparedStatement.setLong(1, id);
            //5 ejecuto
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("------------- domicilio ELIMINADO ------------------");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
    }

    @Override
    public Domicilio buscar(String email) {
        return null;
    }

    @Override
    public Domicilio buscarId(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Domicilio domicilio = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //3 Sentecia crear
            preparedStatement = connection.prepareStatement("SELECT * FROM domicilios WHERE ID =?");

            //Seteo
            preparedStatement.setLong(1, id);

            //4 Ejecuto
            ResultSet resultado = preparedStatement.executeQuery();

            //Evaluamos results
            while (resultado.next()) {
                Long idDomicilio = resultado.getLong("id");
                String calle = resultado.getString("calle");
                int numero = resultado.getInt("numero");
                String localidad = resultado.getString("localidad");
                String provincia = resultado.getString("provincia");

                //si lo encuentra
                domicilio = new Domicilio(calle, numero, localidad, provincia);
                domicilio.setId(idDomicilio);

                //logger.info("Se Busco la dire");

            }
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }

    @Override
    public Domicilio modificar(Domicilio domicilio) {
        return null;
    }
}
