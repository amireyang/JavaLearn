public class Banana{

    private int banana(int[] nums){
        int len = nums.length;
        if(len == 0) return 0;
        int dp = nums[len - 1];
        for(int i = len - 2; i >= 0; i--){
            int max = 2 * nums[i] + dp;
            if(max / 2 <= nums[i] || max / 2 <= dp){
                dp = max;
                continue;
            }
            while(max / 2 >= nums[i] && max / 2 >= dp){
                max--;
            }
            dp = ++max;
        }
        return dp;
    }





  public static void main(String[] args){
        int[] nums = new int[]{5,4,3};
      System.out.println(new Banana().banana(nums));

  }

}