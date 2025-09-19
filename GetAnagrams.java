import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> strMap = new HashMap<>();
        for(String s : strs){
            char[] strArray = s.toCharArray();
            Arrays.sort(strArray);
            String sorted = new String(strArray);
            strMap.putIfAbsent(sorted, new ArrayList<>());
            ArrayList<String> temp = strMap.get(sorted);
            temp.add(s);
            strMap.put(sorted, temp);
        }
        List<List<String>> output = new ArrayList<>();
        strMap.forEach((key, value) -> {
            output.add(value);
        });

        return output;
    }
} {
    
}
