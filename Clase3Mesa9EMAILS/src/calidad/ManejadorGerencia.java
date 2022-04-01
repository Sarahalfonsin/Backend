package calidad;

public class ManejadorGerencia extends Manejador {


    @Override
    public void comprobar(Mails mail) {
        if (mail.getTema().equalsIgnoreCase("Gerencia") || mail.getDestino().equalsIgnoreCase("gerencia@colmena.com")) {

            mail.setDestino("gerencia@colmena.com");
            System.out.println("El email de " + mail.getOrigen() + "fue enviado a " + mail.getDestino());

        } else {
            if (this.getSiguiente() != null) {
                // Llamamos al método en el siguiente objeto
                this.getSiguiente().comprobar(mail);
            }

        }
    }
}
