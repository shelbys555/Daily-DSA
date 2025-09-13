import java.util.HashMap;
import java.util.Map;

public class MostFreqVowelCons3541 {
    public int maxFreqSum(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            if(freqMap.containsKey(s.charAt(i))){
                freqMap.put(s.charAt(i), freqMap.get(s.charAt(i)) + 1);
            }else{
                freqMap.put(s.charAt(i), 1);
            }
        }

        int vow = 0, cons = 0;
        
        String vowel = "aeiou";
        for (int i = 0; i < s.length(); i++){
            if(vowel.contains(String.valueOf(s.charAt(i)))){
                vow = Math.max(vow, freqMap.get(s.charAt(i)));
            }else{
                cons = Math.max(cons, freqMap.get(s.charAt(i)));
            }
        }

        return vow + cons;

//Second Approach

        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        int vow = 0, cons = 0;
        String vowel = "aeiou";
        for(int i = 0; i < 26; i++){
            char c = (char) (i + 'a');
            if(vowel.indexOf(c) != -1){
                vow = Math.max(vow, freq[i]);
            }else{
                cons = Math.max(cons, freq[i]);
            }
        }

        return vow + cons;
    }
}
