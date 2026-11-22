
class Solution {
    public int divide(int dividend, int divisor) {

        // Handle integer overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Check whether the answer should be negative
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert both numbers to positive long values
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        // Keep dividing while dividend is greater than divisor
        while (a >= b) {

            long temp = b;
            long multiple = 1;

            // Find the largest multiple of divisor that fits
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            // Subtract that multiple from dividend
            a -= temp;

            // Add the corresponding multiple to quotient
            quotient += multiple;
        }

        // Apply the correct sign
        if (negative) {
            quotient = -quotient;
        }

        return (int) quotient;
    }
}
