package com.company.daos;

import com.company.entidades.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {
    private static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_pacientes;";
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
            logger.info("Se creó la dirección con id ");

            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return domicilio;
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
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM domicilios WHERE ID = ?");
            //4*Seteamos
            preparedStatement.setLong(1, id);

            //5 Ejecutamos sentencias  solo se guarda
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Domicilio buscar(Long id) {
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

                logger.info("Se Busco la dire");

            }
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        //PASOS PARA GUARDAR EN BD
        //1° Crear conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Domicilio> domicilios = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM domicilios");

            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()) {

                Long idDomicilio = resultado.getLong("id");
                String calle = resultado.getString("calle");
                int numero = resultado.getInt("numero");
                String localidad = resultado.getString("localidad");
                String provincia = resultado.getString("provincia");

                //si lo encuentra
                Domicilio domicilio = new Domicilio(calle, numero, localidad, provincia);

                //Lo guardamos en el arrylist
                domicilios.add(domicilio);
            }
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return domicilios;
    }
}
