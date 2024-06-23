package de.rasmusantons.cubiomes;

import java.io.*;

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

    public static boolean ensureLoaded() {
        if (!isLoaded)
            isLoaded = load();
        return isLoaded;
    }
}
