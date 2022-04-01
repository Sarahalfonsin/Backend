package calidad;

public class ProcesaMail {
    public static void main(String[] args) {
        CompruebaCalidad compruebaCalidad =new CompruebaCalidad();
         compruebaCalidad.comprobar(new Mails("SARAHALFONSIN@HOTMAIL.COM","comercial@sarah.com","Comercial"));
        compruebaCalidad.comprobar(new Mails("SARAHALFONSIN@HOTMAIL.COM","gerencia@colmena.com","Gerencia"));
        compruebaCalidad.comprobar(new Mails("SARAHALFONSIN@HOTMAIL.COM","comercial@sarah.com","QUEJA"));
        compruebaCalidad.comprobar(new Mails("SARAHALFONSIN@HOTMAIL.COM","comercial@sarah.com","Tecnico"));

    }
}
