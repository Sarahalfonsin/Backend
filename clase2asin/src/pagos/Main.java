package pagos;

public class Main {
    public static void main(String[] args) {
        Debito debito = new Debito(1, "asd123", 5,2025,"debito",10000d);
        debito.procesarPago(5000d,debito.getNumeroFrontal());
        debito.procesarPago(3000d,debito.getNumeroFrontal());
        debito.procesarPago(3000d,debito.getNumeroFrontal());
        System.out.println("");

        Credito credito = new Credito(2,"qweqwe",7,2025,"credito",10000d,0d);
        credito.procesarPago(5000d,credito.getNumeroFrontal());
        credito.procesarPago(7000d,credito.getNumeroFrontal());
        credito.procesarPago(1500d,credito.getNumeroFrontal());
        System.out.println("");

        Credito creditoExpirada = new Credito(3,"sdfasd3123",7,2020,"credito",10000d,0d);
        creditoExpirada.procesarPago(1000,creditoExpirada.getNumeroFrontal());
        System.out.println("");

        Debito debitoExpirada = new Debito(4, "hsfghgf", 12,2022,"debito",10000d);
        debitoExpirada.procesarPago(5000d,debitoExpirada.getNumeroFrontal());
        debitoExpirada.setMesExpiacion(1);
        debitoExpirada.procesarPago(500d,debitoExpirada.getNumeroFrontal());

    }
}
