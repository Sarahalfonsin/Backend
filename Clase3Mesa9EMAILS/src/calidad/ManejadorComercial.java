package calidad;

public class ManejadorComercial extends Manejador {

    @Override
    public void comprobar(Mails mail) {
        if (mail.getTema().equalsIgnoreCase("Comercial") || mail.getDestino().equalsIgnoreCase("comercial@colmena.com")) {

            mail.setDestino("comercial@colmena.com");
            System.out.println("El email de " + mail.getOrigen() + "fue enviado a " + mail.getDestino());

        } else {
            if (this.getSiguiente() != null) {
                // Llamamos al método en el siguiente objeto
                this.getSiguiente().comprobar(mail);
            }

        }
    }
}


