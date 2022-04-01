package pagos;

public class Credito extends Tarjeta {
    private double limite;
    private double saldoUtilizado;

    public Credito(int numeroFrontal, String codigoSeguridad, int mesExpiacion, int anioExpiacion, String tipo, double limite, double saldoUtilizado) {
        super(numeroFrontal, codigoSeguridad, mesExpiacion, anioExpiacion, tipo);
        this.limite = limite;
        this.saldoUtilizado = saldoUtilizado;
    }

    @Override
    public boolean checkearAutorizacion(double importe) {
        return saldoUtilizado + importe <= limite;
    }

    @Override
    public void procesarPago(double importe, int numero) {
        if (fechaValida()) {
            if (checkearAutorizacion(importe)) {
                saldoUtilizado += importe;
                System.out.println("El pago de la tarjeta de " + this.getTipo() + " " + numero + " fue procesado, su saldo utilizado ahora es de: " + saldoUtilizado);
            } else {
                System.out.println("No es posible autorizar el pago.");
            }
        } else {
            System.out.println("La tarjeta excede su fecha de expiacion");
        }
    }
}