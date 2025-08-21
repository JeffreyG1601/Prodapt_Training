package D21p1;

import java.util.*;

class Question {
    int qid;
    String question;
    List<String> options;
    int correctOption; // index of correct option (0-based)

    Question(int qid, String question, List<String> options, int correctOption) {
        this.qid = qid;
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q").append(qid).append(": ").append(question).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((i + 1)).append(") ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}

public class D21c13 {
    private static LinkedHashMap<Integer, Question> questions = new LinkedHashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Preload some questions
        addSampleQuestions();

        int score = 0;
        System.out.println("=== Online Exam Started ===\n");

        for (Question q : questions.values()) {
            System.out.println(q);
            System.out.print("Enter your answer (1-" + q.options.size() + "): ");
            int ans = sc.nextInt();

            if (ans - 1 == q.correctOption) {
                System.out.println("✅ Correct!\n");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + (q.correctOption + 1) + "\n");
            }
        }

        System.out.println("=== Exam Finished ===");
        System.out.println("Your Score: " + score + " / " + questions.size());
    }

    private static void addSampleQuestions() {
        questions.put(1, new Question(1, "Which language is used for Android development?",
                Arrays.asList("Java", "Python", "Swift", "C#"), 0));
        questions.put(2, new Question(2, "Which collection does not allow duplicates?",
                Arrays.asList("List", "Set", "Map", "Queue"), 1));
        questions.put(3, new Question(3, "Which keyword is used to inherit a class in Java?",
                Arrays.asList("super", "this", "extends", "implements"), 2));
    }
}
