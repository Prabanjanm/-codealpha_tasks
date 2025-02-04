import java.util.*;

public class AIChatbot {
    private static Map<String, List<String>> responses = new HashMap<>();
    private static Random random = new Random();

    // Predefined responses with multiple variations
    static {
        responses.put("hello", Arrays.asList("Hi! How can I assist you today?", "Hello! What can I do for you?"));
        responses.put("hi", Arrays.asList("Hey there! How can I help?", "Hello! Need any assistance?"));
        responses.put("how are you", Arrays.asList("I'm just a bot, but I'm doing great! How about you?", "I'm always good! What about you?"));
        responses.put("what is your name", Arrays.asList("I'm ChatBot, your virtual assistant.", "You can call me ChatBot!"));
        responses.put("bye", Arrays.asList("Goodbye! Have a great day.", "See you later! Take care."));
        responses.put("thanks", Arrays.asList("You're welcome!", "Happy to help!"));
        responses.put("what can you do", Arrays.asList("I can chat with you, answer simple questions, and keep you company!", "I can respond to your messages and assist you with basic queries."));
        responses.put("tell me a joke", Arrays.asList(
                "Why don’t skeletons fight each other? Because they don’t have the guts!",
                "Why did the scarecrow win an award? Because he was outstanding in his field!"
        ));
        responses.put("how old are you", Arrays.asList("I'm ageless! But I was created recently.", "Age is just a number, and for me, it's infinite!"));
    }

    public static String getResponse(String userInput) {
        userInput = userInput.toLowerCase(); // Convert input to lowercase
        for (String key : responses.keySet()) {
            if (userInput.contains(key)) {
                List<String> possibleResponses = responses.get(key);
                return possibleResponses.get(random.nextInt(possibleResponses.size())); // Random response selection
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
