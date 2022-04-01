package pagos;

public class Debito extends Tarjeta {
    private double saldoDisponible;


    public Debito(int numeroFrontal, String codigoSeguridad, int mesExpiacion, int anioExpiacion, String tipo, double saldoDisponible) {
        super(numeroFrontal, codigoSeguridad, mesExpiacion, anioExpiacion, tipo);
        this.saldoDisponible = saldoDisponible;
    }

    @Override
    public boolean checkearAutorizacion(double importe) {
        return this.saldoDisponible >= importe;
    }

    @Override
    public void procesarPago(double importe, int numero) {
        if (fechaValida()) {
            if (checkearAutorizacion(importe)) {
                saldoDisponible -= importe;
                System.out.println("El pago de la tarjeta de " + this.getTipo() + " " + numero + " fue procesado, su saldo disponible ahora es de: " + saldoDisponible);
            } else {
                System.out.println("No es posible autorizar el pago.");
            }
        } else {
            System.out.println("La tarjeta excede su fecha de expiacion");
        }
    }


}
