package DSA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Solution {
    public String sortVowels(String s) {
        List<Character> characterList = new ArrayList<>();
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < s.length(); i++){
            if(vowels.contains(String.valueOf(s.charAt(i)))){
                characterList.add(s.charAt(i));
            }
        }
        Collections.sort(characterList);
        StringBuilder str = new StringBuilder(s);
        int cnt = 0;
        for (int i = 0; i < str.length(); i++){
            if(vowels.contains(String.valueOf(str.charAt(i)))){
                str.setCharAt(i, characterList.get(cnt++));
            }
        }

        return str.toString();
    }
}