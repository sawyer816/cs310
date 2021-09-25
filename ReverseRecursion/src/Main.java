
public class Main {
    public static int binarySearch(int[ ] data, int target, int low, int high) {
        if (low > high) {
            return -1;
        } else {
            int mid = (low + high) / 2;
            if (target == data[mid]) {
                return target;
            }
        else if (target < data[mid]) {
                return binarySearch(data, target, low, mid - 1);
            }
        else {
            return binarySearch(data, target, mid + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] k = {1,2,3,4,5};
        System.out.println(binarySearch(k, 0, 1, 5));
    }
}
