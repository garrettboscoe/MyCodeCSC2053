// author @garrett boscoe

import java.util.ArrayDeque;
import java.util.Queue;

public class UndirectedGraph<T> implements GraphInterface<T> {

	public static final int NULL_EDGE = 0;
	private static final int DEFCAP = 50;  
	private int numVertices;
	private int maxVertices;
	private T[] vertices;
	private int[][] edges;
	private boolean[] marks;  

	public UndirectedGraph()	{
		numVertices = 0;
		maxVertices = DEFCAP;
		vertices = (T[]) new Object[DEFCAP];
		marks = new boolean[DEFCAP];
		edges = new int[DEFCAP][DEFCAP];
	}

	public UndirectedGraph(int maxV)	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = (T[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = new int[maxV][maxV];
	}

	public boolean isEmpty()	{
		return (numVertices == 0);
	}

	public boolean isFull()	{
		return (numVertices==maxVertices);
	}

	public void addVertex(T vertex)	{ 

		vertices[numVertices] = vertex;
		for(int i=0;i<numVertices;i++) {
			edges[numVertices][i] = NULL_EDGE;
			edges[i][numVertices] = NULL_EDGE;
		}
		numVertices++;

	}

	public boolean hasVertex(T vertex)	{
		for(int i = 0;i<vertices.length;i++) {
			if(vertices[i].equals(vertex)) {
				return true;
			}
		}
		return false;
	}

	private int indexIs(T vertex)	{
		int i = 0;
		while (!vertex.equals(vertices[i]))
			i++;
		return i;
	}

	public void addEdge(T vertex1, T vertex2)	{
		int row = indexIs(vertex1);
		int col = indexIs(vertex2);
		edges[row][col] = 1;
		edges[col][row] = 1;
	}


	public Queue<T> getToVertices(T vertex)	{
		Queue<T> adjacentVertices = new ArrayDeque<T>();
		int start;
		int end;
		start = indexIs(vertex);
		for (end = 0; end < numVertices; end++)
			if (edges[start][end] != NULL_EDGE)
				adjacentVertices.add(vertices[end]);
		return adjacentVertices;
	}

	public void clearMarks() {
		marks = new boolean[maxVertices];
	}

	public void markVertex(T vertex)	{
		marks[indexIs(vertex)]=true;
	}


	public boolean isMarked(T vertex)	{

		return marks[indexIs(vertex)];
	}


	public T getUnmarked()
	{
		for(int i =0;i<vertices.length;i++) {
			if(marks[i]==false)
				return vertices[i];
		}
		return null;

	}


	public boolean edgeExists(T vertex1, T vertex2)
	{
		return (edges[indexIs(vertex1)][indexIs(vertex2)] != NULL_EDGE);
	}


	public boolean removeEdge(T vertex1, T vertex2)	{
		boolean exists = edgeExists(vertex1, vertex2);
		edges[indexIs(vertex1)][indexIs(vertex2)] = NULL_EDGE;
		edges[indexIs(vertex2)][indexIs(vertex1)] = NULL_EDGE;
		return exists;
	}

}
