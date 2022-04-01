package calidad;

public class ManejadorFinal extends Manejador{


    @Override
    public void comprobar(Mails mail) {
        System.out.println("El mensaje paso a SPAM");
    }
}
