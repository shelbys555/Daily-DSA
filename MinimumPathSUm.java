class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 ){
                    if(j == 0) continue;
                    dp[0][j] = grid[0][j] + dp[0][j - 1];
                }
                if(j == 0){
                    if(i == 0) continue;
                    dp[i][0] = grid[i][0] + dp[i - 1][0];
                }
                if(i != 0 && j != 0)
                dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n - 1][m - 1];
    }
}