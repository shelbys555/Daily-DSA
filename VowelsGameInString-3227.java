import java.util.stream.IntStream;

class Solution {
    public boolean doesAliceWin(String s) {
        int vowelCount =(int) s.toLowerCase()
            .chars()
            .filter(c -> "aeiou".indexOf(c) != -1)
            .count();
        if(vowelCount == 0)
            return false;
        else if(vowelCount % 2 != 0)
            return true;

        return true;
    }

// This was my solution but then after checking online I also found an one liner solution for this problem


    public boolean aliceWin(String s){
        return s.chars().anyMatch(c -> "aeiou".indexOf(c) != -1);
    }

}