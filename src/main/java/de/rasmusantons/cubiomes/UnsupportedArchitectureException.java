package de.rasmusantons.cubiomes;

/**
 * Thrown if the code is executed on an unknown architecture (anything except than x64 and aarch64).
 */
public class UnsupportedArchitectureException extends RuntimeException {
    public UnsupportedArchitectureException(String message) {
        super(String.format("Unsupported architecture: %s", message));
    }
}
