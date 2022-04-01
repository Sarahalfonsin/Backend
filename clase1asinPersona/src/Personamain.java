public class Personamain {
    private String Nombre;
    private String Apellido;
    private String Email;
    private int Edad;

    public Personamain(String nombre, String apellido, String email, int edad) {
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Edad = edad;
    }

    public String mostrarNombre(){
        return this.Nombre + ", " + this.Apellido;
    }
    public Boolean mayor(){
        return this.Edad >=  18;
    }
}
