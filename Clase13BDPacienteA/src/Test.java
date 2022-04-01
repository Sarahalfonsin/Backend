import java.sql.*;

public class Test {
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS PACIENTE; CREATE TABLE PACIENTE"
            +"("
            +"ID INT PRIMARY KEY,"
            +"NOMBRE varchar(100) NOT NULL,"
            +"APELLIDO varchar(100) NOT NULL,"
            +"DOMICILIO  varchar(100) NOT NULL,"
            +"FECHA_ALTA varchar(12) NOT NULL,"
            +"USUARIO varchar(100) NOT NULL,"
            +"PASSWORD varchar(100) NOT NULL"
            +")";
    private static final String SQL_INSERT = "INSERT INTO PACIENTE (ID, NOMBRE, APELLIDO, DOMICILIO, FECHA_ALTA, USUARIO, PASSWORD) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE  PACIENTE SET APELLIDO = ? WHERE NOMBRE = ?";

    public static void main(String[] args) throws Exception {
      Paciente joaquin = new Paciente("Joaquin","Morito","Simbron","13-3-2022","therlocks","probando");

      Connection connection = null;

      try {
          connection = getConnection();
          Statement statement = connection.createStatement();

          //Creo base e inserto fila paciente
          statement.execute(SQL_TABLE_CREATE);

          PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
          //EMPIEZO A INSERTAR
          psInsert.setInt(1,1);
          psInsert.setString(2, joaquin.getNombre());
          psInsert.setString(3, joaquin.getApellido());
          psInsert.setString(4, joaquin.getDomicilio());
          psInsert.setString(5, joaquin.getFechaAlta());
          psInsert.setString(6, joaquin.getUsuario());
          psInsert.setString(7, joaquin.getPassword());

          psInsert.execute();

          //AUTOCOMIIT FALSE
          connection.setAutoCommit(false);

          PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
          psUpdate.setString(1,"Aldana");
          psUpdate.setString(2, "Joaquin");
          psUpdate.execute();

          //PRUEBO UN ERROR A VER IS FUNCIONO
          int a  = 14/0;

          connection.commit();

          connection.setAutoCommit(true);

          String sql = "SELECT * FROM PACIENTE";
          Statement stmt = connection.createStatement();
          ResultSet rd = stmt.executeQuery(sql);
          while (rd.next()){
              System.out.println(rd.getLong(1)+rd.getString(2)+rd.getString(3)+rd.getString(4)+rd.getString(5)+rd.getString(6)+rd.getString(7));
          }

      }catch (Exception e){
          e.printStackTrace();
          connection.rollback();

      }finally {
          connection.close();
      }

        Connection connection1 = getConnection();
        Statement statement = connection1.createStatement();
        String sql = "SELECT * FROM PACIENTE";
        ResultSet rd = statement.executeQuery(sql);
        while (rd.next()) {
            System.out.println(rd.getLong(1)+rd.getString(2)+rd.getString(3)+rd.getString(4)+rd.getString(5)+rd.getString(6)+rd.getString(7));
        }
    }



    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        //jdbc:h2:~/test
        return DriverManager.getConnection("jdbc:h2:" +
                "~/test", "sa", "");

    }
}
