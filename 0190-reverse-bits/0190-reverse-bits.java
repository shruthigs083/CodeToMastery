public class Solution {
    public int reverseBits(int n) {

        int result = 0;

        for (int i = 0; i < 32; i++) {

            // Shift result left
            result = result << 1;

            // Extract rightmost bit
            int bit = n & 1;

            // Add bit to result
            result = result | bit;

            // Move to next bit
            n = n >>> 1;
        }

        return result;
    }
}