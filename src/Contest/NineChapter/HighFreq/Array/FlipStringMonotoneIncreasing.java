package Contest.NineChapter.HighFreq.Array;

public class FlipStringMonotoneIncreasing {

    public int minFlipMonoIncr(String s){
        int[] left = new int[s.length()];
        int ones = 0;
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '1'){
                ones ++;
            }
            left[i] = ones;
        }
        int flips = s.length() - ones;
        for(int i = 0; i < s.length(); i ++){
            int rightOnes = ones - left[i];
            int rightZeros = s.length() -  i - 1 - rightOnes;
            flips = Math.min(flips, left[i] + rightZeros); // erase all left one and right Zero
        }
        return flips;
    }
}
