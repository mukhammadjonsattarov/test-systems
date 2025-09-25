import model.Question;
import model.Result;
import model.User;
import service.FileService;
import service.StatisticsService;
import service.UserService;
import util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;

public class Main {
    private static List<Question> questions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        LoggerUtil.info("Dastur ishga tushdi.");

        while (running) {
            System.out.println("\n===== Test Tizimiga Xush Kelibsiz =====");
            System.out.println("1. Test faylini yuklash");
            System.out.println("2. Testni boshlash (simulyatsiya)");
            System.out.println("3. Statistika ko‘rish");
            System.out.println("4. Chiqish");
            System.out.print("Tanlang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    questions = FileService.loadQuestions();
                    if (!questions.isEmpty()) {
                        LoggerUtil.info("Fayldan savollar muvaffaqiyatli yuklandi. Savollar soni: " + questions.size());
                    }
                }
                case 2 -> {
                    if (questions == null || questions.isEmpty()) {
                        System.out.println("❌ Avval test faylini yuklang!");
                    } else {
                        System.out.print("Nechta foydalanuvchi simulyatsiya qilinsin? ");
                        int userCount = scanner.nextInt();
                        scanner.nextLine();

                        List<User> users = new ArrayList<>();
                        for (int i = 1; i <= userCount; i++) {
                            users.add(new User("User " + i));
                        }

                        for (User user : users) {
                            UserService.startTestAsync(user, questions);
                        }
                    }
                }
                case 3 -> {
                    List<Result> results = UserService.getResults();
                    StatisticsService.showStatistics(results);
                }
                case 4 -> {
                    running = false;
                    UserService.shutdown();
                    LoggerUtil.info("Dastur to‘xtatildi.");
                }
                default -> System.out.println("❌ Noto‘g‘ri tanlov!");
            }
        }
    }
}
