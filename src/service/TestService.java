package service;

import model.Question;
import model.Result;
import model.User;
import util.DateUtil;
import util.LoggerUtil;
import util.RandomUtil;

import java.util.List;

public class TestService implements Runnable {
    private final User user;
    private final List<Question> questions;

    public TestService(User user, List<Question> questions) {
        this.user = user;
        this.questions = questions;
    }

    @Override
    public void run() {
        LoggerUtil.info("Foydalanuvchi '" + user.getName() + "' testni boshladi.");

        int correct = 0;
        long start = System.currentTimeMillis();


        for (Question q : questions) {
            System.out.println("\n" + user.getName() + " uchun savol: " + q.getText());
            System.out.println("a) " + q.getOptionA());
            System.out.println("b) " + q.getOptionB());
            System.out.println("c) " + q.getOptionC());
            System.out.println("d) " + q.getOptionD());

            String[] options = {"a", "b", "c", "d"};
            String answer = RandomUtil.getRandomElement(List.of(options));
            System.out.println(user.getName()+" javobi: " + answer);

            if (answer.equalsIgnoreCase(q.getCorrectAnswer())) {
                correct++;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        long duration = DateUtil.calculateDuration(start, end);
        double percentage = (correct * 100.0) / questions.size();

        Result result = new Result(user, correct, percentage, duration);
        UserService.saveResult(result);

        LoggerUtil.info("Foydalanuvchi '" + user.getName() +
                "' testni tugatdi. Natija: " + correct + "/" + questions.size() +
                " (" + percentage + "%), vaqt: " + duration + " ms");
    }
}
