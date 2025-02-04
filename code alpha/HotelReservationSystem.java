import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    public void bookRoom() { isAvailable = false; }
    public void freeRoom() { isAvailable = true; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + category + "] - $" + price + " per night";
    }
}

class Booking {
    private String guestName;
    private Room room;
    private int nights;
    private double totalPrice;

    public Booking(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalPrice = room.getPrice() * nights;
    }

    public String getGuestName() { return guestName; }
    public Room getRoom() { return room; }
    public double getTotalPrice() { return totalPrice; }

    
    @Override
    public String toString() {
        return "Booking for " + guestName + ": " + room.toString() + ", Nights: " + nights + ", Total: $" + totalPrice;
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private Map<Integer, Booking> reservations = new HashMap<>();

    public Hotel() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Single", 100));
        rooms.add(new Room(201, "Double", 150));
        rooms.add(new Room(202, "Double", 150));
        rooms.add(new Room(301, "Suite", 300));
    }

    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public boolean bookRoom(int roomNumber, String guestName, int nights) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.bookRoom();
                Booking booking = new Booking(guestName, room, nights);
                reservations.put(roomNumber, booking);
                System.out.println("\nBooking confirmed: " + booking);
                return true;
            }
        }
        System.out.println("\nRoom not available or invalid room number.");
        return false;
    }

    public void showBookings() {
        System.out.println("\nCurrent Bookings:");
        if (reservations.isEmpty()) {
            System.out.println("No active reservations.");
        } else {
            for (Booking booking : reservations.values()) {
                System.out.println(booking);
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    System.out.print("\nEnter your name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();
                    hotel.bookRoom(roomNumber, guestName, nights);
                    break;

                case 3:
                    hotel.showBookings();
                    break;

                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}