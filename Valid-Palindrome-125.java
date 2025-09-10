package DSA;

class Solution {
    public boolean isPalindrome(String s) {
        String s1 = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        StringBuilder sb = new StringBuilder(s1);
        String rev = sb.reverse().toString();
        return (rev.equals(s1));
    }
}