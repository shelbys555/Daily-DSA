public class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];

        // Map to track the last day a lake was filled
        Map<Integer, Integer> fullLakes = new HashMap<>();

        // TreeSet to keep dry day indices in sorted order
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int lake = rains[i];

            if (lake == 0) {
                // Dry day, placeholder for now, will be updated if needed
                dryDays.add(i);
                ans[i] = 1;  // Default value, might be overwritten
            } else {
                // It's raining on lake
                if (fullLakes.containsKey(lake)) {
                    // Need to find a dry day after the lake was last filled
                    int lastFilledDay = fullLakes.get(lake);
                    Integer dryDay = dryDays.higher(lastFilledDay);

                    if (dryDay == null) {
                        return new int[0]; // No dry day to prevent flood
                    }

                    ans[dryDay] = lake; // Use the dry day to dry this lake
                    dryDays.remove(dryDay);
                }

                // Update lake as full
                fullLakes.put(lake, i);
                ans[i] = -1; // It's raining on this day
            }
        }

        return ans;
    }
}