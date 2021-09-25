public class Num3StringBuilder{
    public static void main(String[] args) {
        System.out.println(deletePun("Go, Fuck! yourself'"));
    }
    public String deletePun(String str) {
        StringBuilder strB = new StringBuilder();
        strB.append(str);
        for (int i = strB.length() - 1; i >= 0; i--) {
            if (strB.charAt(i) == '!' || strB.charAt(i) == '\'' || strB.charAt(i) == '.' | strB.charAt(i) == ',' || strB.charAt(i) == '?') {
                strB.deleteCharAt(i);
            }

        }
        return strB.toString();
    }


}
