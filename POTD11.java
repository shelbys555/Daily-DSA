class Solution {
    public int maxArea(int[] height) {
        int area = 0;
        int i = 0, j = height.length - 1;
        while( i < j){
            area = Math.max(area,(j - i) * (height[i] >= height[j] ? height[j] : height[i]));
            if(height[i] >= height[j])
                j--;
            else
                i++;
        }
        return area;
    }
}