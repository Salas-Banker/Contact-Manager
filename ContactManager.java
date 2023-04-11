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
//        int y = startingMenu();

//        switch (y) {
//            case 1 -> viewContacts();
//            case 2 -> addContact();
//            case 3 -> searchContact();
//            case 4 -> deleteContact();
//        }
    }

    public static void contactApp() throws IOException {
        int y = startingMenu();
        do {
            switch (y) {
                case 1 -> viewContacts();
                case 2 -> addContact();
                case 3 -> searchContact();
                case 4 -> deleteContact();
                case 5 -> {
                    break;
                }
            }
        } while(true);
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
        for (String i : listInfo) {
            System.out.printf("%n%s", i);
        }
    }

    public static void addContact() throws IOException {
        //prompt the user to enter a contact
        System.out.println("Enter a name and phone number: ");
        String userContact = userInput.nextLine();
        List<String> myData = List.of(userContact);
        Files.write(namesAndNumbers, myData, StandardOpenOption.APPEND);
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
        while(!x) {
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

