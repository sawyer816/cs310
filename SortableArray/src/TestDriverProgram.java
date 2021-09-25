import data_structures.SortableArray;
import data_structures.SortableArrayInterface;

/**
 * This class contains the test cases for the SortableArray.java file
 *
 * IMPORTANT!
 * DO NOT EDIT ANY OF THE TESTS BELOW!
 *
 */
public class TestDriverProgram {
    private SortableArrayInterface iTestList; // declare a list of type SortableArrayInterface
    private SortableArrayInterface iTestFileList; // declare a list of type SortableArrayInterface
    private int iSize = 20; // declare and initialize the size for the list
    private String sFile = "UnsortedNumberFile.txt"; // this is the file we will use for testing

    public TestDriverProgram(){
        iTestList = new SortableArray(iSize);   // tests if parameterized constructor works
        iTestFileList = new SortableArray();    // tests if default constructor works
        runAllTests();  // run all of the tests
    }

    /**
     * This method contains all the tests to perform
     * on the SortableArray.java file
     */
    private void runAllTests(){
        System.out.println("------ RUNNING TESTS ------\n");
        testAdd(); // call tests for add(int element)
        testGet(); // tests if the values were inserted correctly
        testSize(); // call tests for size()
        testRemove(); // call tests for remove(int index)
        testAddAtIndex(); // call tests for add(int index, int element)

        // This code below will test that the program can read the file
        // and store the values into an array. This array will then be sorted
        // by the insertionSort and it should write the sorted data back into the file

        testReadFile(); // call tests for readFile(String filename)
        testInsertionSort(); // call tests for insertionSort()
        testSaveFile(); // call tests for saveFile(String filename)
        System.out.println("\n----- TESTING COMPLETE ----- ");
    }

    /**
     * This function will test saveFile(String filename)
     * Will return true if the file was written to and saved.
     */
    private void testSaveFile() {
        System.out.println("------ TESTING : saveFile(String filename) ------");
        try{
            if(!iTestFileList.saveFile(sFile)) {
                throw new RuntimeException("FAILED -> saveFile(String filename) not working correctly");
            }
        }catch (RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    /**
     * This function will test readFile(String filename)
     * Will return true if the file was parsed and contents are
     * stored in data structure.
     */
    private void testReadFile() {
        System.out.println("------ TESTING : readFile(String filename) ------");
        try{
            if(iTestFileList.readFile(sFile)){
                //throw exception if the values are not copied correctly
                if(iTestFileList.get(0) != 42175){
                    throw new RuntimeException("FAILED -> readFile(String filename) not working correctly");
                }
                if(iTestFileList.get(5) != 45545){
                    throw new RuntimeException("FAILED -> readFile(String filename) not working correctly");
                }
                if(iTestFileList.get(10) != 86908) {
                    throw new RuntimeException("FAILED -> readFile(String filename) not working correctly");
                }
            }
            else{
                throw new RuntimeException("FAILED -> readFile(String filename) not working correctly");
            }
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
    }

    /**
     * This function will test the insertion sort algorithm.
     */
    private void testInsertionSort() {
        System.out.println("------ TESTING : insertionSort() ------");
        try{
            iTestFileList.insertionSort(); // calling insertion sort function
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test case for: get(int index)
     * The get function should return all the values that were added to the list
     * in the test case the sequence is 0,1,....,iSize-1
     */
    private void testGet() {
        System.out.println("------ TESTING: get(int index) ------");
        System.out.println("Expected: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ");
        System.out.print("Returned: ");
        try{
            for(int i = 0; i < iSize; i++) {
                if (iTestList.get(i) != i)
                    throw new RuntimeException("FAILED -> get value is not working correctly");
                else
                    System.out.print(iTestList.get(i) + " ");
            }
            // test to see if we can get values out of bounds
            iTestList.get(-9);
            iTestList.get(iSize+10);
        }catch (RuntimeException e){
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
    }

    /**
     * Test case for: remove(int index)
     * This function will remove all the values form the list
     *
     * We will be removing from index 0, so if we have the array
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 after the first
     * iteration we should have removed the value 0. The list should now look like
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19.
     *
     * Then the next value will be deleted from index 0, which is the value 1.
     * The list should now look like this.
     * 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19.
     * ... and so on and so forth
     */
    private void testRemove() {
        System.out.println("------ TESTING: remove(int index) -----");
        try{
            for(int i = 0; i < iSize; i++){
                if(iTestList.remove(0) != i)
                    throw new RuntimeException("FAILED -> remove not working correctly");
            }

            iTestList.remove(-9);
            iTestList.remove(iSize+10);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test case for: size()
     * This function will test if the value passed into the constructor is
     * equal to the size of the list
     */
    private void testSize() {
        System.out.println("------ TESTING: size() ----- ");
        System.out.println("Expected: " +iSize);
        System.out.print("Returned: ");
        try{
            if(iTestList.size() != iSize)
                throw new RuntimeException("FAILED -> test size not matching");
            else
                System.out.print(iTestList.size());
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
        System.out.print("\n");
    }

    /**
     * This function will add values to the front of the array.
     * Doing this will test the worst case for inserting into the array
     * since each insertion will require all values in the array to be shifted
     *
     * This should start with the list
     * 0
     *
     * then the next item it adds will be 1. The list now looks like
     * 1 0
     *
     * We will repeat this process until we have all items in the list.
     * The final list should look like this:
     * 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0
     */
    private void testAddAtIndex(){
        System.out.println("------ TESTING : add(int index, int element) ------");
        try{
            // The insertion will add values to the front
            // the sequence should be iSize-1....,1,0
            for(int i = 0; i < iSize ; i++){
                if(!iTestList.add(0,i))
                    throw new RuntimeException("FAILED -> add(index,element) not working correctly");
            }
            // we should test the out of edge cases
            // if we try the following code and the return value is true or if the
            // program crashes then there is a problem
            if(iTestList.add(iSize+10, 999 )){
                throw new RuntimeException("FAILED -> index should not be > SIZE");
            }
            if(iTestList.add(-2,999 )){
                throw new RuntimeException("FAILED -> index should not be < 0");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test case for: add(int element)
     * in the test case the sequence is 0,1,....,iSize-1
     */
    private void testAdd(){
        System.out.println("------ TESTING: add(int element) -----");
        try{
            // add values to list, the sequence is 0,1,....,iSize-1
            for(int i = 0; i < iSize; i++) {
                // if the add method returns false the program did not work correctly
                if (!iTestList.add(i))
                    throw new RuntimeException("FAILED -> failed to add value to list");
            }
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new TestDriverProgram(); // create a new instance of the TestDriverProgram
    }
}
