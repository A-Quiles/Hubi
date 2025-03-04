package es.hubi.excepciones;

public class HubiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HubiException(String mensaje) {
        super(mensaje);
    }

    public HubiException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
