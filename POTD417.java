import java.util.*;

public class Solution {
    // Directions: up, down, left, right
    private final int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Start DFS from all Pacific-bordering cells (top row and left column)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j);
        }

        // Start DFS from all Atlantic-bordering cells (bottom row and right column)
        for (int i = 0; i < m; i++) {
            dfs(heights, atlantic, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, atlantic, m - 1, j);
        }

        // Find cells reachable from both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] ocean, int i, int j) {
        int m = heights.length;
        int n = heights[0].length;
        ocean[i][j] = true;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            // Check bounds and if not visited and flow is valid (neighbor height >= current)
            if (x >= 0 && x < m && y >= 0 && y < n &&
                !ocean[x][y] && heights[x][y] >= heights[i][j]) {
                dfs(heights, ocean, x, y);
            }
        }
    }
}
