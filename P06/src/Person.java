import java.util.ArrayList;
import java.util.Objects;

public class Person implements Rateable { 
    private String name;
    private String email;
    private ArrayList<Rating> ratings; 
    
    public Person(String name, String email) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Name and email cannot be null or empty");
        }
        this.name = name;
        this.email = email;
        this.ratings = new ArrayList<>(); 
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    

    @Override
    public void addRating(Rating rating) {
        ratings.add(rating);
    }
    
    @Override
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;  
        for (Rating rating : ratings) {
            sum += rating.getStars(); 
        }
        return sum / ratings.size(); 
    }
    
    @Override
    public Rating[] getRatings() {
        return ratings.toArray(new Rating[0]);  
    }
}