package de.rasmusantons.cubiomes;

import java.io.*;

/**
 * Extracts the embedded native library from the jar and loads it.
 */
public class NativeLibLoader {
    public static boolean isLoaded = false;

    private static boolean load() {
        // todo: load .dll on windows
        try (InputStream input = NativeLibLoader.class.getResourceAsStream("/libcubij.so")) {
            if (input == null)
                return false;
            int read;
            byte[] buffer = new byte[8192];
            final File libfile = File.createTempFile("libcubij", ".so");
            libfile.deleteOnExit();
            final OutputStream output = new BufferedOutputStream(new FileOutputStream(libfile));
            while ((read = input.read(buffer)) > -1)
                output.write(buffer, 0, read);
            output.close();
            System.load(libfile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
