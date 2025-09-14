import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VowelChecker_POTD{
    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] ans = new String [queries.length];
        Set<String> wordSet = new HashSet<>();
        Map<String, String> caseMap = new HashMap<>();
        Map<String, String> vowelMap = new HashMap<>();
        for(String s : wordlist){
            wordSet.add(s);
            if(!caseMap.containsKey(s.toLowerCase())){
                caseMap.put(s.toLowerCase(), s);
            }
            String str = s.replaceAll("[aeiouAEIOU]","*").toLowerCase();
            if(!vowelMap.containsKey(str)){
                vowelMap.put(str, s);
            }
        }
        int i = 0;
        for(String q: queries){
            if(wordSet.contains(q)){
                ans[i++] = q;
            }else if(caseMap.containsKey(q.toLowerCase())){
                ans[i++] = caseMap.get(q.toLowerCase()); 
            }else if (vowelMap.containsKey(q.replaceAll("[aeiouAEIOU]","*").toLowerCase())){
                ans[i++] = vowelMap.get(q.replaceAll("[aeiouAEIOU]","*").toLowerCase());
            }else{
                ans[i++] = "";
            }

        }
    
        return ans;
    }
}