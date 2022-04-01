import java.sql.*;

public class Test {
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS USUARIO ; CREATE TABLE USUARIO"
            +"("
            +"ID INT PRIMARY KEY,"
            +"NOMBRE varchar(100) NOT NULL,"
            +"EMAIL  varchar(100) NOT NULL,"
            +"SUELDO numeric(15, 2) NOT NULL"
            +")";
    private static final String SQL_INSERT = "INSERT INTO USUARIO (ID, NOMBRE, EMAIL, SUELDO) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE USUARIO SET SUELDO=? WHERE EMAIL=?";


    public static void main(String[] args) throws Exception{
        Usuario sarah = new Usuario("Sarah","Sarahalfonsin@hotmail.com",10d);

        //Se le asigna null para crear la coneccion adentro del try catch
        Connection connection = null ;

        try {
            //Creamos la coneccion
            connection = getConnection();
            Statement statement = connection.createStatement();
            //creamos la tabla
            statement.execute(SQL_TABLE_CREATE);

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);

            //EMPIEZO A INSERTAR EN LA BASE DE DATOS
            psInsert.setInt(1,1);
            psInsert.setString(2, sarah.getNombre());
            psInsert.setString(3, sarah.getEmail());
            psInsert.setDouble(4, sarah.getSueldo());

            psInsert.execute();

            //Empezar la transaccion
            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setDouble(1,sarah.subirSueldo(10d));
            psUpdate.setString(2,sarah.getEmail());
            psUpdate.execute();

            //vamos a simular un error para probar el rollback
            int a = 4/0;

            connection.commit();

            //AUTOCOMIT SOLO SI SALE BIEN
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM USUARIO";
            Statement stmt = connection.createStatement();
            ResultSet rd = stmt.executeQuery(sql);
            while (rd.next()){
                System.out.println(rd.getInt(1)+rd.getString(2)+rd.getString(3)+rd.getDouble(4));
            }

        }catch (Exception e){
            e.printStackTrace();
            //vuelve atras los valores que cambiamos
            connection.rollback();


        }finally {
            //cierro coneccion god practica
            connection.close();

        }

        //vamos a ver si quedo guardado 10 de sueldo en vez de 20 vamos a hacer una nueva consulta(nos deberia volver lo que hay en la BD osea 10 por el rollback
        Connection connection1 = getConnection();
        String sql = "SELECT * FROM USUARIO";
        Statement stmt = connection1.createStatement();
        ResultSet rd = stmt.executeQuery(sql);
        while (rd.next()){
            System.out.println(rd.getInt(1)+rd.getString(2)+rd.getString(3)+rd.getDouble(4));
        }
    }
    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        //jdbc:h2:~/test
        return DriverManager.getConnection("jdbc:h2:" + "./Database/my", "root", "myPassword");

    }
}
