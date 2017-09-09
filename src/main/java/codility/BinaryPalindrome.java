package codility;

public class BinaryPalindrome {
    public static void main(String[] args) {
        int n = 100;
        String[] arr = new String[n];
        arr[0] = "1";
        arr[1] = "11";
        int exp = 0;
        int idx = 0;
        boolean secondTime = true;
        for (int i = 2; i < n; i++) {
            String number = String.format("1%" + String.valueOf(exp+1) + "s", Integer.toString(idx,2)).replace(' ','0');
            idx++;
            String reverse = new StringBuilder(number).reverse().toString();
            if (secondTime) { // odd
                number = number.substring(0, number.length() - 1) + reverse;
            } else { // even
                number += reverse;
            }
            arr[i] = number;
            if (idx >= (2 << exp)) {
                idx = 0;
                if (secondTime) {
                    secondTime = false;
                } else {
                    exp++;
                    secondTime = true;
                }
            }
            System.out.println(arr[i]+" "+Integer.valueOf(arr[i],2));
        }
    }
}
/*
1
11
101
111
1001
1111
10001
10101
11010
11111
100001
101101
110011
111111
1000001
1001001
1010101
1011101
1100011
1101011
1110111
1111111
10000001
10011001
 */