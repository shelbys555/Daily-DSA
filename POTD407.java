import java.util.PriorityQueue;

public class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> heap = new PriorityQueue<>((a, b) -> a.height - b.height);

        // Add all the border cells into the heap and mark them visited
        for (int i = 0; i < m; i++) {
            heap.offer(new Cell(i, 0, heightMap[i][0]));
            heap.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            heap.offer(new Cell(0, j, heightMap[0][j]));
            heap.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int trappedWater = 0;
        int maxHeight = 0;

        while (!heap.isEmpty()) {
            Cell cell = heap.poll();
            maxHeight = Math.max(maxHeight, cell.height);

            for (int[] dir : directions) {
                int nx = cell.x + dir[0];
                int ny = cell.y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    int neighborHeight = heightMap[nx][ny];
                    if (neighborHeight < maxHeight) {
                        trappedWater += maxHeight - neighborHeight;
                    }
                    heap.offer(new Cell(nx, ny, Math.max(neighborHeight, maxHeight)));
                }
            }
        }

        return trappedWater;
    }

    private static class Cell {
        int x, y, height;
        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
