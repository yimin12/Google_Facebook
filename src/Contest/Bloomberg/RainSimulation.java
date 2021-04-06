package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/30 23:54
 *   @Description :
 *
 */

/**
 *  Simulate rain drops on a 1 meter wide walk way. Each rain drop is going to cover 1 centimeter of the walkway.
 *  Assume that a rain drop randomly falls from any position within the meter and falls straight down.
 *  Find out how many rain drops it takes to wet the whole walkway.
 *
 *  Rain Generation
 *  What we can do is to generate the center of each rain drop with the built-in random function between [0, 1.00001] meter.
 *  If a drop occurs at position 0.921, the area that gets wet will be [0.921 - 0.005, 0.921 + 0.005] = [0.916, 0.926].
 *  If a second drop falls at position 0.918, the area that gets will be [0.913, 0.923].
 *  Then merge the overlapped areas. At this point [0.913, 0.926] of the 1-meter-walkway is wet.
 *  The moment that [0,1] the whole meter is wet, the simulation can stop and return the count of raindrops by far.
 *
 *  Walkway
 *  So how to abstract the walkway in our code to make it easier to keep track of the wet area?
 *  One way is to keep track all the intervals of rain drop areas and merge them every round until eventually left with a single interval [0,1].
 *  The process to merge intervals is going to take O(logN) time if we apply binary search on finding where the current rain drop locates in the set of intervals.
 */
public class RainSimulation {

    /**
     *  One unit of rain, only handle a segment
     *  You should use left, right, up, down to define a square
     */
    static class Interval {
        double left, right;
        boolean isWet(){
            return left >= right;
        }
    }

    /**
     * When the first raindrop falls on [0.916, 0.926],
     * find the the 91st centimeter which is walkway[90] and change it’s right dry boundary to 0.006;
     * find the 92nd centimeter walkway[91] and change it’s left dry boundary to 0.006.
     *
     * When a second drop falls on [0.913, 0.923],
     * find walkway[90] and change right boundary to 0.003;
     * find walkway[91] and see if walkway[91].left is greater than 0.003. Since it is, which means [0, 0.003] has been wet already, so do NOT update walkway[91].left.
     *
     * Whenever a centimeter has a left boundary >= right boundary, the whole centimeter is wet through. Then the count of wet centimeters can increment.
     *
     * When the count of wet centimeters hits 100, the whole walkway is wet through.
     */
    public static void simulateRainDrop(){
        // 100 unit in walkway
        Interval[] sidewalk = new Interval[100];
        for(int i = 0; i < 100; i ++){
            sidewalk[i] = new Interval();
        }
        // simulate 100 drops
        int cnt = 0, wetCnt = 0;
        while(wetCnt < 100){
            ++cnt;
            // get the position of raindrop
            double p = Math.random(); // generate 0 - 1
            double left = p - 0.005;
            double right = p + 0.005;
            if(left >= 0){
                int index = (int)(left / 0.01) ; // update seg[i].right with left bound of rain
                if(!sidewalk[index].isWet()){
                    double curRight = left - index * 0.01;
                    if(curRight < sidewalk[index].right){
                        sidewalk[index].right = curRight;
                        if(sidewalk[index].isWet()){
                            ++wetCnt;
                        }
                    }
                }
            }
            if(right <= 1){
                int index = (int)(right / 0.01);
                if(!sidewalk[index].isWet()){
                    double curLeft = right - index * 0.01; // update seg[i + 1].left with right bound of rain
                    if(curLeft > sidewalk[index].left){
                        sidewalk[index].left = curLeft;
                        if(sidewalk[index].isWet()){
                            ++wetCnt;
                        }
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void main(String[] args) {
        simulateRainDrop();
    }

}
