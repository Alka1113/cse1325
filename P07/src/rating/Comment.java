import java.util.ArrayList;

public class Comment {
    private String text;
    private Person author;
    private Comment inReplyTo;
    private ArrayList<Comment> replies;

    public Comment(String text, Person author, Comment inReplyTo) {
        if (text == null || text.isEmpty() || author == null) {
            throw new IllegalArgumentException("Text and author cannot be null, text cannot be empty");
        }
        this.text = text;
        this.author = author;
        this.inReplyTo = inReplyTo;
        this.replies = new ArrayList<>();
    }

    public void addReply(String text, Person author) {
        if (text == null || text.isEmpty() || author == null) {
            throw new IllegalArgumentException("Text and author cannot be null, text cannot be empty");
        }
        Comment reply = new Comment(text, author, this);
        replies.add(reply);
    }

    public int numReplies() {
        return replies.size();
    }

    public Comment getReply(int index) {
        return replies.get(index);
    }

    public Comment getInReplyTo() {
        return inReplyTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comment by ").append(author);
        
        if (inReplyTo != null) {
            sb.append(" in reply to ").append(inReplyTo.author);
        }
        
        sb.append("\n").append(text);
        
        if (!replies.isEmpty()) {
            sb.append("\nReplies:");
            for (int i = 0; i < replies.size(); i++) {
                sb.append(" (").append(i).append(") ").append(replies.get(i).author.getName());
            }
        }
        
        return sb.toString();
    }
}