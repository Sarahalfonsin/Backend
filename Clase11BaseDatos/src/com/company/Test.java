package com.company;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {


    static Empleado sarah = new Empleado(1, "Sarah", 24, "Digital", "2021-10-18");
    static Empleado joaquin = new Empleado(2, "JOAQUIN", 19, "GOOGLE", "2021-11-19");
    static Empleado mateo = new Empleado(3, "Mateo", 24, "FACEBOOK", "2022-02-17");
    private static final Logger logger = Logger.getLogger(Test.class);


    public static void main(String[] args) throws Exception {


        File log4jfile = new File("C:\\Users\\sarah\\OneDrive\\Documentos\\Sarah Digital\\B3\\BACKEND\\PROYECTOS\\Clase11BaseDatos\\src\\com\\company\\log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        Class.forName("org.h2.Driver").newInstance();
        //jdbc:h2:~/test
        Connection con = DriverManager.getConnection("jdbc:h2:" +
                "~/test", "sa", "");

        Statement stmt = con.createStatement();
        try {
            String createTable = "DROP TABLE IF EXISTS TEST; CREATE TABLE TEST(ID INT PRIMARY KEY, NOMBRE VARCHAR(25),EDAD INT, EMPRESA VARCHAR(25), FECHA VARCHAR(255))";
            stmt.execute(createTable);

            //String fila1 = "INSERT INTO TEST VALUES(1,'SARAH',19,'DIGITAL','2021-10-18')";
            // String fila2 = "INSERT INTO TEST VALUES(2,'JOAQUIN',19,'GOOGLE','2021-11-19')";
            // String fila3 = "INSERT INTO TEST VALUES(3,'MATEO',19,'FACEBOOK','2022-02-17')";
            //stmt.execute(fila1);
            //stmt.execute(fila2);
            //stmt.execute(fila3);
            try {
                String valoresSarah = "INSERT INTO TEST VALUES(" + sarah.getId() + ",'" + sarah.getNombre() + "', '" + sarah.getEdad() + "', '" + sarah.getEmpresa() + "','" + sarah.getFecha() + "')";
                stmt.execute(valoresSarah);
                String valoresJoaquin = "INSERT INTO TEST VALUES(" + joaquin.getId() + ", '" + joaquin.getNombre() + "', '" + joaquin.getEdad() + "', '" + joaquin.getEmpresa() + "','" + joaquin.getFecha() + "')";
                stmt.execute(valoresJoaquin);
                String valoresSarulito = "INSERT INTO TEST VALUES(" + mateo.getId() + ", '" + mateo.getNombre() + "', '" + mateo.getEdad() + "', '" + mateo.getEmpresa() + "','" + mateo.getFecha() + "')";
                stmt.execute(valoresSarulito);
                logger.info("se crearon correctamente");
            } catch (Exception id) {
                logger.error("Hay un error en la insercion de un nuevo dato", id);
            }
            ;
            try{
            //Update
            String updateSaruhlito = "UPDATE TEST SET ID = 4 WHERE NOMBRE= 'Sarah'";
            stmt.execute(updateSaruhlito);
            logger.debug("Se actualizo el usuario");}
            catch (Exception e){
                logger.error("Hay un error en la actualizacion");
            }
            try{
            //Eliminar un empleado
            String eliminarEmpleado = "DELETE FROM TEST WHERE NOMBRE ='JOAQUIN'";
            stmt.execute(eliminarEmpleado);
            logger.info("Se elimino el empleado  " + joaquin);}
            catch (Exception e){
                logger.error("Hay un error en la eliminacion");
            }

            //Eliminar otro empleado
            try{
            String eliminarEmpleado2 = "DELETE FROM TEST WHERE EMPRESA ='FACEBOOK'";
            stmt.execute(eliminarEmpleado2);
            logger.info("Se elimino el empleado de la empresa Facebook");}
            catch (Exception e){
                logger.error("Hay un error en la eliminacion2");
            }
        }finally {
            logger.info("Tabla creada");

        }

        String sql = "SELECT * FROM TEST";
        ResultSet rd = stmt.executeQuery(sql);
        while (rd.next()) {
            System.out.println(rd.getInt(1) + " " + rd.getString(2) + " " + rd.getInt(3) + " " + rd.getString(4) + " " + rd.getString(5));
        }
    }
}
