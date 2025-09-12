

class Solution {
    public int reverse(int x) {
        int revNo = 0;
        int lastDigit = 0;
        while (x != 0){
            lastDigit = x % 10;
            if(revNo > Integer.MAX_VALUE/10 || revNo < Integer.MIN_VALUE/10) {
                return 0;
            }
            revNo = (revNo * 10) + lastDigit;
            x /= 10;
        }
        return revNo;
    }
}