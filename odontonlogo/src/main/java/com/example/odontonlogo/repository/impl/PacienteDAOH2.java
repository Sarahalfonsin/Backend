package com.example.odontonlogo.repository.impl;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Domicilio;
import com.example.odontonlogo.dominio.Paciente;
import com.example.odontonlogo.service.DomicilioService;
import com.example.odontonlogo.service.PacienteService;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PacienteDAOH2 implements IDao<Paciente> {
    private static final Logger logger = Logger.getLogger(PacienteDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER= "sa";
    private final static String DB_PASSWORD = "";

    private DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes

            Domicilio domicilio = domicilioDAOH2.guardar(paciente.getDomicilio());
            paciente.getDomicilio().setId(domicilio.getId());


            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(apellido,nombre,email,dni,fechaIngreso,domicilio_id,odontologo_id) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());

            preparedStatement.setString(3,paciente.getEmail());
            preparedStatement.setInt(4,paciente.getDni());
            preparedStatement.setString(5,paciente.getFechaIngreso());
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id

            preparedStatement.setLong(6,paciente.getDomicilio().getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                paciente.setId(keys.getLong(1));
            logger.info("Se creó el paciente " + paciente.getNombre() + " " +paciente.getApellido()+ "con id "+ paciente.getId() + " ");
            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }



    @Override
    public void eliminar(Long id) {
//1 conexion
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        Long idDomicilio = null;
        //2
        try {

            PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
            paciente = pacienteService.buscarId(id);
            idDomicilio = paciente.getDomicilio().getId();


            //creo el servicio de domicilio para borrar el domicilio asociado antes de proceder a borrar al paciente.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDAOH2());
            domicilioService.eliminarDomicilio(idDomicilio);

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes WHERE ID = ?");
            logger.debug("------------- BUSCANDO Paciente ------------------");
            //4 SET
            preparedStatement.setLong(1, id);
            //5 ejecuto
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("------------- Pacienye ELIMINADO ------------------");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
        }
    }

    @Override
    public Paciente buscarId(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente1 = null;
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
                String emailP = resultado.getString("email");
                int dni = resultado.getInt("dni");
                String fechaIngreso = resultado.getString("fechaIngreso");

                Long idDomicilio = resultado.getLong("domicilio_id");

                logger.info("Se Busco al  paciente" +nombre + " "+ apellido + " de id " + idPaciente);
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios

                Domicilio domicilioID = domicilioDAOH2.buscarId(idDomicilio);

                paciente1 = new Paciente(apellido,nombre,emailP,dni,fechaIngreso,domicilioID);//
                paciente1.setId(idPaciente);

            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
        return paciente1;
    }


    @Override
    public Paciente buscar(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente1 = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE EMAIL=?");

            //4*Seteamos
            preparedStatement.setString(4,email);

            //4 Ejecutamos sentencias con query y guardamos en result
            ResultSet resultado = preparedStatement.executeQuery();

            //5 EVALUAMOS RESULTADOS
            while (resultado.next()){
                Long idPaciente = resultado.getLong("id");
                String nombre =resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String emailP = resultado.getString("email");
                int dni = resultado.getInt("dni");
                String fechaIngreso = resultado.getString("fechaIngreso");

                Long idDomicilio = resultado.getLong("domicilioID");


                logger.info("Se Busco al  paciente" +nombre + " "+ apellido + " de id " + idPaciente);
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios

                Domicilio domicilioID = domicilioDAOH2.buscarId(idDomicilio);

                paciente1 = new Paciente(apellido,nombre,emailP,dni,fechaIngreso,domicilioID);//


            }

            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
        return paciente1;
    }



    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();
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
                String email= resultado.getString("email");
                int dni = resultado.getInt("dni");
                String fechaIngreso = resultado.getString("fechaIngreso");

                Long idDomicilio = resultado.getLong("domicilioID");


                //6* Si se encuentra un paciente

                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios como en el anterior
                Domicilio domicilio = domicilioDAOH2.buscarId(idDomicilio);

                Paciente paciente = new Paciente(apellido,nombre,email,dni,fechaIngreso,domicilio);//

                //7 Lo guardamos en el arrylist
                pacientes.add(paciente);

                logger.info("Paciente:" + idPaciente + " " + nombre + " "+apellido+ " dni " + dni + " Fue encontrado");
            }
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } return pacientes;

    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }
}
