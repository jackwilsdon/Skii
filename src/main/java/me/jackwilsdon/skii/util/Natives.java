package me.jackwilsdon.skii.util;

public enum Natives {
    LINUX32("linux32"),
    MACOSX32("macosx32"),
    WINDOWS32("windows32", "lwjgl.dll", "OpenAL32.dll"),

    LINUX64("linux64"),
    MACOSX64("macosx64", "liblwjgl.dylib", "openal.dylib"),
    WINDOWS64("windows64", "lwjgl64.dll", "OpenAL64.dll");

    private String platform;
    private String[] files;

    Natives(String platform, String... files) {
        this.platform = platform;
        this.files = files;
    }

    public String getPlatform() {
        return platform;
    }

    public String[] getFiles() {
        return files;
    }

    public static Natives getNatives(String platformName) {
        for (Natives natives : values()) {
            if (natives.getPlatform().equals(platformName)) {
                return natives;
            }
        }

        return null;
    }

    public static String[] getPlatformFiles(String platformName) {
        Natives natives = getNatives(platformName);

        if (natives == null) {
            return new String[0];
        }

        return natives.getFiles();
    }
}