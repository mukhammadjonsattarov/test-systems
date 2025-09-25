package service;

import model.Result;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsService {

    public static void showStatistics(List<Result> results) {
        if (results.isEmpty()) {
            System.out.println(" Hozircha natijalar mavjud emas.");
            return;
        }

        Result bestResult = results.stream()
                .max(Comparator.comparingInt(Result::getCorrectAnswers))
                .orElse(null);

        DoubleSummaryStatistics stats = results.stream()
                .collect(Collectors.summarizingDouble(Result::getDurationMs));

        System.out.println("\n===== Statistika =====");
        if (bestResult != null) {
            System.out.println(" Eng yuqori ball olgan foydalanuvchi: "
                    + bestResult.getUser().getName()
                    + " (" + bestResult.getCorrectAnswers() + " to‘g‘ri javob)");
        }
        System.out.println(" O‘rtacha vaqt: " + String.format("%.2f", stats.getAverage()) + " sekund");
    }
}