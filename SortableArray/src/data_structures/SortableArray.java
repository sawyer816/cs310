/**
 * Program 1 - Sortable Array
 * This program implements the SortableArrayInterface. Then has methods to
 * edit arrays, sort arrays, read arrays in a file, and store arrays in a file.
 * @author Sawyer Thompson
 * @date 02/26/21
 * CS 310 Section 2
 */

package data_structures;
import java.io.*;
import java.util.NoSuchElementException;

public class SortableArray implements SortableArrayInterface {

/* Instance variables */
    private int[] array;
    private int numElements;

    /* Constructor given no parameter. */
    public SortableArray(){
        array = new int[DEFAULT_MAX_CAPACITY];
    }

    /* Constructor given capacity parameter*/
    public SortableArray(int capacity){
        array = new int[capacity];
    }

    @Override
    /* adds an element to an array. */
    public boolean add(int element) {
        boolean isAdded = true;
        try {
            array[numElements++] = element;
        }catch (Exception e){
            isAdded = false;
        }
        return isAdded;
    }

    @Override
    /* Adds an element to an array at a specific index. */
    public boolean add(int index, int element) {
        boolean added = true;
        try {
            //moves elements to make room for new element.
                System.arraycopy(array, index, array, index + 1, numElements - index);
            array[index] = element;
            numElements++;
        } catch (Exception e) {
            added = false;
        }
        return added;
    }

    @Override
    /* Get's an element of the array at a specific index. */
    public int get (int index) {
        int elementToReturn = 0;
        try {
            if(index < numElements && index >= 0)
            elementToReturn = array[index];
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
        return elementToReturn;
    }

    @Override
    /* Removes an element of an array at a specific index. */
    public int remove(int index) {
        int old  = 0;
        try {
            if (index >= 0 && index < numElements) {

                old = array[index];
                //shifts elements to the left to fill missing element
                if (size() - 1 - index >= 0)
                    System.arraycopy(array, index + 1, array, index,
                            numElements - 1 - index);
                numElements--;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        //clears array
        if(size() == 0){
            array = new int[DEFAULT_MAX_CAPACITY];
        }
        return old;
    }
    @Override
    /* Returns the number of elements in the array */
    public int size() {
        return numElements;
    }

    @Override
    /* Reads input from a file and puts them into the array.*/
    public boolean readFile(String filename) {
        boolean read = true;
        BufferedReader objReader = null;
        try {
            objReader = new BufferedReader(new FileReader(filename));
            //sets initial value to the first line of file.
            String strCurrentLine = objReader.readLine();
            while ((strCurrentLine != null)) {
                //puts integer into array.
                array[numElements++] = Integer.parseInt(strCurrentLine);
                strCurrentLine = objReader.readLine();
            }

        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            read = false;

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException | NoSuchElementException ex) {
                ex.printStackTrace();
                read = false;
            }
        }
        return read;
    }

    @Override
    /* Puts input from the array into a file and returns true if successful */
    public boolean saveFile(String filename){
        //names files to output.
        filename = "SortedNumberFile.txt";
        boolean saved = true;
        BufferedWriter objSaver;
        try {
            FileWriter out = new FileWriter(filename);
            objSaver = new BufferedWriter(out);
            for (int i = 0; i < numElements; i++) {
                objSaver.write(String.valueOf(array[i]));
                //puts each element onto a new line.
                objSaver.newLine();
            }
            objSaver.close();
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            saved = false;
        }
        return saved;
    }

    @Override
    /* Sorts an array using an insertion sort with O(n^2) complexity.
    * Returns the time in ms for finishing the insertSort  */
    public long insertionSort() {
        long start = System.nanoTime();
        for (int i = 1; i < numElements; ++i) {
            int current = array[i];
            int hold = i - 1;
            // Move elements of array that are greater than key,
            // to one position ahead of their current position
            while (hold >= 0 && array[hold] > current) {
                array[hold + 1] = array[hold];
                hold = hold - 1;
            }
            array[hold + 1] = current;
        }
        long end = System.nanoTime();
        // divides by 1000 to make it into milliseconds.
        return (end - start) / 1000;
    }
}
