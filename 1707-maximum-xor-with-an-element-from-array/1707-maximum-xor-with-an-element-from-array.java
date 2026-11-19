import java.util.*;

class Solution {

    class Node {
        Node[] links = new Node[2];
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
            }
        }

        int getMax(int num) {
            Node node = root;
            int maxXor = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                int opposite = 1 - bit;

                if (node.links[opposite] != null) {
                    maxXor |= (1 << i);
                    node = node.links[opposite];
                } else {
                    node = node.links[bit];
                }
            }

            return maxXor;
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {

        Arrays.sort(nums);

        int n = queries.length;
        int[][] offlineQueries = new int[n][3];

        for (int i = 0; i < n; i++) {
            offlineQueries[i][0] = queries[i][0];
            offlineQueries[i][1] = queries[i][1];
            offlineQueries[i][2] = i;
        }

        Arrays.sort(offlineQueries,
            (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[n];

        Trie trie = new Trie();
        int j = 0;

        for (int i = 0; i < n; i++) {

            int x = offlineQueries[i][0];
            int m = offlineQueries[i][1];
            int index = offlineQueries[i][2];

            while (j < nums.length && nums[j] <= m) {
                trie.insert(nums[j]);
                j++;
            }

            if (j == 0) {
                ans[index] = -1;
            } else {
                ans[index] = trie.getMax(x);
            }
        }

        return ans;
    }
}