class Solution {

    class Node {
        Node[] links = new Node[2];
        int count = 0;
    }

    class Trie {
        Node root = new Node();

        void insert(int num) {
            Node node = root;

            for (int i = 15; i >= 0; i--) {
                int bit = (num >> i) & 1;

                if (node.links[bit] == null) {
                    node.links[bit] = new Node();
                }

                node = node.links[bit];
                node.count++;
            }
        }

        int countLessThan(int num, int limit) {
            Node node = root;
            int count = 0;

            for (int i = 15; i >= 0; i--) {

                if (node == null) {
                    break;
                }

                int bitNum = (num >> i) & 1;
                int bitLimit = (limit >> i) & 1;

                if (bitLimit == 1) {

                    if (node.links[bitNum] != null) {
                        count += node.links[bitNum].count;
                    }

                    node = node.links[1 - bitNum];

                } else {

                    node = node.links[bitNum];
                }
            }

            return count;
        }
    }

    public int countPairs(int[] nums, int low, int high) {

        Trie trie = new Trie();
        int ans = 0;

        for (int num : nums) {

            ans += trie.countLessThan(num, high + 1)
                 - trie.countLessThan(num, low);

            trie.insert(num);
        }

        return ans;
    }
}