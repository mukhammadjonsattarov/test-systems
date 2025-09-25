package service;

import model.Question;
import model.Result;
import model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    private static final List<Result> results = new CopyOnWriteArrayList<>();

    public static List<Result> getResults() {
        return results;
    }

    public static void saveResult(Result result) {
        results.add(result);
    }

    public static void startTestAsync(User user, List<Question> questions) {
        executor.submit(() -> {
            try {new TestService(user, questions).run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}
