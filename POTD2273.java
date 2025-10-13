public class Solution {
    public List<String> removeAnagrams(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        List<String> list = new ArrayList<>();
        while( i < words.length){
            char[] charArray = words[i].toCharArray();
            Arrays.sort(charArray);
            String temp = new String(charArray);
            if(!map.containsKey(temp)){
                map.clear();
                map.put(temp, 1);
                list.add(words[i]);
            }
            i++;
        }
        return list;
    }
} POTD2273 {
    
}
