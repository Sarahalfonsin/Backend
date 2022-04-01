package calidad;

public class ManejadorTecnico extends Manejador {
    @Override
    public void comprobar(Mails mail) {
        if (mail.getTema().equalsIgnoreCase("Tecnico") || mail.getDestino().equalsIgnoreCase("tecnica@colmena.com")) {

            mail.setDestino("tecnica@colmena.com");
            System.out.println("El email de " + mail.getOrigen() + "fue enviado a " + mail.getDestino());

        } else {
            if (this.getSiguiente() != null) {
                // Llamamos al método en el siguiente objeto
                this.getSiguiente().comprobar(mail);
            }

        }
    }

}
