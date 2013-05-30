package edu.KorobovMS.SpanningTreeAPI;

import java.util.*;

public class KruskalAlgorithm implements SpanningTreeMaker {

	@Override
	public Graph buildSpanningTree(Graph graph) {
		// Результирующее множество вершин
		Set<Graph.Edge> resultEdges = new HashSet<Graph.Edge>();
		
		// Множество множеств вершин
		Set< Set<Graph.Vertex> > vertexSet = new HashSet<>();
		
		// Список вершин, отсортированный в порядке возрастания весов 
		List<Graph.Edge> sortedEdges = Collections.list(Collections.enumeration(graph.edges));
		Iterator<Graph.Vertex> i = graph.vertices.iterator();		
		while (i.hasNext()) {
			Graph.Vertex v = i.next();
			Set<Graph.Vertex> temp = new HashSet<Graph.Vertex>();
			temp.add(v);
			vertexSet.add(temp);			
		} // Имеем - множество множеств, состоящих из вершин
		
		// Сортируем вершины
		Collections.sort(sortedEdges);
		
		// Прогоняем алгоритм
		Iterator<Graph.Edge> edgeIterator = sortedEdges.iterator();
		while (edgeIterator.hasNext()) {
			// Пара множеств, с которым будем работать потом (будем решать, мержить их или нет)
			Set<Set<Graph.Vertex>> pair = new HashSet<Set<Graph.Vertex>>(2);
			
			// Следующее ребро в списке отсортированных ребер
			Graph.Edge edge = edgeIterator.next();			
			
			// Концы ребра edge
			Iterator<Graph.Vertex> endIter = edge.getVertices().iterator();
			Graph.Vertex end1 = endIter.next();
			Graph.Vertex end2 = endIter.next();
			
			// Итератор по множеству множеств
			Iterator<Set<Graph.Vertex>> setIterator = vertexSet.iterator();
			while (setIterator.hasNext()) {
				// Рассматриваемое подмножество вершин
				Set<Graph.Vertex> subset = setIterator.next();
				if (subset.contains(end1) || subset.contains(end2)) {
					pair.add(subset);
					setIterator.remove();
				}			
			}
			
			// Если были включены 2 различных подмножества
			if (pair.size() == 2) {
				// Включаем ребро в граф и мержим пару множеств pair
				resultEdges.add(edge);
				Iterator<Set<Graph.Vertex>> pairIter = pair.iterator();
				Set<Graph.Vertex> set1 = pairIter.next();
				Set<Graph.Vertex> set2 = pairIter.next();
				set1.addAll(set2);
				vertexSet.add(set1);
			} else if (pair.size() == 1) {
				Set<Graph.Vertex> set1 = pair.iterator().next();
				vertexSet.add(set1);
			}
		}
		
		return new Graph(graph.vertices, resultEdges);
	}
}
