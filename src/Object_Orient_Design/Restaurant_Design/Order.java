package Object_Orient_Design.Restaurant_Design;

import java.util.ArrayList;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 20:19
 *   @Description :
 *
 */
public class Order {

    private List<Meal> meals;

    public Order() {
        this.meals = new ArrayList<>();
    }

    public List<Meal> getMeals() {
        return meals;
    }

    // it might add order multiple times
    public void mergeOrder(Order order){
        if(order != null){
            for(Meal meal : order.getMeals()){
                meals.add(meal);
            }
        }
    }

    public float getBill(){
        int bill = 0;
        for(Meal meal : meals){
            bill += meal.getPrice();
        }
        return bill;
    }
}
