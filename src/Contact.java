import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Contact {
    public static void main(String[] args){
        //save the directory and file names in variables
        String directory = "data";
        String filename = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path contactFilePath = Paths.get(directory, filename);

        //create a directory
        createDir(dataDirectory);

        //create a file
        createNewFile(contactFilePath);

        //write 3 contacts to the file
        List<String> contactList = Arrays.asList("Michael Jordan | 3120998170", "Lady Gaga | 7138250777", "Daniel Craig | 5820171995");
        writeFile(contactFilePath, contactList);




//        showOptions();

    }
    public static void showOptions(){

        System.out.println("1. View contacts. \n 2. Add a new contact. \n 3. Search a contact by name. \n 4. Delete " +
                "an existing contact. \n 5. Exit. \n Enter an option (1, 2, 3, 4, or 5): ");

    }

    public static void createDir(Path aDirectory) {
        if(Files.notExists(aDirectory)) {
            try {
                Files.createDirectory(aDirectory);
            }catch (IOException e) {
                System.out.println("Problem creating the directory.");
                e.printStackTrace();
            }
        }
    }


    public static void createNewFile(Path aFile) {
        if(Files.notExists(aFile)) {
            try {
                Files.createFile(aFile);
            }catch (IOException e) {
                System.out.println("Problem creating the file.");
                e.printStackTrace();
            }

        }
    }

    public static void writeFile(Path aFile, List<String> aList) {
        try {
            Files.write(aFile, aList);
        }catch (IOException e) {
            System.out.println("Problem writing in the file.");
            e.printStackTrace();
        }

    }

    //if (! Files.exists(dataFile)) {
    //    Files.createFile(dataFile);
    //}
    //inside the main method ^^^^
    //the actual method to add the contacts will be in the other method
}
