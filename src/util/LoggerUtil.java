package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;

public class LoggerUtil {
    private static final Path logPath = Paths.get("logs.txt");

    private static void write(String level, String message) {
        String log = LocalDateTime.now() + " [" + level + "] " + message + System.lineSeparator();
        try {
            Files.writeString(logPath, log, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Log yozishda xatolik: " + e.getMessage());
        }
    }

    public static void info(String message) {
        write("INFO", message);
    }

    public static void error(String message) {
        write("ERROR", message);
    }
}
