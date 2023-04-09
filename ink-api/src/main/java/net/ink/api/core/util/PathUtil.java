package net.ink.api.core.util;

public class PathUtil {
    private PathUtil() { }

    public static String replaceWindowPathToLinuxPath(String path) {
        return path.replaceAll("\\\\", "/");
    }
}
