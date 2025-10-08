class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions); // Step 1: Sort potions
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            // Step 2: Minimum required potion
            long required = (success + spell - 1) / spell;

            // Step 3: Binary search
            int left = 0, right = m;
            while (left < right) {
                int mid = (left + right) / 2;
                if (potions[mid] >= required) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // Step 4: Count successful pairs
            result[i] = m - left;
        }

        return result;
    }
}