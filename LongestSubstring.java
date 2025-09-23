class Solution {
    public int lengthOfLongestSubstring(String s) {
        int om = 0, nm = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int i = 0, j = 0;
        while(j < s.length()){
            if(!charMap.containsKey(s.charAt(j))){
                charMap.put(s.charAt(j), j);
                om++;
                j++;
            }else{
                charMap.clear();
                i++;
                j = i;
                nm = Math.max(nm,om);
                om = 1;
                
                charMap.put(s.charAt(j), j);
                j++;
            }
            nm = Math.max(nm,om);
        }
        return nm;
    }
}