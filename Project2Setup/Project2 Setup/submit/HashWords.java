import itsc2214.*;

/**
 * HashWords class to be used in Project2.
 *
 * @author Kaitlyn Brunhaver
 */
public class HashWords {
    private WordFrequency[] dataSet;
    private int uniqueWords = 0;
    private int totalWords = 0;

    /**
     * The constructor for HashWords that initialises a HashTable.
     *
     * @param initialSize - the int value of the initial size of the array.
     */
    public HashWords(int initialSize) {
        this.dataSet = new WordFrequency[initialSize];
    }

    /**
     * Computes the size of the Array of WordFrequency objects.
     *
     * @return int value the length of the Array.
     */
    public int size() {
        return this.dataSet.length;
    }

    /**
     * Takes a string input and computes a hashKey.
     * It does this by taking the sum of all the ASCII values
     * not case-sensitive from the param modulo the size of the Array.
     *
     * @param w - the String being hashed
     * @return sum of ASCII value modulo Array size.
     */
    public int hashKey(String w) {
        int sum = 0;
        for (int i = 0; i < w.length(); i++) {
            sum += (int) w.toLowerCase().charAt(i);
        }
        return sum % size();
    }

    /**
     * Gets the value of count contained in WordFrequency object.
     * Value is now many times the word is added to the array.
     *
     * @param w - the String we want to find the frequency of.
     * @return 0 if the word is not in Array, returns the count if word is contained.
     */
    public int frequency(String w) {
        if(contains(w)) {
            int index = hashKey(w);
            int wordIndex = index;
            while(!dataSet[index].equals(w)) {
                index = (index + 1) % size();
            }
            return dataSet[index].getCount();

        }
        return 0;
    }

    /**
     * adds word to array by using hashkey.
     * if position is not empty uses linear search
     * triples size of array if there are no more empty values in array
     * rehashes all the values contained in the array
     *
     * @param w - this word is added to the array
     */
    public void addWord(String w) {
        if(w == null || w.isEmpty()) {
            return;
        }
        int index = hashKey(w);

        if(contains(w)){
            while(!dataSet[index].equals(w)){
                index = (index + 1) % size();
            }
            dataSet[index].increment();
            totalWords++;
            return;
        }
        else if (uniqueWords < size()) {
            while(dataSet[index] != null){
                index = (index + 1) % size();
            }
            dataSet[index] = new WordFrequency(w);
            uniqueWords++;
            totalWords++;
        }
        else{
            int newDataSetSize = size() * 3;
            WordFrequency[] oldDataSet = this.dataSet;
            this.dataSet = new WordFrequency[newDataSetSize];

            for (WordFrequency wordFr : oldDataSet) {
                index = hashKey(wordFr.getWord());
                while (dataSet[index] != null) {
                    index = (index + 1) % size();
                }
                this.dataSet[index] = wordFr;
            }
            index = hashKey(w);
            while (dataSet[index] != null) {
                index = (index + 1) % size();
            }
            dataSet[index] = new WordFrequency(w);
            uniqueWords++;
            totalWords++;
        }


    }

    /**
     * Checks if the array already contains the word.
     *
     * @param w - the String we are checking if in the Array.
     * @return true if the word is in the Array, false if not or if
     * string being passed is null
     */
    public boolean contains(String w) {
        if(w==null) return false;
        int index = hashKey(w);
        int wordIndex = index;
        while (dataSet[index] != null) {
            if (dataSet[index].equals(w)) {
                return true;
            }
            index = (index + 1) % size();
            if(index == wordIndex) {
                return false;
            }
        }

        return false;
    }

    /**
     * Returns the count for how many new words are added to the Array.
     * These are values that are not duplicates.
     *
     * @return the int value of the number of new words added
     */
    public int numUniqueWordsInTable() {
        return this.uniqueWords;
    }

    /**
     * Returns the count for how many total words are added to the Array.
     * This is including duplicate values.
     *
     * @return the int value of the total number of words added to Array.
     */
    public int totalNumOfWords() {
        return this.totalWords;
    }

    /**
     * This returns the string that has the highest frequency.
     *
     * @return the String with the highest frequency, if Array is Null
     * or empty it returns null.
     */
    public String mostCommonWord() {
        String mostCommonWord = "";
        int largestFrequency = 0;

        for(WordFrequency wordAndCount : this.dataSet) {
            if(wordAndCount != null) {
                if(wordAndCount.getCount() > largestFrequency) {
                    largestFrequency = wordAndCount.getCount();
                    mostCommonWord = wordAndCount.getWord();
                }
            }
        }
        if (largestFrequency > 0) {
            return mostCommonWord;
        } else {
            return null;
        }
    }

    /**
     * Returns the uniqueness of the word.
     * This is done by dividing the frequency of the word by the total
     * number of words.
     *
     * @param w - the String that we want to find the term frequency of.
     * @return the double which is the quotient of the frequency of the word
     * divided by the total number of words. If the total number of words is 0
     * then 0 is returned.
     */
    public double termFrequency(String w) {
        if(totalNumOfWords() == 0) {
            return 0;
        }
        return (double) frequency(w)/totalNumOfWords();
    }
}
