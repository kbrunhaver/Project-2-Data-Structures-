/**
 * WordFrequency class to be used in a hash table.
 *
 * @author Kaitlyn Brunhaver
 */
public class WordFrequency {
    // declare private variables here
    private String word;
    private int count;

    /**
     * Constructor for WordFrequency object.
     *
     * @param w the w
     */
    public WordFrequency(String w) {
        this.word = w;
        this.count = 1;
    }

    /**
     * Gets the value for the instance of word.
     *
     * @return the String word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Gets the value for the instance of count.
     *
     * @return the int count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Increments the value of count by 1.
     */
    public void increment() {
        //
        count += 1;
    }

    /**
     * equals() - compares two WordFrequency objects
     * checking to see if they are the same. Equality
     * is defined by string matching ignoring case.
     * 
     * @param other object to compare against
     * @return true if this and other are equals, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof String) {
            String w = (String) other;
            return getWord().equalsIgnoreCase(w);
        } else if (other instanceof WordFrequency) {
            WordFrequency wf = (WordFrequency) other;
            String w = wf.getWord();
            return getWord().equalsIgnoreCase(w);
        } else {
            return false;
        }
    }
}
