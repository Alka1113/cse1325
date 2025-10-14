package test;
import rating.Rating;
import people.Person;
import rating.Comment;
public class TestRating {
    public static void main(String[] args) {
        testStarRatings();
        testReviewComment();
        System.out.println("All TestRating tests passed!");
    }

    private static void testStarRatings() {
        for (int i = 1; i <= 5; i++) {
            Rating rating = new Rating(i, null);
            if (rating.getStars() != i) {
                throw new AssertionError("Expected " + i + " stars, got " + rating.getStars());
            }
            
            String starString = rating.toString();
            int filledStars = starString.replace("☆", "").length();
            if (filledStars != i) {
                throw new AssertionError("Expected " + i + " filled stars in toString");
            }
        }
    }

    private static void testReviewComment() {
        Person author = new Person("Test Person", "test@email.com");
        Comment review = new Comment("Great tutor!", author, null);
        Rating rating = new Rating(5, review);
        
        if (rating.getReview() != review) {
            throw new AssertionError("Review comment not returned correctly");
        }
    }
}