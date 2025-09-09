public class Card {
    private String term;
    private String definition;
    
    public Card(String term, String definition) {
        if (term == null || term.isEmpty() || definition == null || definition.isEmpty()) {
            throw new IllegalArgumentException("Term and definition cannot be null or empty");
        }
        
        this.term = term;
        this.definition = definition;
    }
    
    @Override
    public String toString() {
        return definition;
    }
    
    public boolean attempt(String response) {
        if (response == null) return false;
        return response.equalsIgnoreCase(term);
    }
    
    public String getTerm() {
        return term;
    }
}
