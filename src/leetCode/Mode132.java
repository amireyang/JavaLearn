package leetCode;

public class Mode132{
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3){
            return false;
        }
        int i = 1;
        while(i < nums.length){

            while(i < nums.length && nums[i - 1] >= nums[i]){
                i++;
            }
            if(i == nums.length - 1) return false;
            if(nums[i + 1] < nums[i]){
                return true;
            }
            else{
                i++;
            }


        }
        return false;

    }
}
