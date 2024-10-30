package de.rasmusantons.cubiomes;

import java.io.*;

/**
 * Extracts the embedded native library from the jar and loads it.
 */
public class NativeLibLoader {
    public static boolean isLoaded = false;

    private static String getArch() {
        String arch = System.getProperty("os.arch");
        return switch (arch) {
            case "amd64" -> "x86-64";
            case "aarch64" -> "aarch64";
            default -> throw new UnsupportedArchitectureException(arch);
        };
    }

    private static boolean load() {
        String prefix = "lib";
        String arch = getArch();
        String suffix = ".so";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            prefix = "";
            suffix = ".dll";
        }
        try (InputStream input = NativeLibLoader.class.getResourceAsStream("/" + prefix + "cubij_" + arch + suffix)) {
            if (input == null)
                throw new RuntimeException("Missing native library for " + arch);
            int read;
            byte[] buffer = new byte[8192];
            final File libfile = File.createTempFile(prefix + "cubij_" + arch, suffix);
            libfile.deleteOnExit();
            final OutputStream output = new BufferedOutputStream(new FileOutputStream(libfile));
            while ((read = input.read(buffer)) > -1)
                output.write(buffer, 0, read);
            output.close();
            System.load(libfile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read native library for " + arch);
        }
        return true;
    }

    /**
     * This will load the library only if it hasn't been loaded yet. This function can be called multiple times.
     *
     * @return true, if the library was successfully extracted, otherwise false
     */
    public static boolean ensureLoaded() {
        if (!isLoaded)
            isLoaded = load();
        return isLoaded;
    }
}
