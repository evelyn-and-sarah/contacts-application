import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Contact {
    public static void main(String[] args) {
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
//        List<String> contactList = Arrays.asList();
//        writeFile(contactFilePath, contactList);

        //print out each line in the file
//        readFile(contactFilePath, true);


        readFile(contactFilePath, true);
        showOptions();

    }

    public static void showOptions() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. View contacts. \n 2. Add a new contact. \n 3. Search a contact by name. \n 4. Delete " +
                    "an existing contact. \n 5. Exit. \n Enter an option (1, 2, 3, 4, or 5): ");
            String inp = sc.nextLine();

            String directory = "data";
            String filename = "contacts.txt";
            Path contactFilePath = Paths.get(directory, filename);

            if (inp.equals("1")) {
                readFile(contactFilePath, true);
            } else if (inp.equals("2")) {
                System.out.println("Enter name: ");
                String newName = sc.nextLine();
                System.out.println("Enter phone number: ");
                String newPhone = sc.nextLine();
                List<String> newItem = Arrays.asList(newName + " | " + newPhone);

                try {
                    Files.write(
                            contactFilePath,
                            newItem,
                            StandardOpenOption.APPEND);
                    readFile(contactFilePath, true);
                } catch (IOException e) {
                    System.out.println("Problem adding new contact.");
                    e.printStackTrace();
                }
            }else if(inp.equals("3")){
                System.out.println("searchContact() = " + searchContact());
            }else if (inp.equals("4")) {
                deleteContact();
            } else if (inp.equals("5")) {
                //exit the application
                quit();
                break;
            }


        }
    }

    public static void createDir(Path aDirectory) {
        if (Files.notExists(aDirectory)) {
            try {
                Files.createDirectory(aDirectory);
            } catch (IOException e) {
                System.out.println("Problem creating the directory.");
                e.printStackTrace();
            }
        }
    }


    public static void createNewFile(Path aFile) {
        if (Files.notExists(aFile)) {
            try {
                Files.createFile(aFile);
            } catch (IOException e) {
                System.out.println("Problem creating the file.");
                e.printStackTrace();
            }

        }
    }

    public static void writeFile(Path aFile, List<String> aList) {
        try {
            Files.write(aFile, aList);
        } catch (IOException e) {
            System.out.println("Problem writing in the file.");
            e.printStackTrace();
        }

    }

    public static List<String> readFile(Path aFile, boolean print) {
        List<String> lines;
        try {
            lines = Files.readAllLines(aFile);
            if (print == true) {
                System.out.println("Name | Phone Number");
                System.out.println("-------------------");
                for (String line : lines) {
                    System.out.println(line);
                }
//                return null;
            }
            return lines;
        } catch (IOException e) {
            System.out.println("Problem reading the file.");
            e.printStackTrace();
            return null;
        }

    }

    public static String searchContact(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Search Contacts: ");
        String userSearch = sc.nextLine();
        String directory = "data";
        String filename = "contacts.txt";
        Path contactFilePath = Paths.get(directory, filename);
        while(true) {
            try {
                List<String> lines = Files.readAllLines(contactFilePath);

                for (String line : lines) {
                    if (line.toLowerCase().contains(userSearch.toLowerCase())) {
                        return line;
                    }

                }
                System.out.println("Contact not found.");
                return searchContact();
            } catch (IOException e) {
                System.out.println("Problem searching for contact.");
                e.printStackTrace();
            }
        }

    }

    public static void deleteContact(){
        Scanner sc = new Scanner(System.in);
        String directory = "data";
        String filename = "contacts.txt";
        Path contactFilePath = Paths.get(directory, filename);
        System.out.println("Enter a contact to delete.");
        String deletedName = sc.nextLine();
        try{
            List<String> lines = Files.readAllLines(contactFilePath);
            List<String> newList = new ArrayList<>();
            for(String line : lines){
                if(line.toLowerCase().contains(deletedName.toLowerCase())){
                    continue;
                }
                newList.add(line);
            }
            writeFile(contactFilePath, newList);
        }catch(IOException e){
            System.out.println("Problem deleting contact.");
            e.printStackTrace();
        }
    }

    public static void quit() {
        System.out.println("Goodbye.");
    }

}
