package calidad;

public class CompruebaCalidad {
        Manejador inicial;

        public CompruebaCalidad() {
            this.inicial = new ManejadorGerencia();
            Manejador Comercial = new ManejadorComercial();
            Manejador Tecnico = new ManejadorTecnico();
            Manejador Final = new ManejadorFinal();

            inicial.setSiguiente( Comercial);
            Comercial.setSiguiente( Tecnico );
            Tecnico.setSiguiente( Final );
        }

        public void comprobar(Mails  mail)
        {
            inicial.comprobar( mail );
        }
    }

