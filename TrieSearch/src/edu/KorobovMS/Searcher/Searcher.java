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
	}
	
	private void out(Trie.Node node, int level) {
		if (node.edges == null) {
			return;
		} else {
			StringBuilder line = new StringBuilder();
			for (int i = 1; i <= level; i++) {
				line.append("-");
			}

			for (Trie.Node.Edge e : node.edges) {
				System.out.println(line.toString() + e.character);
				out(e.next, level + 1);
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
			Trie.Node.Edge suitableEdge = null;
			Iterator<Trie.Node.Edge> edgeIterator = null;
			
			if (node.edges == null) {
				return false;
			} else {
				edgeIterator = node.edges.iterator();
			}
			
			while (edgeIterator.hasNext()) {
				Trie.Node.Edge currentEdge = edgeIterator.next();
				if (currentEdge.character == str.charAt(0)) {
					suitableEdge = currentEdge;
					break;
				}
			}
			
			if (suitableEdge == null) {
				return false;
			} else {
				return actualFind(suitableEdge.next, str.substring(1));
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
		public List<Edge> edges = null;
		public boolean leaf = false;
		
		/**
		 * Ребро бора. Имеет букву и ссылку на инциндентный узел.
		 * 
		 * @author misha
		 *
		 */
		class Edge {
			public char character;
			public Node next;
		}		
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
			if (node.edges == null) {
				Node.Edge tempEdge;
				
				node.edges = new ArrayList<Node.Edge>();				
				tempEdge = node.new Edge();
				tempEdge.character = str.charAt(0);
				tempEdge.next = new Node();				
				node.edges.add(tempEdge);
				
				actualAddString(tempEdge.next, str.substring(1));
			} else {
				Node.Edge suitableEdge = null;
				Iterator<Node.Edge> edgeIterator = node.edges.iterator();
				
				while (edgeIterator.hasNext()) {
					Node.Edge currentEdge = edgeIterator.next();
					
					if (currentEdge.character == str.charAt(0)) {
						suitableEdge = currentEdge;
						break;
					}
				}
				
				if (suitableEdge == null) {
					suitableEdge = node.new Edge();
					suitableEdge.character = str.charAt(0);
					suitableEdge.next = new Node();
					node.edges.add(suitableEdge);
				}
				
				actualAddString(suitableEdge.next, str.substring(1));
			}
		}
	}
}
