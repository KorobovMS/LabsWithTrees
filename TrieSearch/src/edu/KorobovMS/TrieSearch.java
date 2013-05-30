package edu.KorobovMS;

import java.util.*;
import java.io.*;

import edu.KorobovMS.Searcher.*;

public class TrieSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Searcher s = null;
		Set<String> dict = new HashSet<String>();
		boolean wordFound;
		long time;
		InputStreamReader file = null;
		
		System.out.println("Let's start!");
		time = System.nanoTime();
		try {
			int c;
			StringBuilder result = new StringBuilder();
			
			file = new FileReader("Document");
			
			while ((c = file.read()) != -1) {
				if (Character.isLetter(c)) {
					result.append((char) c);
				} else {
					dict.add(result.toString());
					result.setLength(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} finally {
			if (file != null) {
				file.close();
			}
		}
		
		s = new Searcher(dict);
		time = System.nanoTime() - time;
		System.out.println("Searcher has been generated for " + time + " ns");
		
		time = System.nanoTime();
		String wordToFind = (args.length != 0) ? args[0] : "";
		wordFound = s.find(wordToFind);
		time = System.nanoTime() - time;
		
		StringBuilder result = new StringBuilder("Word \"" + wordToFind + "\" ");
		result.append((wordFound ? "found" : "not found"));
		result.append("; time = ");
		result.append(time);
		result.append(" ns");		
		System.out.println(result.toString());
	}
}
