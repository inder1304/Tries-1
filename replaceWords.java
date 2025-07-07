class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root = new TrieNode();

    // Insert root words into Trie
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

   
    private String findRoot(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) break;
            prefix.append(c);
            node = node.children[idx];
            if (node.isEnd) return prefix.toString();
        }

        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String root : dictionary) {
            insert(root);
        }

        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");

        for (String word : words) {
            if (result.length() > 0) result.append(" ");
            result.append(findRoot(word));
        }

        return result.toString();
    }
}
