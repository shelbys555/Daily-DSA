class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int emptyBottles = 0;
        int drunk = 0;
        while(numBottles != 0 || numExchange <= emptyBottles){
            if(emptyBottles < numExchange){
                //drink
                drunk += numBottles;
                emptyBottles += numBottles;
                numBottles = 0;
            }else{
                //exchange
                numBottles++;
                emptyBottles = emptyBottles - numExchange;
                numExchange++;
            }
            
        }
        return drunk;
    }
}