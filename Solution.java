public class Solution {
    public int singleNumber(int[] nums) {
        int unique = 0;
        for( int i = 0; i < nums.length; i++){
            unique = unique ^ nums[i];
        }
        return unique;
    } 
}

//solved using xor operation 
//as xor gives if two inputs are same
// thereby making all the double entries as 0
