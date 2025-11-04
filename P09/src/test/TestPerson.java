package test;
import people.Person;
public class TestPerson {
    public static void main(String[] args) {
        testToString();
        testGetName();
        testEquals();
        System.out.println("All TestPerson tests passed!");
    }

    private static void testToString() {
        Person person = new Person("John Doe", "john@example.com");
        String result = person.toString();
        if (!result.equals("John Doe (john@example.com)")) {
            throw new AssertionError("toString format incorrect: " + result);
        }
    }

    private static void testGetName() {
        Person person = new Person("Jane Smith", "jane@example.com");
        if (!person.getName().equals("Jane Smith")) {
            throw new AssertionError("getName returned wrong name");
        }
    }

    private static void testEquals() {
        Person person1 = new Person("Alice", "alice@example.com");
        Person person2 = new Person("Alice", "alice@example.com");
        Person person3 = new Person("Bob", "bob@example.com");
        
        if (!person1.equals(person1)) {
            throw new AssertionError("Same object should be equal");
        }
        
        if (!person1.equals(person2)) {
            throw new AssertionError("Objects with same data should be equal");
        }
        
        if (person1.equals(person3)) {
            throw new AssertionError("Objects with different data should not be equal");
        }
        
        if (person1.equals(null)) {
            throw new AssertionError("Comparison with null should return false");
        }
        
        if (person1.equals("string")) {
            throw new AssertionError("Comparison with different type should return false");
        }
    }
}