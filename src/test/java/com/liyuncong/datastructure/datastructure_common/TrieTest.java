package com.liyuncong.datastructure.datastructure_common;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TrieTest {

	@Test
	public void test() throws IOException {
		InputStream input = new FileInputStream("d:/test/words.txt");
		List<String> words = IOUtils.readLines(input, "utf-8");
		Trie trie = new Trie();
		trie.addAll(words);
		List<String> result = trie.getWordsBySuffix("like");
		for (String string : result) {
			System.out.println(string);
		}
	}

}
