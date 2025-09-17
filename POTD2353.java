import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class POTD2353 {
    class FoodRatings {
    private Map<String, Integer> foodToRating = new HashMap<>();
    private Map<String, String> foodToCuisine = new HashMap<>();
    private Map<String, TreeSet<String>> cuisineToFoodSet = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i = 0; i < foods.length; i++){
            foodToRating.put(foods[i], ratings[i]);
            foodToCuisine.put(foods[i], cuisines[i]);

            cuisineToFoodSet.putIfAbsent(cuisines[i], new TreeSet<>(
                (a, b) -> {
                    int cmp = Integer.compare(foodToRating.get(b), foodToRating.get(a));
                    if(cmp == 0) return a.compareTo(b);
                    return cmp;
                }
            ));

            cuisineToFoodSet.get(cuisines[i]).add(foods[i]);
        }
    }
    
    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> foodSet = cuisineToFoodSet.get(cuisine);
        foodSet.remove(food);
        foodToRating.put(food, newRating);
        foodSet.add(food);
    }
    
    public String highestRated(String cuisine) {
        return cuisineToFoodSet.get(cuisine).first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
}
