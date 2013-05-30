package edu.KorobovMS.SpanningTreeAPI;

import java.util.*;

import edu.KorobovMS.SpanningTreeAPI.Graph.Vertex;

public class PrimAlgorithm implements SpanningTreeMaker {

	@Override
	public Graph buildSpanningTree(Graph graph) {		
		final List<Graph.Vertex> vertices = Collections.list(Collections.enumeration(graph.vertices));
		final List<Graph.Edge> edges = Collections.list(Collections.enumeration(graph.edges));
		int verticesCount = vertices.size();
		final double d[] = new double[verticesCount];
		Graph.Vertex p[] = new Graph.Vertex[verticesCount];
		Queue<Graph.Vertex> Q = new PriorityQueue<Graph.Vertex>(verticesCount, new Comparator<Graph.Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				int i1 = vertices.indexOf(o1);
				int i2 = vertices.indexOf(o2);
				return Double.compare(d[i1], d[i2]);
			}
		});
		Set<Graph.Edge> T = new HashSet<Graph.Edge>();
		Graph.Vertex v = null;
		
		// Прогонка алгоритма
		for (int i = 0; i < verticesCount; i++) {
			d[i] = Double.POSITIVE_INFINITY;
			p[i] = null;
		}
		
		d[0] = 0;
		Q.addAll(vertices); 
		v = Q.remove();
		
		while (Q.size() != 0) {
			Iterator<Graph.Edge> edgeIterator = edges.iterator();
			// Цикл по всем смежным вершинам для v
			while (edgeIterator.hasNext()) {
				Graph.Edge current = edgeIterator.next();
				if (current.getVertices().contains(v)) {
					Graph.Vertex u = null;	
					Iterator<Graph.Vertex> i1 = current.getVertices().iterator();
					while (i1.hasNext()) {
						Graph.Vertex q = i1.next();
						if (!q.equals(v)) {
							u = q;
						}
					}
					// Имеем u и v - смежные
					
					if (Q.contains(u) && current.getWeight() < d[vertices.indexOf(u)]) {
						d[vertices.indexOf(u)] = current.getWeight();
						p[vertices.indexOf(u)] = v;
					}
				}
			} // Цикл по всем смежным вершинам для v
			
			v = Q.remove();
			edgeIterator = edges.iterator();
			while (edgeIterator.hasNext()) {
				Graph.Edge current = edgeIterator.next();
				if (current.getVertices().contains(v) && current.getVertices().contains(p[vertices.indexOf(v)])) {
					T.add(current);
					break;
				}
			}
			System.out.println("------------ " + T);
		}
		
		return new Graph(graph.vertices, T);
	}
}
