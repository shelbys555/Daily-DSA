class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101];

        for(int i = 0; i < nums.length; i++){
            freq[nums[i]]++;
        }
        int max = 0, count = 0;
        for(int i = 0; i < freq.length; i++){
            if(max == freq[i]){
                count += freq[i];
            }
            if(freq[i] > max){
                max = freq[i];
                count = freq[i];
            }
        }
        return count;
    }
}