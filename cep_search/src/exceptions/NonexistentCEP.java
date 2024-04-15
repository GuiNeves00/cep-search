package exceptions;

public class NonexistentCEP extends RuntimeException {
    private String message;

    public NonexistentCEP(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
