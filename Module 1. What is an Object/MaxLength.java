import java.util.*;

public class MaxLength {
    public static void main(String[] args) {
        ArrayList<String> listOfStrings = new ArrayList<String>(Arrays.asList("String", "One more"));
        System.out.println(maxLength(listOfStrings));
    }

    public static int maxLength(ArrayList<String> list) {
        if (list.size() == 0) {
            return 0;
        }
        int maxLen = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() >= maxLen) {
                maxLen = list.get(i).length();
            }
        }
        return maxLen;
    }
}
