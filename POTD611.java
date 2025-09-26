public import java.util.Arrays;

public class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);  // Step 1: Sort the array
        int n = nums.length;
        int count = 0;

        // Step 2: Fix the third side from the end
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;

            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    // If nums[i] + nums[j] > nums[k], then all elements between i and j are valid
                    count += (j - i);
                    j--;
                } else {
                    i++;
                }
            }
        }

        return count;
    }
} {
    
}
