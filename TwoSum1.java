import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        Map<Integer, Integer> sumMap = new HashMap<>();
        for( int i = 0; i < nums.length; i++){
            if(!sumMap.containsKey(target - nums[i]))
                sumMap.put(nums[i], i);
            else{
                a[0] = i;
                a[1] = sumMap.get(target - nums[i]);
            }
        }
        return a;
    }

}
