import java.util.HashSet;
import java.util.Set;

class SmallestAbsentPosInt {
    public int smallestAbsent(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int sum = 0;
        for (int num : nums){
            sum += num;
            numSet.add(num);
        }
        double avg = (double) sum / nums.length;
        int target = (int) Math.floor(avg) + 1;

        while(numSet.contains(target)){
            target++;
        }
        if(target <= 0){
            target = 1;
            while(numSet.contains(target)){
            target++;
        }
        }
            
        return target;
    }
}