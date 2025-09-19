class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int cheapest = prices[0];
        for(int i : prices){
            if(i < cheapest){
                cheapest = i;
            }
            if(i - cheapest > maxProfit){
                maxProfit = i - cheapest;
            }
        }
        return maxProfit;
    }
}