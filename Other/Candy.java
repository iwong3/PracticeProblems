import java.util.*;

class Candy {

    public static void main(String[] args) {
        int[] ratings = new int[]{1, 2, 2};
        for (int i : ratings) {
            System.out.print("["+i+"]");
        }
        System.out.println("");
        System.out.println(candy(ratings));
    }

    // problem
    // each child must have at least 1 candy
    // children with a higher rating get more candy than their neighbors
    // given a list of child ratings, return the minimum candies
    public static int candy(int[] ratings) {
        
        // algo
        // going left to right, make sure all children have correct candy w/ respect to person on left
        // going right to left, make sure all children have correct candy w/ respect to person on right
        
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        
        for (int i = 1; i < candy.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
        }
        
        for (int i = candy.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candy[i] = Math.max(candy[i], candy[i+1] + 1);
            }
        }
        
        int sum = 0;
        for (int c : candy) {
            sum += c;
        }
        return sum;
        
    }
}