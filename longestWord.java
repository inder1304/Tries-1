class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; //store complete word at leaf
    }

    private TrieNode root = new TrieNode();

    public String longestWord(String[] words) {

        for (String word : words) {
            insert(word);
        }

        return dfs(root, "");
    }

    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.word = word; //marks complete word
    }

    private String dfs(TrieNode node, String result) {
        if (node == null) return result;

        if (node != root && node.word == null) return result;

        String best = node.word != null ? node.word : result;

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                String temp = dfs(node.children[i], best);
                if (temp.length() > best.length() ||
                        (temp.length() == best.length() && temp.compareTo(best) < 0)) {
                    best = temp;
                }
            }
        }
        return best;
    }
}
