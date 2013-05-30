package edu.KorobovMS.SpanningTreeAPI;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {
	public Set<Vertex> vertices = null;
	public Set<Edge> edges = null;
	
	public Graph(Set<Vertex> vertices, Set<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(vertices.toString());
		result.append("\n");
		result.append(edges.toString());
		return result.toString();
	}
	
	public static class Vertex {
		private String name;
		
		public Vertex(String name) {
			setName(name);
		}
		
		public String toString() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static class Edge implements Comparable<Edge >{
		private Set<Vertex> vertices = new HashSet<Vertex>(2);
		private double weight;
		
		public Edge(Vertex v1, Vertex v2, double weight) {
			vertices.add(v1);
			vertices.add(v2);
			this.weight = weight;
		}
		
		public String toString() {
			StringBuilder result = new StringBuilder("[ ");
			Iterator<Vertex> i = vertices.iterator();
			while (i.hasNext()) {
				Vertex v = i.next();
				result.append(v.getName()).append(" ");
			}
			result.append("(").append(weight).append(") ]");
			return result.toString();
			
		}
		
		@Override
		public int compareTo(Edge arg) {
			return Double.valueOf(weight).compareTo(Double.valueOf(arg.weight));
		}
		
		public Set<Vertex> getVertices() {
			return vertices;
		}

		public double getWeight() {
			return weight;
		}
	}
}
