package service;

import model.Question;
import util.LoggerUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            Path path = Path.of(FileService.class.getClassLoader().getResource("java_questions.csv").toURI());

            List<String> lines = Files.readAllLines(path);

            if (lines.size() <= 1) {
                LoggerUtil.error("Faylda savollar topilmadi: java_questions.csv");
                return questions;
            }

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    questions.add(new Question(
                            parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]
                    ));
                }
            }

            LoggerUtil.info("Fayldan savollar yuklandi: " + questions.size());

        } catch (Exception e) {
            LoggerUtil.error("Fayl yuklashda xatolik: " + e.getMessage());
        }

        return questions;
    }
}
