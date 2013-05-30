package edu.KorobovMS;

import edu.KorobovMS.SpanningTreeAPI.*;

import java.util.*;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Graph graph = null;
			Set<Graph.Vertex> vertices = new LinkedHashSet<Graph.Vertex>();
			Set<Graph.Edge> edges = new LinkedHashSet<>();
			Graph.Vertex[] arr;

			vertices.add(new Graph.Vertex("1"));
			vertices.add(new Graph.Vertex("2"));
			vertices.add(new Graph.Vertex("3"));
			vertices.add(new Graph.Vertex("4"));
			vertices.add(new Graph.Vertex("5"));
			vertices.add(new Graph.Vertex("6"));

			arr = (Graph.Vertex[]) vertices.toArray(new Graph.Vertex[1]);

			edges.add(new Graph.Edge(arr[0], arr[1], 3.0));
			edges.add(new Graph.Edge(arr[0], arr[3], 4.0));
			edges.add(new Graph.Edge(arr[0], arr[2], 7.0));
			edges.add(new Graph.Edge(arr[1], arr[3], 2.0));
			edges.add(new Graph.Edge(arr[2], arr[3], 3.0));
			edges.add(new Graph.Edge(arr[1], arr[4], 8.0));
			edges.add(new Graph.Edge(arr[2], arr[5], 6.0));
			edges.add(new Graph.Edge(arr[3], arr[4], 4.0));
			edges.add(new Graph.Edge(arr[3], arr[4], 4.0));
			edges.add(new Graph.Edge(arr[4], arr[5], 2.0));

			graph = new Graph(vertices, edges);

			System.out.println(graph.toString());

			SpanningTreeMaker maker = new PrimAlgorithm();
			System.out.println(maker.buildSpanningTree(graph));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ОЛЯРМ! ОЛЯРМ! ГОЛАКТЕКО ОПАСНОСТЕ!");
		}
	}
}
