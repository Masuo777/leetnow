package com.marshio.code.leetcode.solutions;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    // 线性搜索
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

    // 字典树
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

    // 字典树 + 深度优先搜索（dfs）
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

    // 判断儿子节点是否都为空
    private boolean isChildrenEmpty(TrieNode[] children) {
        for (TrieNode child : children) {
            if(!isNull(child)) {
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

    }
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("aaa");
        // list.add("b");
        // list.add("cd");
        System.out.println(wordBreak3("aaaaaaa", list));
    }
}
