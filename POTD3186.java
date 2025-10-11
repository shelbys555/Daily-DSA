import java.util.*;

class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int p : power) {
            freqMap.put(p, freqMap.getOrDefault(p, 0) + 1);
        }

        // Sort the unique damage values
        List<Integer> unique = new ArrayList<>(freqMap.keySet());
        Collections.sort(unique);

        int n = unique.size();
        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            int dmg = unique.get(i);
            long totalDamage = (long) dmg * freqMap.get(dmg);

            if (i == 0) {
                dp[i] = totalDamage;
            } else {
                // Find the last index j where unique[i] - unique[j] > 2
                int j = i - 1;
                while (j >= 0 && unique.get(i) - unique.get(j) <= 2) {
                    j--;
                }

                long include = totalDamage + (j >= 0 ? dp[j] : 0);
                dp[i] = Math.max(dp[i - 1], include);
            }
        }

        return dp[n - 1];
    }
}
