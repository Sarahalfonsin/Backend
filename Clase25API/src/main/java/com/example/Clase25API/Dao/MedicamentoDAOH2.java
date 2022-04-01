package com.example.Clase25API.Dao;

import com.example.Clase25API.model.Medicamento;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class MedicamentoDAOH2 implements IDao<Medicamento> {

    private static final Logger logger = Logger.getLogger(MedicamentoDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_medicamento;";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Medicamento guardar(Medicamento medicamento) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO medicamento(nombre,marca,cantidad,precio) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,medicamento.getId());
            preparedStatement.setString(1, medicamento.getNombre());
            preparedStatement.setString(2,medicamento.getMarca());
            preparedStatement.setInt(3,medicamento.getCantidad());
            preparedStatement.setDouble(4,medicamento.getPrecio());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                medicamento.setId(keys.getInt(1));
            }
            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            System.out.println("Se cargo"+ medicamento.getId());


            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return medicamento;
    }

    @Override
    public Medicamento modificar(Medicamento medicamento) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //2° LEVANTAR DRIVER Y CONECATRNOS
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3*creamos sentencia
            preparedStatement = connection.prepareStatement("UPDATE medicamento SET nombre=?,marca=?,cantidad=?,precio=? WHERE id=?");
            //preparedStatement.setInt(1,medicamento.getId());
            preparedStatement.setString(1, medicamento.getNombre());
            preparedStatement.setString(2,medicamento.getMarca());
            preparedStatement.setInt(3,medicamento.getCantidad());
            preparedStatement.setDouble(4,medicamento.getPrecio());
            preparedStatement.setInt(5,medicamento.getId());

            preparedStatement.executeUpdate();

            System.out.println("Se modificoo  "+ medicamento.getId());
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
        }
        return medicamento;

    }

        @Override
    public void eliminar(int id ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //2
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3 sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM medicamento WHERE ID = ?");
            //4 SET
            preparedStatement.setLong(1, id);
            //5 ejecuto
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
}

    @Override
    public Medicamento buscar(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medicamento medicamento = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //3 Sentecia crear
            preparedStatement = connection.prepareStatement("SELECT * FROM medicamento WHERE ID =?");

            //Seteo
            preparedStatement.setInt(1, id);

            //4 Ejecuto
            ResultSet resultado = preparedStatement.executeQuery();

            //Evaluamos results
            while (resultado.next()) {
                int identificador = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String marca = resultado.getString("marca");
                int cantidad = resultado.getInt("cantidad");
                Double precio = resultado.getDouble("precio");



                //si lo encuentra
                medicamento= new Medicamento(nombre,marca, cantidad, precio);
                medicamento.setId(identificador);
                System.out.println(medicamento);

            }
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        logger.info("se busco");
        return medicamento;
    }

    @Override
    public List<Medicamento> buscarTodos() {
        return null;
    }
}
