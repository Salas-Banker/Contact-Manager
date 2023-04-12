import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactManager {

    static Scanner userInput = new Scanner(System.in);
    static Scanner userIntInput = new Scanner(System.in);
    static Path namesAndNumbers = Paths.get("contacts.txt");

    public static void main(String[] args) throws IOException {
        contactApp();
    }

    public static void contactApp() throws IOException {
        boolean confirm;
        do {
            int y = startingMenu();
            switch (y) {
                case 1 -> viewContacts();
                case 2 -> addContact();
                case 3 -> searchContact();
                case 4 -> deleteContact();
                case 5 -> {
                    break;
                }
            }
            System.out.println("\nWould you like to try something else? Enter yes or no.");
            confirm = userInput.nextLine().equalsIgnoreCase("Yes");
        } while (confirm);
    }


    public static int startingMenu() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n");
        System.out.println("Enter a number: ");
        return userIntInput.nextInt();
    }

    public static void viewContacts() throws IOException {
        Path namesAndNumbers = Paths.get("contacts.txt");
        List<String> listInfo = Files.readAllLines(namesAndNumbers);
        System.out.printf("%n%-21s | %s |", "Name", "Phone Number");
        for (String i : listInfo) {
            String[] lineSplit = i.split(" ");
            System.out.printf("%n%-10s %-10s | %-10s |", lineSplit[0], lineSplit[1], lineSplit[2]);
        }
    }

    public static void addContact() throws IOException {
        //prompt the user to enter a contact
        boolean x = false;
        while (!x) {
            System.out.println("Enter a valid name and phone number. ex: john doe 1234567890");
            String userContact = userInput.nextLine();
            String[] inputContact = userContact.trim().split(" ");
            boolean isNum = false;
            boolean isChar = false;
            //checks if either the first name or last name contains a number
            for (int i = 0; i < inputContact.length -1; i++) {
                String a = inputContact[i];
                char[] chars = a.toCharArray();
                for (char l : chars) {
                    if(Character.isDigit(l)) {
                        isNum = true;
                    }
                }
            }
            //checks to see if the phone number contains a letter
            char[] chars = inputContact[inputContact.length -1].toCharArray();
            for(char i : chars){
                if(!Character.isDigit(i)) {
                    isChar = true;
                }
            }
            List<String> myData = List.of(userContact);
            List<String> listInfo = Files.readAllLines(namesAndNumbers);
            if(!isNum && !isChar && inputContact.length == 3) {
                if (listInfo.contains(userContact)) {
                    System.out.printf("Contact %s already exists, would you like to add another? yes or no", userContact);
                    if (userInput.nextLine().equalsIgnoreCase("No")) {
                        x = true;
                    }
                } else {
                    Files.write(namesAndNumbers, myData, StandardOpenOption.APPEND);
                    x = true;
                }
            } else {
                System.out.println("That was an invalid input. Please make sure there is a first name and a second name consisting of only letters and that the phone number consist of only numbers.");
            }
        }
    }

    public static void searchContact() throws IOException {
        List<String> listInfo = Files.readAllLines(namesAndNumbers);
        System.out.println("Enter the contacts name: ");
        String whatUserSearched = userInput.nextLine();
        for (String i : listInfo) {
            if (i.toLowerCase().contains(whatUserSearched)) {
                System.out.printf("%n%s", i);
            }
        }
    }

    public static void deleteContact() throws IOException {
        List<String> listInfo = Files.readAllLines(namesAndNumbers);
        boolean x = false;
        System.out.println("Please give the first and last name and number you wish to delete: ");
        while (!x) {
            String userDeletedContact = userInput.nextLine();
            if (listInfo.remove(userDeletedContact)) {
                Files.write(namesAndNumbers, listInfo);
                x = true;
            } else {
                System.out.println("The contact you entered is not valid please try again:");
            }
        }
    }
}

