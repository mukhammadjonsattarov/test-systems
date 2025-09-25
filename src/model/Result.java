package model;

public class Result {
    private final User user;
    private final int correctAnswers;
    private final double percentage;
    private final long durationMs;

    public Result(User user, int correctAnswers, double percentage, long durationMs) {
        this.user = user;
        this.correctAnswers = correctAnswers;
        this.percentage = percentage;
        this.durationMs = durationMs;
    }

    public User getUser() {
        return user;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public double getPercentage() {
        return percentage;
    }

    public long getDurationMs() {
        return durationMs;
    }

    @Override
    public String toString() {
        return "User=" + user.getName() +
                ", correct=" + correctAnswers +
                ", percentage=" + percentage + "%" +
                ", time=" + durationMs + " ms";
    }

}
