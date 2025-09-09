import java.util.Scanner;
import java.util.Random;

public class FlashCards {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static final int numCards = 27;
    
    private static Card[] loadDeck() {
        Card[] deck = new Card[numCards];
        
        deck[0] = new Card("Abstraction", "Hiding complex reality while exposing only essential parts");
        deck[1] = new Card("Algorithm", "A step-by-step procedure to solve a problem");
        deck[2] = new Card("Assertion", "A statement that a predicate is always true at that point in code execution");
        deck[3] = new Card("Class", "A template encapsulating data and code that manipulates it");
        deck[4] = new Card("Constructor", "A special class member that creates and initializes an object from the class");
        deck[5] = new Card("Copyright", "Legal protection for original works of authorship");
        deck[6] = new Card("Data Validation", "Process of ensuring data is correct and useful");
        deck[7] = new Card("Declaration", "Statement that establishes a variable's name and type");
        deck[8] = new Card("Definition", "Implementation of a variable, method, or class");
        deck[9] = new Card("Encapsulation", "Bundling of data with methods that operate on that data");
        deck[10] = new Card("Enumerated type", "A data type consisting of a set of named values");
        deck[11] = new Card("Exception", "An event that occurs during execution that disrupts normal flow");
        deck[12] = new Card("Field", "A variable that belongs to an object or class");
        deck[13] = new Card("Getter", "A method that returns the value of a private variable");
        deck[14] = new Card("Intellectual Property", "Creations of the mind that have legal protections");
        deck[15] = new Card("Invariant", "A condition that is always true during execution of a program");
        deck[16] = new Card("Method", "A function that is associated with an object");
        deck[17] = new Card("Object", "An instance of a class containing data and behavior");
        deck[18] = new Card("Object-Oriented Programming (OOP)", "Programming paradigm based on objects");
        deck[19] = new Card("Operator", "A symbol that represents a specific mathematical or logical action");
        deck[20] = new Card("Patent", "Exclusive right granted for an invention");
        deck[21] = new Card("Setter", "A method that sets the value of a private variable");
        deck[22] = new Card("Shadowing", "When a variable in a inner scope has the same name as in outer scope");
        deck[23] = new Card("Trademark", "A recognizable sign identifying products or services");
        deck[24] = new Card("Unified Modeling Language (UML)", "Standardized modeling language for software design");
        deck[25] = new Card("Validation Rules", "Constraints that data must satisfy");
        deck[26] = new Card("Variable", "A named storage location that can hold a value");
        
        return deck;
    }
    
    public static void main(String[] args) {
        Card[] deck = loadDeck();
        
        System.out.println("FLASH CARDS");
        System.out.println("===========");
        System.out.println("\nVocabulary terms:");
        
        for (Card card : deck) {
            System.out.println("* " + card.getTerm());
        }
        
        System.out.println();
        
        while (true) {
            int index = random.nextInt(deck.length);
            Card currentCard = deck[index];
            
            System.out.println(currentCard);
            System.out.print("Which term matches this definition ('q' to exit)? ");
            
            String response = scanner.nextLine().trim();
            
            if (response.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }
            
            if (currentCard.attempt(response)) {
                System.out.println("Correct!");
            } else {
                System.out.println("No, the term is " + currentCard.getTerm());
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
}
