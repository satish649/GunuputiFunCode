public class RotateArray {
    public void rotate(int[] nums, int k) {
        if(k > nums.length) {
            // other option is do k =k%(nums.length)
            System.out.println("Swap not possbile!");
            return;
        }
        int size = nums.length;
        int currentPos = nums.length -k;
        int currentPosPreviousValue = nums[currentPos];
        int savedPosValue = nums[currentPos];
        for(int i= 0; i < size; i ++) {
            int swapPos = (currentPos+k)%size;
            int tmp = nums[swapPos];
            nums[swapPos] = currentPosPreviousValue;
            currentPos = swapPos;
            currentPosPreviousValue = tmp;
        }
    }
}
