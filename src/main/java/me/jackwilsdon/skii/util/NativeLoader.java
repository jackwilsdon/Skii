package me.jackwilsdon.skii.util;

import org.lwjgl.LWJGLUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class NativeLoader {
    private File nativeDirectory;

    public NativeLoader(File nativeDirectory) {
        this.nativeDirectory = nativeDirectory;
    }

    public NativeLoader(String nativeDirectory) {
        this(new File(nativeDirectory));
    }

    public NativeLoader() {
        this("natives");
    }

    private File extractPlatform(String platformName) {
        String[] files = Natives.getPlatformFiles(platformName);
        File platformDirectory = new File(nativeDirectory, platformName);

        if (files.length == 0 || (!platformDirectory.exists() && !platformDirectory.mkdirs())) {
            return null;
        }

        for (String filename : files) {
            String resourceName = "/" + filename;

            if (extractResourceToDirectory(resourceName, platformDirectory) == null) {
                return null;
            }
        }

        return platformDirectory;
    }

    private void loadPlatform(String platformName) {
        if (extractPlatform(platformName) == null) {
            System.out.println("Failed to extract for platform " + platformName);
        }
    }

    public boolean load() {
        File platformDirectory = extractPlatform(getPlatform(true));

        if (platformDirectory == null) {
            return false;
        }

        System.setProperty("org.lwjgl.librarypath", platformDirectory.getAbsolutePath());

        return true;
    }

    private static boolean is64bit() {
        String architecture = System.getProperty("os.arch");
        return architecture.equals("amd64") || architecture.equals("x86_64");
    }

    private static String getPlatform(boolean includeArchitecture) {
        String platformName = LWJGLUtil.getPlatformName();

        if (includeArchitecture) {
            String architecture = System.getProperty("os.arch");

            if (architecture.equals("amd64") || architecture.equals("x86_64")) {
                platformName += "64";
            } else {
                platformName += "32";
            }
        }

        return platformName;
    }

    private static File extractResourceToDirectory(String source, File directory) {
        InputStream sourceStream = NativeLoader.class.getResourceAsStream(source);

        if (sourceStream == null || (!directory.exists() && !directory.mkdirs())) {
            return null;
        }

        String destinationName = new File(source).getName();
        File destination = new File(directory, destinationName);

        if (destination.exists()) {
            return destination;
        }

        try {
            Files.copy(sourceStream, destination.toPath());
        } catch (IOException ignored) {
            return null;
        }

        if (!destination.exists()) {
            return null;
        }

        return destination;
    }
}
