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
        List<String> myData = List.of("david Banker 6897765234");
        Files.write(namesAndNumbers, myData, StandardOpenOption.APPEND);
        List<String> listInfo = Files.readAllLines(namesAndNumbers);
        for (String i : listInfo) {
            System.out.printf("%s%n", i);
        }
        Scanner newInput = new Scanner(System.in);

    }
}
