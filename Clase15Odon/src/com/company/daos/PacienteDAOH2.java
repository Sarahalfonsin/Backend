package com.company.daos;

import com.company.Util.Util;
import com.company.entidades.Domicilio;
import com.company.entidades.Pacientes;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IDao<Pacientes>{

    private static final Logger logger = Logger.getLogger(PacienteDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_pacientes;";
    private final static String DB_USER= "sa";
    private final static String DB_PASSWORD = "";

    private DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();

    @Override
    public Pacientes guardar(Pacientes pacientes) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes
            Domicilio domicilio = domicilioDAOH2.guardar(pacientes.getDomicilio());
            pacientes.getDomicilio().setId(domicilio.getId());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nombre,apellido,dni,fechaIngreso,domicilioID) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos

            preparedStatement.setString(1, pacientes.getNombre());
            preparedStatement.setString(2, pacientes.getApellido());
            preparedStatement.setInt(3,pacientes.getDni());
            preparedStatement.setDate(4, Util.utilDateToSqlDate(pacientes.getFechaIngreso()));
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id
            preparedStatement.setLong(5,pacientes.getDomicilio().getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                pacientes.setId(keys.getLong(1));
            logger.info("Se creó el paciente " + pacientes.getNombre() + " " +pacientes.getApellido()+ "con id "+ pacientes.getId() + " ");
            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes WHERE ID = ?");
            preparedStatement.setLong(1,id);
            //4 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("Se Elimino :( el paciente con id " + id);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
    }

    @Override
    public Pacientes buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Pacientes pacientes = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE ID=?");

            //4*Seteamos
            preparedStatement.setLong(1,id);

            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()){
                Long idPaciente = resultado.getLong("id");
                String nombre =resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int dni = resultado.getInt("dni");
                Date fechaIngreso = resultado.getDate("fechaIngreso");

                Long idDomicilio = resultado.getLong("domicilioID");


                logger.info("Se Busco al  paciente" +nombre + " "+ apellido + " de id " + idPaciente);
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios

                Domicilio domicilioid = domicilioDAOH2.buscar(idDomicilio);

                pacientes = new Pacientes(nombre,apellido,dni,fechaIngreso,domicilioid);


            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
        return pacientes;
        }

    @Override
    public List<Pacientes> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Pacientes> pacientes = new ArrayList<>();
        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //3*creamos sentencia
           preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()){
                Long idPaciente = resultado.getLong("id");
                String nombre =resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int dni = resultado.getInt("dni");
                Date fechaIngreso = resultado.getDate("fechaIngreso");

                Long idDomicilio = resultado.getLong("domicilioID");

                //6* Si se encuentra un paciente

                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios como en el anterior
                Domicilio domicilio = domicilioDAOH2.buscar(idDomicilio);
                   Pacientes paciente = new Pacientes(nombre,apellido,dni,fechaIngreso,domicilio);

                   //7 Lo guardamos en el arrylist
                   pacientes.add(paciente);

                logger.info("Paciente:" + idPaciente + " " + nombre + " "+apellido+ " dni " + dni + " Fue encontrado");
            }
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } return pacientes;

        }
}
