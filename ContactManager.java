import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactManager {

    public static void main(String[] args) throws IOException {
        Path namesAndNumbers = Paths.get("contacts.txt");
        List<String> listInfo = Files.readAllLines(namesAndNumbers);

        //add a contact
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):\n");

        Scanner userChoice = new Scanner(System.in);
        System.out.println("Enter a number: ");
        String numberPicked = userChoice.nextLine();

        if(numberPicked.trim().equals("1")){
            for (String i : listInfo) {
                System.out.printf("%n%s", i);
            }
        } else if (numberPicked.trim().equals("2")) {
            //prompt the user to enter a contact
            Scanner newContactInput = new Scanner(System.in);
            System.out.println("Enter a name and phone number: ");
            String userContact = newContactInput.nextLine();
            List<String> myData = List.of(userContact);
            Files.write(namesAndNumbers, myData, StandardOpenOption.APPEND);

            //would the user like to see the users now?
            Scanner contactsListAgain = new Scanner(System.in);
            System.out.println("Would you like to see the contacts list? yes/no: ");
            String usersContactsList = contactsListAgain.nextLine();
            if(usersContactsList.toLowerCase().trim().equals("yes")){
                for (String i : listInfo) {
                    System.out.printf("%n%s", i);
                }
            }
        } else if (numberPicked.trim().equals("3")) {
            Scanner userSearch = new Scanner(System.in);
            System.out.println("Enter the contacts name: ");
            String whatUserSearched = userSearch.nextLine();
            for (String i : listInfo) {
                if (i.toLowerCase().contains(whatUserSearched)) {
                    System.out.printf("%n%s", i);
                }
            }
        } else if(numberPicked.trim().equals("4")) {
            Scanner deleteContact = new Scanner(System.in);
            System.out.println("Please give the name or number you wish to delete: ");
            String userDeletedContact = deleteContact.nextLine();
            System.out.println(listInfo.remove(userDeletedContact));
            listInfo.remove(userDeletedContact);
//            listInfo.remove(int indexOf(userDeletedContact));
            for (String i : listInfo) {
                System.out.printf("%n%s", i);
            }
            Files.write(namesAndNumbers, listInfo);
//            Files.write(namesAndNumbers, listInfo, StandardOpenOption.APPEND);

//            for(String i : listInfo) {
//                if (userDeletedContact.toLowerCase().equals(i)) {
//                    String x = i.replaceAll(userDeletedContact, " ");
//                }
//            }
        }
    }
}

