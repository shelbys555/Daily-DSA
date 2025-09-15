import java.util.HashSet;
import java.util.Set;

public class POTD1935 {

    public int canBeTypedWords(String text, String brokenLetters) {
        String[] wordlist = text.split(" ");
        Set<Character> charSet = new HashSet<>();
        for(int i = 0; i < brokenLetters.length(); i++){
            charSet.add(brokenLetters.charAt(i));
        }
        int wordCount = 0;

        for(String s: wordlist){
            for(int i = 0; i < s.length(); i++){
                if(charSet.contains(s.charAt(i))){
                    wordCount++;
                    break;
                }
            }
        }
        // for(String s : wordlist){
        //     for(int i = 0; i < brokenLetters.length(); i++){
        //         if(s.indexOf(brokenLetters.charAt(i)) != -1){
        //             wordCount++;
        //             break;
        //         }
        //     }
        // }
        return wordlist.length - wordCount;
    }

}
