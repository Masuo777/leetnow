package com.marshio.code.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 10/5/2022 下午1:43
 * @Description 单词拆分II
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-break-ii
 */

public class S003_H_WordBreakII {
    /*
     * 给定一个字符串 s 和一个字符串字典wordDict，在字符串s中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
     * 以任意顺序 返回所有这些可能的句子。
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     */

    public List<String> wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String str : wordDict) {
            trie.insert(str);
        }
        trie.findAll(s, 0);

        return trie.getList();
    }

    static class Trie {

        TrieNode root;
        List<String> res;
        List<String> list;

        public Trie() {
            this.root = new TrieNode('/');
            this.res = new ArrayList<>();
            this.list = new ArrayList<>();
        }

        public void insert(String word) {
            TrieNode tmp = root;
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                if (tmp.children[c - 'a'] == null) {
                    tmp.children[c - 'a'] = new TrieNode(c);
                }
                tmp = tmp.children[c - 'a'];
            }
            tmp.isEnd = true;
        }

        public void findAll(String s, int start) {
            if (start >= s.length()) {
                res.add(String.join(" ", list));
                return;
            }
            TrieNode tmp = root;
            for (int i = start; i < s.length(); i++) {
                char c = s.charAt(i);
                if (tmp.children[c - 'a'] == null) {
                    return;
                }
                tmp = tmp.children[c - 'a'];
                if (tmp.isEnd) {
                    list.add(s.substring(start, i + 1));
                    findAll(s, i + 1);
                    list.remove(list.size() - 1);
                }
            }
        }

        public List<String> getList() {
            return res;
        }
    }

    static class TrieNode {
        char data;
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];

        public TrieNode(char data) {
            this.data = data;
            this.isEnd = false;
        }
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("cats");
        list.add("and");
        list.add("sand");
        list.add("dog");
        System.out.println(wordBreak("catsanddog", list));
    }
}
