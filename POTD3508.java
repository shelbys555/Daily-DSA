import java.util.*;

class Router {
    private int memoryLimit;
    private Deque<String> packets;
    private Map<String, int[]> packetMap;

    // For each destination, store timestamps and prefix sums for fast queries
    private Map<Integer, List<Integer>> destTimestamps;
    private Map<Integer, List<Integer>> destPrefixSums;
    private Map<Integer, Map<Integer, Integer>> destCounts;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.packets = new ArrayDeque<>();
        this.packetMap = new HashMap<>();

        this.destTimestamps = new HashMap<>();
        this.destPrefixSums = new HashMap<>();
        this.destCounts = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        String packetKey = source + "_" + destination + "_" + timestamp;
        if(packetMap.containsKey(packetKey)) {
            return false;
        }
        if(packets.size() >= memoryLimit) {
            forwardPacket();
        }
        
        packets.offer(packetKey);
        packetMap.put(packetKey, new int[]{source, destination, timestamp});

        destCounts.putIfAbsent(destination, new HashMap<>());
        destTimestamps.putIfAbsent(destination, new ArrayList<>());
        destPrefixSums.putIfAbsent(destination, new ArrayList<>());

        Map<Integer, Integer> countsMap = destCounts.get(destination);
        List<Integer> timestamps = destTimestamps.get(destination);
        List<Integer> prefixSums = destPrefixSums.get(destination);

        int oldCount = countsMap.getOrDefault(timestamp, 0);
        countsMap.put(timestamp, oldCount + 1);

        // If first time seeing this timestamp, append to timestamps and update prefix sums
        if (oldCount == 0) {
            timestamps.add(timestamp);
            int newPrefix = prefixSums.isEmpty() ? 1 : prefixSums.get(prefixSums.size() - 1) + 1;
            prefixSums.add(newPrefix);
        } else {
            // Duplicate timestamp, just update prefix sums from this timestamp onwards
            // Because timestamps are strictly increasing, this timestamp exists at the end, so increment last prefix sum by 1
            for (int i = timestamps.size() - 1; i >= 0; i--) {
                if (timestamps.get(i) == timestamp) {
                    for (int j = i; j < prefixSums.size(); j++) {
                        prefixSums.set(j, prefixSums.get(j) + 1);
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    public int[] forwardPacket() {
        if(packets.isEmpty()) {
            return new int[0];
        }

        String packetKey = packets.poll();
        int[] packet = packetMap.remove(packetKey);

        int destination = packet[1];
        int timestamp = packet[2];

        Map<Integer, Integer> countsMap = destCounts.get(destination);
        List<Integer> timestamps = destTimestamps.get(destination);
        List<Integer> prefixSums = destPrefixSums.get(destination);

        int count = countsMap.get(timestamp);
        if (count == 1) {
            // Remove timestamp from counts and timestamps
            countsMap.remove(timestamp);

            // Find index of timestamp
            int idx = Collections.binarySearch(timestamps, timestamp);
            timestamps.remove(idx);

            // Update prefix sums by removing the count at idx
            int delta = 1;
            prefixSums.remove(idx);
            // Update prefix sums from idx onwards
            for (int i = idx; i < prefixSums.size(); i++) {
                prefixSums.set(i, prefixSums.get(i) - delta);
            }
        } else {
            // Just decrement count and update prefix sums accordingly
            countsMap.put(timestamp, count - 1);
            int idx = Collections.binarySearch(timestamps, timestamp);
            for (int i = idx; i < prefixSums.size(); i++) {
                prefixSums.set(i, prefixSums.get(i) - 1);
            }
        }

        // If no timestamps left for this destination, clean up maps
        if (timestamps.isEmpty()) {
            destTimestamps.remove(destination);
            destPrefixSums.remove(destination);
            destCounts.remove(destination);
        }

        return packet;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!destTimestamps.containsKey(destination)) return 0;

        List<Integer> timestamps = destTimestamps.get(destination);
        List<Integer> prefixSums = destPrefixSums.get(destination);

        int startIdx = Collections.binarySearch(timestamps, startTime);
        if (startIdx < 0) startIdx = -startIdx - 1;

        int endIdx = Collections.binarySearch(timestamps, endTime);
        if (endIdx < 0) endIdx = -endIdx - 2; // last timestamp <= endTime
        // if endIdx < 0 after this, means no timestamps <= endTime

        if (startIdx >= timestamps.size() || endIdx < 0 || endIdx < startIdx) {
            return 0;
        }

        int countUpToEnd = prefixSums.get(endIdx);
        int countBeforeStart = startIdx == 0 ? 0 : prefixSums.get(startIdx - 1);

        return countUpToEnd - countBeforeStart;
    }
}
