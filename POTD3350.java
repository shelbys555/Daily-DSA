class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] incFromLeft = new int[n];
        int[] incFromRight = new int[n];

        // Fill incFromLeft
        incFromLeft[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                incFromLeft[i] = incFromLeft[i - 1] + 1;
            } else {
                incFromLeft[i] = 1;
            }
        }

        // Fill incFromRight
        incFromRight[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1)) {
                incFromRight[i] = incFromRight[i + 1] + 1;
            } else {
                incFromRight[i] = 1;
            }
        }

        // Binary search on k
        int low = 1, high = n / 2;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (hasTwoAdjacentIncreasing(nums, incFromRight, mid)) {
                ans = mid;
                low = mid + 1;  // try to find a bigger k
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean hasTwoAdjacentIncreasing(List<Integer> nums, int[] incFromRight, int k) {
        int n = nums.size();
        for (int i = 0; i + 2 * k <= n; i++) {
            if (incFromRight[i] >= k && incFromRight[i + k] >= k) {
                return true;
            }
        }
        return false;
    }
}
