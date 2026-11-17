import java.util.*;

class Solution {
    public String longestWord(String[] words) {

        Arrays.sort(words);

        HashSet<String> set = new HashSet<>();

        String ans = "";

        for (String word : words) {

            boolean valid = true;

            // Check whether all prefixes exist
            for (int i = 1; i < word.length(); i++) {

                String prefix = word.substring(0, i);

                if (!set.contains(prefix)) {
                    valid = false;
                    break;
                }
            }

            // If all prefixes exist
            if (valid) {

                set.add(word);

                if (word.length() > ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }
}