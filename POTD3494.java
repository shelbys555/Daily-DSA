class Solution {
    /**
     * Memory-optimized version.
     * Time: O(n * m), Space: O(n)
     */
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;

        long[] finishTime = new long[n]; // Finish time for each wizard
        long[] time = new long[n];       // timeTaken[i] for current potion
        long[] prefix = new long[n];     // prefix sum of timeTaken

        // --- Potion 0 ---
        prefix[0] = time[0] = (long) skill[0] * mana[0];
        finishTime[0] = prefix[0];

        for (int i = 1; i < n; i++) {
            time[i] = (long) skill[i] * mana[0];
            prefix[i] = prefix[i - 1] + time[i];
            finishTime[i] = prefix[i];
        }

        // --- Potions 1 to m - 1 ---
        for (int j = 1; j < m; j++) {
            // Compute timeTaken and prefix for current potion
            prefix[0] = time[0] = (long) skill[0] * mana[j];
            for (int i = 1; i < n; i++) {
                time[i] = (long) skill[i] * mana[j];
                prefix[i] = prefix[i - 1] + time[i];
            }

            // Find earliest possible start time for potion j
            long startTime = finishTime[0]; // Wizard 0 must be free

            for (int k = 1; k < n; k++) {
                long requiredStart = finishTime[k] - prefix[k - 1];
                startTime = Math.max(startTime, requiredStart);
            }

            // Update finish times
            finishTime[0] = startTime + time[0];
            for (int i = 1; i < n; i++) {
                finishTime[i] = finishTime[i - 1] + time[i];
            }
        }

        return finishTime[n - 1]; // Final finish time of last potion by last wizard
    }
}
