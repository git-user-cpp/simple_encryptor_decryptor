import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EncryptionProgram {
    private Scanner scanner;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffeledList;
    private char character;
    private char[] letters;

    public EncryptionProgram() {
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        shuffeledList = new ArrayList<>();
        character = ' ';

        newKey();
        askQuestion();
    }

    private void askQuestion() {
        while (true) {
            System.out.println("***************************************************");
            System.out.println("Choose an option:");
            System.out.println("(N)ew key, (G)et key, (E)ncrypt, (D)ecrypt, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response) {
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("Not a valid option.");
            }
        }
    }

    private void newKey() {
        character = ' ';
        list.clear();
        shuffeledList.clear();

        for(int i = 32; i < 127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }

        shuffeledList = new ArrayList<>(list);
        Collections.shuffle(shuffeledList);
        System.out.println("*a new key has been generated.*");
    }

    private void getKey() {
        System.out.print("Key: ");
        for (Character c : list) {
            System.out.print(c);
        }
        System.out.println();
        for (Character c : shuffeledList) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void encrypt() {
        System.out.println("Enter a message to be encrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = shuffeledList.get(j);
                    break;
                }
            }
        }

        System.out.println("Encrypted: ");
        for (char c : letters) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void decrypt() {
        System.out.println("Enter a message to be decrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < shuffeledList.size(); j++) {
                if (letters[i] == shuffeledList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }

        System.out.println("Decrypted: ");
        for (char c : letters) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void quit() {
        System.out.println("The program was terminated.");
        System.exit(0);
    }
}
