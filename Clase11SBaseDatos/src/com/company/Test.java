package com.company;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
  private final static Logger logger = Logger.getLogger(Test.class);


    static Empleado e1 = new Empleado(1L, "zoe", 25,"dh","12/03");
    static Empleado e2 = new Empleado(2L, "sarah", 19,"pg","19/03");
    static Empleado e3 = new Empleado(1L, "gigi", 24,"globant","21/03");


    public static void main(String[] args) throws Exception {
        File log4jfile = new File("C:\\Users\\sarah\\OneDrive\\Documentos\\Sarah Digital\\B3\\BACKEND\\PROYECTOS\\Clase11SBaseDatos\\src\\com\\company\\log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());



        Class.forName("org.h2.Driver").newInstance();
        //jdbc:h2:~/test
        Connection con = DriverManager.getConnection("jdbc:h2:" +
                "~/test", "sa", "");

        Statement stmt = con.createStatement();

        //String createTable = "DROP TABLE IF EXISTS TEST; CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255), EDAD INT, EMPRESA VARCHAR(255), FECHA VARCHAR(255));\n)";

        String createTable = "DROP TABLE IF EXISTS TEST;\n" +
                "CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255), EDAD INT, EMPRESA VARCHAR(255), FECHA_INGRESO VARCHAR(255));\n";

        stmt.execute(createTable);

        String valoresE1 = "INSERT INTO TEST VALUES(" + e1.getId() + ",'"+e1.getNombre()+"','" + e1.getEdad() +"','"+ e1.getEmpresa() +"','"+ e1.getFechaEntrada()+ "')";
        stmt.execute(valoresE1);
        String valoresE2 = "INSERT INTO TEST VALUES(" + e2.getId() + ",'"+e2.getNombre()+"','" + e2.getEdad() +"','"+ e2.getEmpresa() +"','"+ e2.getFechaEntrada()+ "')";
        stmt.execute(valoresE2);
        String valoresE3 = "INSERT INTO TEST VALUES(" + e3.getId() + ",'"+e3.getNombre()+"','" + e3.getEdad() +"','"+ e3.getEmpresa() +"','"+ e3.getFechaEntrada()+ "')";
        stmt.execute(valoresE3);

        String sql = "SELECT * FROM TEST";
        ResultSet rd =  stmt.executeQuery(sql);
        while (rd.next()){
            System.out.println(rd.getLong(1)+" "+rd.getString(2)+" " + rd.getInt(3)+ " "+ rd.getString(4) + " " + rd.getString(5));
        }

    }
}