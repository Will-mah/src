import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetBAG {
    private static int dogSpaces = 30;  // Number of dog spaces
    private static int catSpaces = 12;  // Number of cat  spaces
    private static List<Pet> petList = new ArrayList<>();  // List to store checked-in pets

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my Living Hell!!!!!!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Pet Check-In");
            System.out.println("2. Pet Check-Out");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    petCheckIn(scanner);
                    break;
                case 2:
                    petCheckOut(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for not breaking. Goodbye!");
    }

    public static void petCheckIn(Scanner scanner) {
        System.out.println("\nPet Check-In");

        // Determine if the pet is a dog or a cat
        System.out.println("Is the pet a dog or a cat?");
        String petType = scanner.nextLine();

        // Check if there is boarding space available
        int availableSpaces = petType.equalsIgnoreCase("dog") ? dogSpaces : catSpaces;
        if (availableSpaces == 0) {
            System.out.println("Sorry, there is no boarding space available for " + petType + "s.");
            return;
        }

        // Collect pet information
        System.out.println("Enter the pet's name:");
        String petName = scanner.nextLine();

        System.out.println("Enter the pet's age:");
        int petAge = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Declare weight variable
        double weight = 0.0;

        // Ask for weight if the pet is a dog
        if (petType.equalsIgnoreCase("dog")) {
            System.out.println("Enter the dog's weight (lbs):");
            weight = scanner.nextDouble();
            scanner.nextLine();  // Consume the newline character
        }

        System.out.println("Enter the length of stay (in days):");
        int daysStay = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Determine if grooming is requested for dogs staying two or more days
        boolean groomingRequested = false;
        if (petType.equalsIgnoreCase("dog") && daysStay >= 2) {
            System.out.println("Would you like the dog to be groomed? (yes/no)");
            String groomingInput = scanner.nextLine();
            groomingRequested = groomingInput.equalsIgnoreCase("yes");
        }

        // Create the appropriate pet object and assign the pet to a space
        Pet pet;
        int assignedSpace;
        if (petType.equalsIgnoreCase("dog")) {
            dogSpaces--;
            assignedSpace = dogSpaces + 1;
            pet = new Dog(petName, daysStay, weight, groomingRequested, petAge, assignedSpace);
        } else {
            catSpaces--;
            assignedSpace = catSpaces + 1;
            pet = new Cat(petName, daysStay, petAge, assignedSpace);
        }

        petList.add(pet);  // Add the pet to the checked-in pets list

        // Calculate fees
        double fees = calculateFees(pet);

        // Display check-in information
        System.out.println("Pet Check-In Successful:");
        System.out.println("Pet Type: " + pet.getPetType());
        System.out.println("Pet Name: " + pet.getPetName());
        System.out.println("Pet Age: " + pet.getPetAge());
        if (pet instanceof Dog) {
            System.out.println("Weight: " + ((Dog) pet).getDogWeight() + " lbs");
        }
        System.out.println("Days of Stay: " + pet.getDaysStay() + " days");
        if (pet instanceof Dog) {
            System.out.println("Grooming Requested: " + ((Dog) pet).isGroomingRequested());
        }
        System.out.println("Assigned Space: " + assignedSpace);
        System.out.println("Fees: $" + fees);
    }



    public static void petCheckOut(Scanner scanner) {
        System.out.println("\nPet Check-Out");

        // Check if there are any checked-in pets
        if (petList.isEmpty()) {
            System.out.println("No pets are currently checked in.");
            return;
        }

        // Display the list of checked-in pets
        System.out.println("List of Checked-In Pets:");
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            String petInfo = (i + 1) + ". " + pet.getPetName() + " --- " + pet.getPetType() + " --- " + pet.getPetAge();
            System.out.println(petInfo);
        }

        // Select the pet to check out
        System.out.println("Enter the number of the pet to check out:");
        int petIndex = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Check if the selected pet index is valid
        if (petIndex < 1 || petIndex > petList.size()) {
            System.out.println("Invalid pet number. Please try again.");
            return;
        }

        // Retrieve the selected pet from the list
        Pet pet = petList.get(petIndex - 1);

        // Remove the pet from the list
        petList.remove(petIndex - 1);

        // Mark the boarding space as vacant
        if (pet instanceof Dog) {
            dogSpaces++;
        } else {
            catSpaces++;
        }

        // Calculate fees
        double fees = calculateFees(pet);

        // Display check-out information
        System.out.println("Pet Check-Out Successful:");
        System.out.println("Pet Type: " + pet.getPetType());
        System.out.println("Pet Name: " + pet.getPetName());
        System.out.println("Pet Age: " + pet.getPetAge());
        if (pet instanceof Dog) {
            System.out.println("Weight: " + ((Dog) pet).getDogWeight() + " lbs");
        }
        System.out.println("Days of Stay: " + pet.getDaysStay() + " days");
        System.out.println("Fees: $" + fees);
    }
    public static double calculateFees(Pet pet) {
        if (pet instanceof Dog) {
            Dog dog = (Dog) pet;
            double groomingFee = 0.0;
            double boardingFee = 0.0;

            if (dog.isGroomingRequested() && dog.getDaysStay() >= 2) {
                groomingFee = 29.95;
            }

            if (dog.getDogWeight() >= 30) {
                boardingFee = 34.00;
            } else if (dog.getDogWeight() >= 20 && dog.getDogWeight() < 30) {
                boardingFee = 29.00;
            } else if (dog.getDogWeight() < 20) {
                boardingFee = 24.00;
            }

            return (boardingFee * dog.getDaysStay()) + groomingFee;
        } else if (pet instanceof Cat) {
            Cat cat = (Cat) pet;
            return 18.00 * cat.getDaysStay();
        } else {
            return 0.0;  // if pet dies idk idc at this point
        }
    }
}