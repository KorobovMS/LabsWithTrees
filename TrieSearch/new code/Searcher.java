package edu.KorobovMS.Searcher;

import java.util.*;

/**
 * Класс, который ищет слово в словаре по бору
 * 
 * @author misha
 *
 */
public class Searcher {
	private Trie trie = new Trie();
	private int count = 0;
	/**
	 * Конструктор
	 * @param newDictionary Набор всех слов в словаре
	 */
	public Searcher(Set<String> newDictionary) {
		Set<String> dictionary = new HashSet<String>(newDictionary);
		for (String str : dictionary) {
			trie.addString(str);
		}
		out(trie.root, 0);
		System.out.println("count = " + count);
	}
	
	private void out(Trie.Node node, int level) {
		if (node.leaf) {
			count++;
		}
		if (node.edges == null) {
			return;
		} else {
			for (Trie.Node e : node.edges.values()) {
				out(e, level + 1);
			}
		}
	}
	
	/**
	 * Добавление нового слова в словарь
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		trie.addString(word);
	}
	
	/**
	 * Поиск слова в словаре
	 * @param word 
	 * @return Факт присутствия слова в словаре
	 */
	public boolean find(String word) {
		return actualFind(trie.root, word);
	}
	
	private boolean actualFind(Trie.Node node, String str) {
		if (str.length() == 0) {
			return node.leaf;
		} else {
			if (node.edges == null) {
				return false;
			} else {
				Trie.Node nextNode = node.edges.get(Character.valueOf(str.charAt(0)));
				if (nextNode == null) {
					return false;
				} else {
					return actualFind(nextNode, str.substring(1));
				}
			}
		}
	}
}

/**
 * Структура данных "бор"
 * 
 * @author misha
 *
 */
class Trie {
	// Корневой элемент бора
	public Node root = new Node();
	
	/**
	 * Узел бора. Имеет несколько инцидентных ребер, помеченных буквой.
	 * 
	 * @author misha
	 *
	 */
	class Node {
		public Map<Character, Node> edges = null;
		public boolean leaf = false;
	}
	
	/**
	 * Добавление строки в бор
	 * @param str
	 */
	public void addString(String str) {
		actualAddString(root, str);
	}
	
	private void actualAddString(Node node, String str) {
		if (str.length() == 0) {
			node.leaf = true;
			return;
		} else {
			Node nextNode = null;
			if (node.edges == null) {
				nextNode = new Node();
				node.edges = new HashMap<Character, Node>();
				node.edges.put(Character.valueOf(str.charAt(0)), nextNode);
			} else {
				nextNode = node.edges.get(Character.valueOf(str.charAt(0)));
				if (nextNode == null) {
					nextNode = new Node();
					node.edges.put(Character.valueOf(str.charAt(0)), nextNode);
				}
			}
			
			actualAddString(nextNode, str.substring(1));
		}
	}
}
