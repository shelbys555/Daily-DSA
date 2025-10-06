import java.util.PriorityQueue;

public class Solution {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        
        // PriorityQueue stores: [elevation, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { grid[0][0], 0, 0 });

        int[][] directions = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };

        int maxElevation = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int elevation = curr[0];
            int x = curr[1];
            int y = curr[2];

            maxElevation = Math.max(maxElevation, elevation);

            if (x == n - 1 && y == n - 1) {
                return maxElevation;
            }

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    pq.offer(new int[] { grid[nx][ny], nx, ny });
                }
            }
        }

        return -1;  // This should never be reached
    }
}
