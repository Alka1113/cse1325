public class Rating {
    private int stars;
    private Comment review;

    // Constructor that takes Comment review
    public Rating(int stars, Comment review) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }
        this.stars = stars;
        this.review = review;
    }

    public int getStars() {
        return stars;
    }

    public Comment getReview() {
        return review;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            sb.append("★"); // Filled star (
        }
        for (int i = stars; i < 5; i++) {
            sb.append("☆"); // Empty star
        }
        return sb.toString();
    }
}