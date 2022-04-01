package pagos;

import java.util.Calendar;

public abstract class Tarjeta {
    private int numeroFrontal;
    private String codigoSeguridad;
    private int mesExpiacion;
    private int anioExpiacion;
    private String tipo;

    public Tarjeta(int numeroFrontal, String codigoSeguridad, int mesExpiacion, int anioExpiacion, String tipo) {
        this.numeroFrontal = numeroFrontal;
        this.codigoSeguridad = codigoSeguridad;
        this.mesExpiacion = mesExpiacion;
        this.anioExpiacion = anioExpiacion;
        this.tipo = tipo;
    }

    public int getNumeroFrontal() {
        return numeroFrontal;
    }

    public void setNumeroFrontal(int numeroFrontal) {
        this.numeroFrontal = numeroFrontal;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public int getMesExpiacion() {
        return mesExpiacion;
    }

    public void setMesExpiacion(int mesExpiacion) {
        this.mesExpiacion = mesExpiacion;
    }

    public int getAnioExpiacion() {
        return anioExpiacion;
    }

    public void setAnioExpiacion(int anioExpiacion) {
        this.anioExpiacion = anioExpiacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract boolean checkearAutorizacion(double importe);

    public abstract void procesarPago(double importe, int numero);

    public boolean fechaValida() {
        boolean respuesta = false;

        Calendar date = Calendar.getInstance();
        int anio = date.get(Calendar.YEAR);
        int mes = date.get(Calendar.MONTH);

        if (this.anioExpiacion > anio) {
            respuesta = true;
        }
        if (this.anioExpiacion == anio && this.mesExpiacion > mes) {
            respuesta = true;
        }

        return respuesta;
    }



    }

