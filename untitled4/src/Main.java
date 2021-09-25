import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static File DNA2RNA (String DNAFile, String RNAFile)
            throws FileNotFoundException {
        //creates files and ways to open and write to them
        File DNA = new File(DNAFile);
        Scanner open = new Scanner(DNA);
        File RNA = new File(RNAFile);
        PrintWriter convert = new PrintWriter(RNA);
        while (open.hasNextInt()){
            int message = open.nextInt();
            //runs through each line and checks if certain letters are found
            //and then are replaced when writing the the RNA File
            for (int i = 0; i < message.length(); i++) {
                char c = message.charAt(i);
                if(c=='T') {
                    convert.print('A');
                }
                else if(c=='A'){
                    convert.print('U');
                }
                else if(c=='C'){
                    convert.print('G');
                }
                else if(c=='G'){
                    convert.print('C');
                }
                else{
                    throw new NoSuchElementException("Not a DNA character");
                }
            }
            //add new line
            convert.println();
        }
        //end and close files
        convert.flush();
        convert.close();
        return RNA;
    }
    public static void main(String[] args) {
	// write your code here
    }
}
