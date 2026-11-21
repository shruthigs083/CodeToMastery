class Solution {

    int max = 0;

    public int maxLength(List<String> arr) {
        backtrack(arr, 0, 0, 0);
        return max;
    }

    void backtrack(List<String> arr, int index, int mask, int len) {

        // Update maximum length
        max = Math.max(max, len);

        if (index == arr.size()) {
            return;
        }

        int newMask = 0;
        boolean valid = true;

        // Create bitmask for current string
        for (char ch : arr.get(index).toCharArray()) {

            int bit = 1 << (ch - 'a');

            // Check duplicate characters within the string
            if ((newMask & bit) != 0) {
                valid = false;
                break;
            }

            newMask |= bit;
        }

        // Choice 1: Include current string
        if (valid && (mask & newMask) == 0) {
            backtrack(
                arr,
                index + 1,
                mask | newMask,
                len + arr.get(index).length()
            );
        }

        // Choice 2: Exclude current string
        backtrack(arr, index + 1, mask, len);
    }
}