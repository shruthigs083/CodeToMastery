class Solution {
    int sum = 0;

    public int subsetXORSum(int[] nums) {
        backtrack(nums, 0, 0);
        return sum;
    }

    void backtrack(int[] nums, int index, int xor) {

        // All elements have been considered
        if (index == nums.length) {
            sum += xor;
            return;
        }

        // Choice 1: Include current element
        backtrack(nums, index + 1, xor ^ nums[index]);

        // Choice 2: Exclude current element
        backtrack(nums, index + 1, xor);
    }
}