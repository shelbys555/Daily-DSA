class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        
        // Loop through all possible starting indices of the first subarray
        for (int i = 0; i + 2 * k <= n; i++) {
            if (isStrictlyIncreasing(nums, i, i + k) && 
                isStrictlyIncreasing(nums, i + k, i + 2 * k)) {
                return true;
            }
        }
        
        return false;
    }

    // Helper method to check if nums[start..end-1] is strictly increasing
    private boolean isStrictlyIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            if (nums.get(i) >= nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
