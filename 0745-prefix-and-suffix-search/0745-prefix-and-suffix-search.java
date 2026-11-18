
class WordFilter {

    class TrieNode {
        TrieNode[] children = new TrieNode[27];
        int weight = -1;
    }

    TrieNode root = new TrieNode();

    public WordFilter(String[] words) {

        for (int index = 0; index < words.length; index++) {

            String word = words[index];

            for (int i = 0; i <= word.length(); i++) {

                String suffix = word.substring(i);

                String key = suffix + "{" + word;

                TrieNode curr = root;

                for (char ch : key.toCharArray()) {

                    int pos = ch == '{' ? 26 : ch - 'a';

                    if (curr.children[pos] == null) {
                        curr.children[pos] = new TrieNode();
                    }

                    curr = curr.children[pos];
                    curr.weight = index;
                }
            }
        }
    }

    public int f(String pref, String suff) {

        TrieNode curr = root;

        String key = suff + "{" + pref;

        for (char ch : key.toCharArray()) {

            int pos = ch == '{' ? 26 : ch - 'a';

            if (curr.children[pos] == null) {
                return -1;
            }

            curr = curr.children[pos];
        }

        return curr.weight;
    }
}
