import org.junit.*;
import static org.junit.Assert.*;

/**
 * WordFrequencyTest class used to test WordFrequency.
 *
 * @author Kaitlyn Brunhaver
 */
public class WordFrequencyTest {
    // Object to be tested
    private WordFrequency runner;

    /**
     * setup() method, runs before each test method below.
     * initializes a new WordFrequency object
     */
    @Before
    public void setUp()  {
        runner = new WordFrequency("Hello");
    }

    /**
     * wordFrequencyConstTest().
     * tests that WordFrequency initializes with the correct word and count.
     */
    @Test
    public void wordFrequencyConstTest() {
        assertEquals("Constructor does not work as expected", "Hello", runner.getWord());
        assertEquals("Count is not what is expected", 1, runner.getCount());
    }

    /**
     * getWordTest() tests that it returns the same word WordFrequency was initialised with.
     */
    @Test
    public void getWordTest() {
        assertEquals("getWord does not work as expected", "Hello", runner.getWord());
    }

    /**
     * getCountTest() tests that it returns the same count that WordFrequency was initialized with.
     */
    @Test
    public void getCountTest() {
        assertEquals("getCount does not work as expected", 1, runner.getCount());
    }

    /**
     * incrementCountTest() tests that each time the method is called count increases by one.
     */
    @Test
    public void incrementCountTest() {
        runner.increment();
        runner.increment();
        assertEquals("incrementCount does not work as expected",3 , runner.getCount());
    }

    /**
     * equalsSameObjectTest() tests return true when comparing the same object to itself.
     */
    @Test
    public void equalsSameObjectTest() {
        WordFrequency test = new WordFrequency("test");
        assertFalse(runner.equals(test));
        assertTrue(test.equals(test));
    }

    /**
     * equalsStringTest() tests if returns true.
     * when WordFrequency is compared to the same word contained in it.
     */
    @Test
    public void equalsStringTest() {
        String test = "hello";
        assertTrue(runner.equals(test));
    }

    /**
     * equalsStringObjectTest() test if returns true.
     * when compared to a WordFrequency object containing the same string.
     */
    @Test
    public void equalsStringObjectTest(){
        WordFrequency test = new WordFrequency("hello");
        assertTrue(runner.equals(test));
    }

    /**
     * equalsNotStringObjectTest() test if returns false.
     * when passed a value that is not a String or WordFrequency object.
     */
    @Test
    public void equalsNotStringObjectTest(){
        Integer  test = 1;
        assertFalse(runner.equals(test));
    }

}
