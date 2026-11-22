
class Solution {
    public int totalHammingDistance(int[] nums) {

        int n = nums.length;
        int ans = 0;

        // Check all 32 bit positions
        for (int i = 0; i < 32; i++) {

            int ones = 0;

            // Count numbers having 1 at the current bit
            for (int num : nums) {

                if ((num & (1 << i)) != 0) {
                    ones++;
                }
            }

            // Count numbers having 0
            int zeros = n - ones;

            // Calculate different pairs
            ans += ones * zeros;
        }

        return ans;
    }
}
