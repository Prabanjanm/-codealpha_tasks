import java.util.ArrayList;
import java.util.Scanner;

public class studentgradetracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        System.out.println("Enter student grades (type -1 to stop):");

        // Input grades dynamically
        while (true) {
            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();
            
            if (grade == -1) {
                break; // Stop when user enters -1
            }

            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grade! Enter a number between 0 and 100.");
                continue;
            }

            grades.add(grade);
        }

        // Check if there are any grades entered
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            // Compute statistics
            int highest = getHighest(grades);
            int lowest = getLowest(grades);
            double average = getAverage(grades);

            // Display results
            System.out.println("\n--- Grade Summary ---");
            System.out.println("Total Students: " + grades.size());
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
            System.out.printf("Average Grade: %.2f%n", average);
        }

        scanner.close();
    }

    // Method to calculate average
    public static double getAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    // Method to find highest grade
    public static int getHighest(ArrayList<Integer> grades) {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    // Method to find lowest grade
    public static int getLowest(ArrayList<Integer> grades) {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}