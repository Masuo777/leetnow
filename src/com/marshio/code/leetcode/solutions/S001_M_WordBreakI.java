package com.marshio.code.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author masuo
 * @data 9/5/2022 上午11:05
 * @Description 单词拆分
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-break
 */

public class S001_M_WordBreakI {

    /*
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     */

    // 线性搜索 --> aaaaaaa {aaa,aaaa} 失败
    public boolean wordBreak1(String s, List<String> wordDict) {
        // 这种方法遇到重复字符就会失败,如：aaaaaaa，["aaa","aaaa"]
        int length = s.length();
        int start = 0;
        for (int i = 0; i < length; i++) {
            String substring = s.substring(start, i + 1);
            if (wordDict.contains(substring)) {
                start = i + 1;
            } else if (i == length - 1) {
                return false;
            }
        }

        return true;
    }

    // 字典树 --> aaaaaaa {aaa,aaaa} 失败
    public boolean wordBreak2(String s, List<String> wordDict) {

        TrieNode root = new TrieNode('/');

        // 将单词加入字典树
        buildTrieTree(root, wordDict);

        int length = s.length();

        int index = 0;
        // 每次新单词都从根节点开始寻找
        TrieNode start = root;
        // 循环字符
        while (index < length) {
            char c = s.charAt(index++);
            TrieNode child = start.children[c - 'a'];
            if (child == null) {
                // 为空说明没有这个单词，
                return false;
            }
            // 这种判断会使单词长度前缀相同的后缀不同的单词搜索不到，比如 abcd、abce、abc，每次遇到 abc 就会重新开始，但是后面还有单词，所以我们需要深度搜索
            if (child.isEnd) {
                if (index == length) {
                    // 长度到头且结束
                    return true;
                }
                // 重新开始
                start = root;
            } else {
                // 深度
                start = child;
            }
        }

        return start.isEnd;
    }

    // 字典树 + 深度优先搜索（dfs） --> aaaaaaa {aaa,aaaa} 失败
    public boolean wordBreak3(String s, List<String> wordDict) {

        TrieNode root = new TrieNode('/');

        // 将单词加入字典树
        buildTrieTree(root, wordDict);

        int length = s.length();

        int index = 0;
        // 每次新单词都从根节点开始寻找
        TrieNode start = root;
        // 循环字符
        while (index < length) {
            char c = s.charAt(index++);
            TrieNode child = start.children[c - 'a'];
            if (child == null) {
                // 为空说明没有这个单词，
                return false;
            }
            if (index == length) {
                // 长度到头且结束
                return child.isEnd;
            }
            // 深度
            if (isChildrenEmpty(child.children) && child.isEnd) {
                // 没有儿子节点且单词到头则重新开始
                start = root;
            } else {
                // 儿子节点不为空/不是结束点，继续深度
                start = child;
            }
        }

        return start.isEnd;
    }

    // 字典树 + 回溯 --> 超时 OOT
    public boolean wordBreak4(String s, List<String> wordDict) {

        // 利用字典树节点构建字典树
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }
        return trie.find(s, 0);
    }

    // 字典树 + 回溯 + 记忆法
    public boolean wordBreak5(String s, List<String> wordDict) {

        // 利用字典树节点构建字典树
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }
        return trie.find(s, 0);
    }

    // 判断儿子节点是否都为空
    private boolean isChildrenEmpty(TrieNode[] children) {
        for (TrieNode child : children) {
            if (!isNull(child)) {
                return false;
            }
        }
        return true;
    }

    private void buildTrieTree(TrieNode root, List<String> wordDict) {
        for (String word : wordDict) {
            if (word.isEmpty()) {
                continue;
            }
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (tmp.children[c - 'a'] == null) {
                    tmp.children[c - 'a'] = new TrieNode(c);
                }
                tmp = tmp.children[c - 'a'];
            }
            tmp.isEnd = true;
        }
    }

    // 字典树节点
    static class TrieNode {
        public char data;
        public boolean isEnd;
        // 26个字母，小写
        public TrieNode[] children;

        public TrieNode(char data) {
            this.data = data;
            this.isEnd = false;
            this.children = new TrieNode[26];
        }
    }

    // 字典树
    static class Trie {

        TrieNode root = new TrieNode('/');

        int[] memory = new int[301];

        public void insert(String word) {
            TrieNode tmp = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (tmp.children[c - 'a'] == null) {
                    tmp.children[c - 'a'] = new TrieNode(c);
                }
                tmp = tmp.children[c - 'a'];
            }
            tmp.isEnd = true;
        }

        /**
         * 查找在 [startPos ，s.length() - 1] 区间是否有合适的单词
         *
         * @param s        待搜索字符串
         * @param startPos 搜索起始位置
         * @return boolean
         */
        public boolean find(String s, int startPos) {
            int len = s.length();
            if(memory[startPos] == 1) {
                return false;
            }
            if (startPos >= len) {
                return true;
            }
            // 从s的第一位开始搜索，过程类似上面
            TrieNode tmp = root;
            for (int i = startPos; i < len; i++) {
                char c = s.charAt(i);
                TrieNode child = tmp.children[c - 'a'];
                if (child == null) {
                    // 遇到为空的节点，说明单词发生不匹配
                    break;
                }
                tmp = child;
                if (tmp.isEnd && find(s, i + 1)) {
                    // 以 i 为新的起点，往下搜索
                    return true;
                }
            }
            memory[startPos] = 1;
            return false;
        }
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("aaa");
        list.add("bbb");
        list.add("bbbb");
        System.out.println(wordBreak4("aaaaaaa", list));
    }
}
