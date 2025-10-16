class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] freq = new int[value];

        // Normalize modulo and count frequencies
        for (int num : nums) {
            int mod = ((num % value) + value) % value;  // Ensure non-negative modulo
            freq[mod]++;
        }

        int mex = 0;
        while (true) {
            int mod = mex % value;
            if (freq[mod] > 0) {
                freq[mod]--;
                mex++;
            } else {
                return mex;
            }
        }
    }
}
