public class Strings {

    private static void recursion(String letters, String print) {
        if (letters.length() ==  0) {
            System.out.println(print);
            return;
        }

        for (int i = 0; i < letters.length(); ++i) {
            char c = letters.charAt(i);
            String restStr = letters.substring(0, i) + letters.substring(i + 1);
            recursion(restStr, print + c);
        }
    }

    public static void main(String[] args) {
        recursion("catdog","");
    }
}
