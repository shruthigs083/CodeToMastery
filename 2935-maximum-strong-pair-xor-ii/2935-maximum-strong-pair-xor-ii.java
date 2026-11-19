import java.util.*;

class Solution {

    class Node {
        Node[] links = new Node[2];
        int count = 0;
    }

    class Trie {
        Node root = new Node();

        void insert(int num) {
            Node node = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;

                if (node.links[bit] == null) {
                    node.links[bit] = new Node();
                }

                node = node.links[bit];
                node.count++;
            }
        }

        void remove(int num) {
            Node node = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                node = node.links[bit];
                node.count--;
            }
        }

        int getMax(int num) {
            Node node = root;
            int maxXor = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                int opposite = 1 - bit;

                if (node.links[opposite] != null &&
                    node.links[opposite].count > 0) {

                    maxXor |= (1 << i);
                    node = node.links[opposite];

                } else {
                    node = node.links[bit];
                }
            }

            return maxXor;
        }
    }

    public int maximumStrongPairXor(int[] nums) {

        Arrays.sort(nums);

        Trie trie = new Trie();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            trie.insert(nums[right]);

            while (nums[right] - nums[left] > nums[left]) {
                trie.remove(nums[left]);
                left++;
            }

            ans = Math.max(ans, trie.getMax(nums[right]));
        }

        return ans;
    }
}