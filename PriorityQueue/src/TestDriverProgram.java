import data_structures.BinaryHeapPriorityQueue;
import data_structures.OrderedArrayPriorityQueue;
import data_structures.UnorderedArrayPriorityQueue;
import data_structures.PriorityQueue;

import java.util.Arrays;

public class TestDriverProgram {
    private final PriorityQueue<Integer> orderedAPQ;
    private final PriorityQueue<String> orderedDefAPQ;
    private final PriorityQueue<Integer> unorderedAPQ;
    private final PriorityQueue<String> unorderedDefAPQ;
    private final PriorityQueue<Integer> binaryHeapPQ;
    private final PriorityQueue<String> binaryHeapDefPQ;

    private final int iSize = 10;

    // this array is used for default constructor tests
    String[] mostVisitedCities = {"Hong Kong", "BangKok", "London", "Macau", "Singapore", "Paris", "Dubai",
            "New York", "Kuala Lumpur","Istanbul","Delhi","Antalya", "Shenzhen","Mumbai","Phuket",
            "Rome", "Tokyo", "Pattaya","Taipei","Mecca"};

    // this array is used for parameterized constructor tests
    int[] mostCommonNumbers = {1,23,8,12,3,4,21,22,10,2};

    public TestDriverProgram(){
        orderedAPQ = new OrderedArrayPriorityQueue<>(iSize);
        orderedDefAPQ = new OrderedArrayPriorityQueue<>();
        unorderedAPQ = new UnorderedArrayPriorityQueue<>(iSize);
        unorderedDefAPQ = new UnorderedArrayPriorityQueue<>();
        binaryHeapPQ = new BinaryHeapPriorityQueue<>(iSize);
        binaryHeapDefPQ = new BinaryHeapPriorityQueue<>();
        runTests();
    }

    private void runTests() {
        System.out.println("-------------------------------------------");
        System.out.println("---------- orderedArrayPQ TESTS -----------");
        System.out.println("-------------------------------------------");
        testInsertOrderedAPQ();
        testFillOrderedAPQ();
        testPeekOrderedAPQ();
        testRemoveOrderedAPQ();
        testDeleteOrderedAPQ();
        testClearOrderedAPQ();
        testDefaultConstructorOrderedAPQ();

        System.out.println("-------------------------------------------");
        System.out.println("--------- UnorderedArrayPQ TESTS ----------");
        System.out.println("-------------------------------------------");
        testInsertUnorderedAPQ();
        testFillUnorderedAPQ();
        testPeekUnorderedAPQ();
        testRemoveUnorderedAPQ();
        testDeleteUnorderedAPQ();
        testClearUnorderedAPQ();
        testDefaultConstructorUnorderedAPQ();

        System.out.println("-------------------------------------------");
        System.out.println("----------- BinaryHeapPQ TESTS ------------");
        System.out.println("-------------------------------------------");
        testInsertBinaryHeapPQ();
        testFillBinaryHeapPQ();
        testPeekBinaryHeapPQ();
        testRemoveBinaryHeapPQ();
        testDeleteBinaryHeapPQ();
        testClearBinaryHeapPQ();
        testDefaultConstructorBinaryHeapPQ();

        System.out.println("------------ TESTING COMPLETE -------------");
    }

    private void testDeleteBinaryHeapPQ() {
        System.out.println("------ TESTING: delete(E obj) -----");
        try{
            int[] copy;
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            System.out.print("Inserting from left to right: ");
            for(int i = 0; i < copy.length; i++){
                if(!binaryHeapPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> insert in BinaryHeapPQ not working correctly");
                System.out.print(copy[i] +" ");
            }
            System.out.println("\nApplying delete(3)");
            if(!binaryHeapPQ.delete(3))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            System.out.println("\nApplying delete(12)");
            if(!binaryHeapPQ.delete(12))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            System.out.println("\nApplying delete(1)");
            if(!binaryHeapPQ.delete(1))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            if(binaryHeapPQ.contains(3))
                throw new RuntimeException("FAILED -> the list should not contain '3' anymore");

            if(binaryHeapPQ.contains(12))
                throw new RuntimeException("FAILED -> the list should not contain '12' anymore");

            if(binaryHeapPQ.contains(1))
                throw new RuntimeException("FAILED -> the list should not contain '1' anymore");

            System.out.print("Returned: ");
            for(Integer num : binaryHeapPQ)
                System.out.print(num +" ");
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testRemoveBinaryHeapPQ() {
        System.out.println("------ TESTING: remove(E obj) -----");
        int[] copy = Arrays.copyOf(mostCommonNumbers, iSize);
        Arrays.sort(copy);
        try{
            System.out.print("Expecting: ");
            for(int num : copy)
                System.out.print(num +" ");
            System.out.println();

            for(int i = 0; i < copy.length; i++){
                if(binaryHeapPQ.remove() != copy[i])
                    throw new RuntimeException("FAILED -> item not removed correctly");
            }

            if(!binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list is not empty");

        }catch(RuntimeException e){
            System.out.println("e.getMessage()");
        }
    }

    private void testClearBinaryHeapPQ() {
        System.out.println("\n------ TESTING: clear() -----");
        try {
            binaryHeapPQ.clear();
            if (!binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should be empty");

            if(binaryHeapPQ.isFull())
                throw new RuntimeException("FAILED -> list should not be full");

            System.out.println("Expected size: " +0);
            System.out.println("Returned size: " +binaryHeapPQ.size());
            if(binaryHeapPQ.size() != 0){
                throw new RuntimeException("FAILED -> list should be of size " +0);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testPeekBinaryHeapPQ() {
        System.out.println("\n------ TESTING: peek() -----");
        try {
            if(binaryHeapPQ.peek() != 1)
                throw new RuntimeException("FAILED -> error in peek method");
        }catch (Exception e){
            System.out.println();
        }
    }

    private void testFillBinaryHeapPQ() {
        System.out.println("\n------ TESTING: isEmpty() -----");
        try {
            if (binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should not be empty");

            System.out.println("\n------ TESTING: isFull() -----");
            if(!binaryHeapPQ.isFull())
                throw new RuntimeException("FAILED -> list should be full");

            System.out.println("\n------ TESTING: size() -----");
            System.out.println("Expected size: " +iSize);
            System.out.println("Returned size: " +binaryHeapPQ.size());
            if(binaryHeapPQ.size() != iSize){
                throw new RuntimeException("FAILED -> list should be of size " +iSize);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testInsertBinaryHeapPQ() {
        System.out.println("\n------ TESTING: insert(E obj) -----");
        try{
            int[] copy;
            int[] heap = {1,2,4,10,3,8,21,23,22,12};
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            System.out.print("Inserting from left to right: ");
            for(int i = 0; i < copy.length; i++){
                if(!binaryHeapPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> insert in BinaryHeapPQ not working correctly");
                System.out.print(copy[i] +" ");
            }

            System.out.print("\nExpected: ");
            for(int num : heap)
                System.out.print(num +" ");

            System.out.print("\nReturned: ");
            for(Integer num : binaryHeapPQ)
                System.out.print(num +" ");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private void testClearUnorderedAPQ() {
        System.out.println("\n------ TESTING: clear() -----");
        try {
            unorderedAPQ.clear();
            if (!unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should be empty");

            if(unorderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should not be full");

            System.out.println("Expected size: " +0);
            System.out.println("Returned size: " +unorderedAPQ.size());
            if(unorderedAPQ.size() != 0){
                throw new RuntimeException("FAILED -> list should be of size " +0);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testDeleteUnorderedAPQ() {
        System.out.println("------ TESTING: delete(E obj) -----");
        try{
            System.out.print("Array: ");
            for(int i = 0; i < iSize; i++){
                if(!unorderedAPQ.insert(i%2)){
                    throw new RuntimeException("FAILED -> failed to add value to list");
                }
                System.out.print(i%2 +" ");
            }
            System.out.println("\nApplying delete(0)");
            unorderedAPQ.delete(0);

            System.out.print("Expected: ");
            for(int i =0; i < 5; i++) {
                System.out.print("1 ");
            }
            System.out.println();

            if(unorderedAPQ.contains(0))
                throw new RuntimeException("FAILED -> list should not contain '0' anymore");

            System.out.print("Returned: ");
            for(Integer num : unorderedAPQ){
                System.out.print(num +" ");
            }
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testPeekUnorderedAPQ() {
        System.out.println("\n------ TESTING: peek() -----");
        try {
            if(unorderedAPQ.peek() != mostCommonNumbers[0])
                throw new RuntimeException("FAILED -> error in peek method");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testFillUnorderedAPQ() {
        System.out.println("\n------ TESTING: isEmpty() -----");
        try {
            if (unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should not be empty");

            System.out.println("\n------ TESTING: isFull() -----");
            if(!unorderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should be full");

            System.out.println("\n------ TESTING: size() -----");
            System.out.println("Expected size: " +iSize);
            System.out.println("Returned size: " +unorderedAPQ.size());
            if(unorderedAPQ.size() != iSize){
                throw new RuntimeException("FAILED -> list should be of size " +iSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testRemoveUnorderedAPQ() {
        System.out.println("\n------ TESTING: remove() -----");
        int[] copy;
        copy = Arrays.copyOf(mostCommonNumbers,iSize);
        Arrays.sort(copy);
        System.out.println("Values expected to be removed in this order");
        System.out.print("Expected: ");
        for(Integer num : copy)
            System.out.print(num +" ");
        System.out.println();
        try{
            System.out.print("Returned: ");
            for(int i = 0; i < copy.length; i++){
                int valReturned = unorderedAPQ.remove();
                if(valReturned != copy[i])
                    throw new RuntimeException("FAILED -> item not removed correctly");
                System.out.print(valReturned +" ");
            }
            System.out.println();
            if(!unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list is not empty");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testInsertUnorderedAPQ() {
        System.out.println("------ TESTING: insert(E obj) -----");
        try{
            int[] copy;
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            for(int i = 0; i < copy.length; i++){
                if(!unorderedAPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> failed to add value to list");
            }

            System.out.print("Expected: ");
            for(int i = 0; i < copy.length; i++)
                System.out.print(copy[i] +" ");

            System.out.print("\nReturned: ");
            for(Integer num : unorderedAPQ)
                System.out.print(num +" ");

        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testClearOrderedAPQ() {
        System.out.println("\n------ TESTING: clear() -----");
        try {
            orderedAPQ.clear();
            if (!orderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should be empty");

            if(orderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should not be full");

            System.out.println("Expected size: " +0);
            System.out.println("Returned size: " +orderedAPQ.size());
            if(orderedAPQ.size() != 0)
                throw new RuntimeException("FAILED -> list should be of size " +0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void testPeekOrderedAPQ() {
        System.out.println("\n------ TESTING: peek() -----");
        try {
            if (orderedAPQ.peek() != 1)
                throw new RuntimeException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void testFillOrderedAPQ() {
        System.out.println("\n------ TESTING: isEmpty() -----");
        try {
            if (orderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should not be empty");

            System.out.println("\n------ TESTING: isFull() -----");
            if(!orderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should be full");

            if(!orderedAPQ.contains(12))
                throw new RuntimeException(("FAILED -> contains(E obj) not working correctly"));

            System.out.println("\n------ TESTING: size() -----");
            System.out.println("Expected size: " +iSize);
            System.out.println("Returned size: " +orderedAPQ.size());
            if(orderedAPQ.size() != iSize)
                throw new RuntimeException("FAILED -> list should be of size " +iSize);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testDeleteOrderedAPQ() {
        System.out.println("------ TESTING: delete(E obj) -----");
        try{
            System.out.print("Array: ");
            for(int i = iSize ; i > 0; i--){
                if(!orderedAPQ.insert(i%2))
                    throw new RuntimeException("FAILED -> failed to add value to list");

                System.out.print(i%2 +" ");
            }
            System.out.println("\nApplying delete(0)");
            orderedAPQ.delete(0); // remove all '0' values from the array

            System.out.print("Expected: ");
            for(int i =0; i < 5; i++) {
                System.out.print("1 ");
            }

            System.out.print("\nReturned: ");
            for(Integer num : orderedAPQ)
                System.out.print(num +" ");
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testRemoveOrderedAPQ() {
        int[] copy;
        copy = Arrays.copyOf(mostCommonNumbers,iSize);
        Arrays.sort(copy);
        System.out.println("------ TESTING: remove(E obj) -----");
        try{
            System.out.println("Values expected to be removed in this order");
            System.out.print("Expected: ");
            for(int i : copy)
                System.out.print(i+" ");

            System.out.print("\nReturned: ");
            for (int j : copy) {
                int valRemoved = orderedAPQ.remove();
                if (valRemoved != j)
                    throw new RuntimeException("FAILED -> item not removed correctly");
                System.out.print(valRemoved +" ");
            }
            System.out.println();
            if(!orderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list is not empty");
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void testInsertOrderedAPQ() {
        System.out.println("------ TESTING: insert(E obj) -----");
        int[] copy;
        copy = Arrays.copyOf(mostCommonNumbers,iSize);
        try{
            for(int i = 0 ; i < copy.length; i++){
                if(!orderedAPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> failed to add value to list");
            }
            Arrays.sort(copy);
            System.out.print("Expected: ");
            for(int i = 0; i < copy.length; i++){
                System.out.print( copy[i] +" ");
            }
            int i = 0;
            System.out.print("\nReturned: ");
            for(Integer num : orderedAPQ){
                if(num != copy[i])
                    throw new RuntimeException("FAILED -> values don't match the order");
                System.out.print(num +" ");
                i++;
            }
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testDefaultConstructorBinaryHeapPQ() {
        System.out.println("------ TESTING: default constructor -----");
        try{
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!binaryHeapDefPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            for(String city: binaryHeapDefPQ)
                System.out.print(city +" ,");

            System.out.println("\nPerforming: contains(\"Tokyo\")");
            if(!binaryHeapDefPQ.contains("Tokyo"))
                throw new RuntimeException("FAILED -> error in contains method");

            System.out.println("Performing remove()");
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(binaryHeapDefPQ.remove() != mostVisitedCities[i])
                    throw new RuntimeException("FAILED -> Default constructor instance not removing correctly");

            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!binaryHeapDefPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            binaryHeapPQ.clear();
            if(binaryHeapPQ.size() != 0)
                throw new RuntimeException("FAILED -> Size using default constructor should be 0 ");
        }catch(Exception e){
            e.getMessage();
        }
    }

    private void testDefaultConstructorUnorderedAPQ() {
        System.out.println("------ TESTING: default constructor -----");
        try{
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!unorderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            for(String city: unorderedDefAPQ)
                System.out.print(city +" ,");
            System.out.println("\nPerforming: contains(\"Tokyo\")");
            if(!unorderedDefAPQ.contains("Tokyo"))
                throw new RuntimeException("FAILED -> error in contains method");

            System.out.println("Performing remove()");
            for(int i = 0; i < mostVisitedCities.length;i++){
                unorderedDefAPQ.remove();
            }

            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!unorderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            unorderedDefAPQ.clear();
            if(unorderedDefAPQ.size() != 0)
                throw new RuntimeException("FAILED -> Size using default constructor should be 0 ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void testDefaultConstructorOrderedAPQ() {

        System.out.println("------ TESTING: default constructor -----");
        try{
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!orderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            for(String city: orderedDefAPQ)
                System.out.print(city +" ,");

            System.out.println("\nPerforming: contains(\"Tokyo\")");
            if(!orderedDefAPQ.contains("Tokyo"))
                throw new RuntimeException("FAILED -> error in contains method");

            System.out.println("Performing: remove()");
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(orderedDefAPQ.remove() != mostVisitedCities[i])
                    throw new RuntimeException("FAILED -> Default constructor instance not removing correctly");


            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!orderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");


            orderedDefAPQ.clear();
            if(orderedDefAPQ.size() != 0)
                throw new RuntimeException("FAILED -> Size using default constructor should be 0 ");
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new TestDriverProgram();
    }
}

