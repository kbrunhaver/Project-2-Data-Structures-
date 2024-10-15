import org.junit.*;
import static org.junit.Assert.*;

/**
 * HashWordsTest class used to test HashWords.
 *
 * @author Kaitlyn Brunhaver
 */
public class HashWordsTest {
    // Object to be tested
    private HashWords runner;

    /**
     * setup()  initialises a new HashWords array of size 10.
     *
     */
    @Before
    public void setup() {
        runner = new HashWords(10);
    }

    /**
     * hashWordsConstructorTest() tests that the size of the table is initialised correctly.
     */
    @Test
    public void hashWordsConstructorTest() {
        assertEquals("Size of table is wrong",10, runner.size());
    }

    /**
     * sizeTest() tests that the size method returns the correct value.
     */
    @Test
    public void sizeTest() {
        assertEquals("Size method does not work as expected",10, runner.size());
    }

    /**
     * hashKeyTest() tests that returns the correct value not case-sensitive.
     */
    @Test
    public void hashKeyTest() {
        assertEquals(9, runner.hashKey("shrimp"));
        assertEquals(9, runner.hashKey("SHRIMP"));
    }

    /**
     * frequencyTestElementsContained().
     * tests that returns the number of times a word is added
     */
    @Test
    public void frequencyTestElementsContained(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        runner.addWord("rates");
        runner.addWord("stear");
        runner.addWord("blimp");
        runner.addWord("apple");
        runner.addWord("aster");
        runner.addWord("beans");
        runner.addWord("pizza");
        runner.addWord("crackers");

        assertEquals(2,runner.frequency("shrimp"));
        assertEquals(1,runner.frequency("stear"));
    }

    /**
     * frequencyTestElementsNotContainedTest().
     * tests if 0 is returned when called for a word not in the array
     */
    @Test
    public void frequencyTestElementsNotContainedTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        runner.addWord("beans");
        runner.addWord("pizza");
        runner.addWord("far");
        assertEquals(0,runner.frequency("Pie"));
        assertEquals(0,runner.frequency("arf"));
        assertEquals(0,runner.frequency(null));
    }

    /**
     * addWordNullAndEmptyTest() tests if 0 is returned when null or empty value.
     */
    @Test
    public void addWordNullAndEmptyTest(){
        runner.addWord(null);
        runner.addWord("");
        assertEquals(0,runner.totalNumOfWords());
    }

    /**
     * addWordTest() tests that word is added.
     */
    @Test
    public void addWordTest(){
        runner.addWord("shrimp");
        assertEquals(1,runner.totalNumOfWords());
    }

    /**
     * addWordDuplicatesSameIndexTest() tests that words are added.
     * correctly when there is duplicates.
     */
    @Test
    public void addWordDuplicatesSameIndexTest(){
        runner.addWord("bat");
        runner.addWord("tab");
        runner.addWord("abt");
        runner.addWord("bat");
        runner.addWord("tab");
        assertEquals(5,runner.totalNumOfWords());
        assertEquals(3,runner.numUniqueWordsInTable());
        assertEquals(2,runner.frequency("bat"));
        assertEquals(2,runner.frequency("tab"));
    }

    /**
     * addWordRehashTest() tests that when array list is full.
     * it expands its size and rehashes the values correctly.
     */
    @Test
    public void addWordRehashTest(){
        for(int i = 0; i < 6; i++){
            runner.addWord("Val"+i);
        }
        assertEquals(6,runner.numUniqueWordsInTable());
        assertEquals(10,runner.size());


        for(int i = 0; i < 6; i++){
            runner.addWord(i+"Val");
        }
        assertEquals(12,runner.numUniqueWordsInTable());
        assertEquals(30,runner.size());
    }

    /**
     * containsNullTest() tests that returns false when passed a null value.
     */
    @Test
    public void containsNullTest(){
        runner.addWord("stear");
        runner.addWord("blimp");
        assertFalse(runner.contains(null));
    }

    /**
     * containsWordNotInTest() tests that returns false.
     * when passed a value that's not in the array.
     */
    @Test
    public void containsWordNotInTest(){
        assertFalse(runner.contains("not-here"));
    }

    /**
     * containsWordNotInLoopThroughTest() tests that loops through.
     * to make sure word is not in array.
     */
    @Test
    public void containsWordNotInLoopThroughTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        runner.addWord("rates");
        runner.addWord("stear");
        runner.addWord("blimp");
        runner.addWord("apple");
        runner.addWord("aster");
        runner.addWord("beans");
        runner.addWord("pizza");
        runner.addWord("crackers");
        assertFalse(runner.contains("not-here"));
    }

    /**
     * numUniqueWordsInTableTest() tests that returns number of words that are new.
     * this excludes counting their duplicates
     */
    @Test
    public void numUniqueWordsInTableTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        assertEquals(2,runner.numUniqueWordsInTable());
        runner.addWord("rates");
        assertEquals(3,runner.numUniqueWordsInTable());
    }

    /**
     * totalNumOfWordsTest() returns the total number of words added to the array.
     */
    @Test
    public void totalNumOfWordsTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        assertEquals(3,runner.totalNumOfWords());
        runner.addWord("rates");
        assertEquals(4,runner.totalNumOfWords());
    }

    /**
     * mostCommonWordNull() tests that returns null when passed null value.
     */
    @Test
    public void mostCommonWordNull(){
        runner.addWord(null);
        assertNull(runner.mostCommonWord());
    }

    /**
     * mostCommonWordTest() tests that returns word with the highest frequency.
     */
    @Test
    public void mostCommonWordTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        runner.addWord("bear");
        runner.addWord("stare");
        runner.addWord("stare");
        assertEquals("stare", runner.mostCommonWord());
    }

    /**
     * termFrequencyEmptyListTest() tests that 0 is returned if the array is empty.
     */
    @Test
    public void termFrequencyEmptyListTest(){
        assertTrue(0 ==runner.termFrequency("beans"));
    }

    /**
     * termFrequencyTest() tests that values are calculated correctly.
     */
    @Test
    public void termFrequencyTest(){
        runner.addWord("shrimp");
        runner.addWord("SHRIMP");
        runner.addWord("stare");
        assertTrue(2.0/3.0 == runner.termFrequency("shrimp"));
        assertTrue(1.0/3.0 == runner.termFrequency("stare"));
    }

}
