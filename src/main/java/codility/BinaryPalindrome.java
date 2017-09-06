package codility;

import org.apache.commons.lang3.StringUtils;

public class BinaryPalindrome {
    public static void main(String[] args) {
        // 1 11, 101 111 1001 1111, 10001 10101 11011 11111 100001 101101 110011
        int n = 20;
        String[] arr = new String[n];
        arr[0] = "1";
        arr[1] = "11";
        arr[2] = "101";
        arr[3] = "111";
        int exp = 1;
        int idx = 0;
        boolean secondTime = false;
        for (int i = 4; i < n; i++) {
            String number = String.format("%" + String.valueOf(exp ) + "s", Integer.toString(idx++,2)).replace(' ','0');
            if (secondTime) { // odd
                String reverse = StringUtils.reverse(number);
                number = number.substring(0, number.length() - 2) + reverse;
            } else { // even
                number = number.concat(number);
            }
            arr[i] = "1" + number + '1';
            if (idx >= (2 << exp)-1) {
                idx = 0;
                if (secondTime) {
                    exp++;
                    secondTime = false;
                } else {
                    secondTime = true;
                }
            }
            System.out.println(arr[i]);
        }
    }
}
