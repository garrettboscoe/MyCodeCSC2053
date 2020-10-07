// @author garrett boscoe

import java.util.ArrayDeque;
import java.util.Queue;

public class GraphTester {
	

	private static boolean isPathBF(GraphInterface<String> graph,
			String startVertex, 
			String endVertex    ) {
		
		Queue<String> queue = new ArrayDeque<String>();
		Queue<String> vertexQueue = new ArrayDeque<String>();

		boolean found = false;
		String currVertex;      
		String adjVertex;       

		graph.clearMarks();
		graph.markVertex(startVertex);
		queue.add(startVertex);

		do
		{
			currVertex = queue.remove();
			if (currVertex.equals(endVertex))
				found = true;
			else
			{
				vertexQueue = graph.getToVertices(currVertex); 
				while (!vertexQueue.isEmpty())
				{
					adjVertex = vertexQueue.remove();
					if (!graph.isMarked(adjVertex))
					{
						graph.markVertex(adjVertex);
						queue.add(adjVertex);
					}
				}
			}
		} while (!queue.isEmpty() && !found);

		return found;
	}


	public static void main(String[] args) {


		UndirectedGraph<String> graph = new UndirectedGraph<>();
		graph.addVertex("Bob");
		graph.addVertex("Jack");
		graph.addVertex("Cynthia");
		graph.addVertex("Cathy");
		graph.addVertex("Colleen");
		graph.addVertex("Alex");
		graph.addVertex("George");
		graph.addVertex("Kevin");
		
		graph.addEdge("Bob","Jack");
		graph.addEdge("Bob","Cynthia");
		graph.addEdge("Bob","Alex");
		graph.addEdge("Cynthia","Alex");
		graph.addEdge("Alex","Cathy");
		graph.addEdge("Kevin","Colleen");
		graph.addEdge("Kevin","George");
		
		System.out.println("Bob's friends are "+ graph.getToVertices("Bob"));
		System.out.println("Alex's friends are "+ graph.getToVertices("Alex"));
		System.out.println("George's friends are " + graph.getToVertices("George"));
		System.out.println("Are Jack and Cathy indirect friends?: " + isPathBF(graph,"Jack","Cathy"));
		System.out.println("Are Jack and George indirect friends?: "+ isPathBF(graph,"Jack","George"));
		
		
	}

}



