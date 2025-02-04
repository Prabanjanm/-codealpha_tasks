import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AIChatbot {
    private static Map<String, String> responses = new HashMap<>();

    // Predefined responses
    static {
        responses.put("hello", "Hi! How can I assist you today?");
        responses.put("hi", "Hello! How can I help you?");
        responses.put("how are you", "I'm just a bot, but I'm doing great! How about you?");
        responses.put("what is your name", "I'm ChatBot, your virtual assistant.");
        responses.put("bye", "Goodbye! Have a great day.");
        responses.put("thanks", "You're welcome!");
    }

    public static String getResponse(String userInput) {
        userInput = userInput.toLowerCase(); // Convert input to lowercase
        for (String key : responses.keySet()) {
            if (userInput.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm sorry, I didn't understand that. Can you rephrase?";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ChatBot: Hello! Type 'exit' to end the conversation.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("ChatBot: Goodbye! Have a nice day.");
                break;
            }

            String response = getResponse(userInput);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }
}