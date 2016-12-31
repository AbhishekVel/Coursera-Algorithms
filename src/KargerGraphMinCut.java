import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class KargerGraphMinCut {
	
	private HashMap<Integer, Node> vertices;
	
	public KargerGraphMinCut(String location) {
		vertices = new HashMap<Integer, Node>();
		loadData(location);
		
		
	}
	
	/**
	 * Applies the KargerMinCut algorithm 
	 * @return a size 2 array with the two vertices which to split for a minimum cut
	 */
	public int getMinimumCut() {
		ArrayList<Node> listOfNodes = new ArrayList<>(vertices.values());
		Node[] vertices = new Node[2];
		while (listOfNodes.size() > 2) {
			// choosing a random node
			Node firstNode = listOfNodes.get((new Random()).nextInt(listOfNodes.size()));
			//System.out.print(firstNode.id);
			
			//choosing a random node which connects to one of the first node's edges
			Node toRemove = firstNode.adjacencyList.remove((new Random()).nextInt(firstNode.adjacencyList.size()));
			listOfNodes.remove(toRemove);
			//System.out.println(" " + toRemove.id);
			
			for (Node mergeNode : toRemove.adjacencyList) {
				if (!firstNode.equals(mergeNode)) {
					firstNode.adjacencyList.add(mergeNode);
					mergeNode.adjacencyList.add(firstNode);
					mergeNode.adjacencyList.remove(toRemove);
				}
			}			
		}
		
		vertices[0] = listOfNodes.get(0);
		vertices[1] = listOfNodes.get(1);
		
		//System.out.println("size " + vertices[0].adjacencyList.size() + " vertices: " + vertices[0].id + ", " + vertices[1].id);
		return vertices[0].adjacencyList.size();
	}
	
	private void loadData(String location) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(location));
			
			while (scan.hasNextLine()) {
				String[] verticesStr = scan.nextLine().split("\t");
				int vertexId = Integer.parseInt(verticesStr[0]);
				Node vertex;
				
				if (vertices.containsKey(vertexId))
					vertex = vertices.get(vertexId);
				else {
					vertex = new Node(vertexId);
					vertices.put(vertexId, vertex);

				}
				
				for (int pos = 1; pos < verticesStr.length; pos++) {
					Node adjacent;
					int adjacentId = Integer.parseInt(verticesStr[pos]);
					
					if (vertices.containsKey(adjacentId))
						adjacent = vertices.get(adjacentId);
					else {
						adjacent = new Node(adjacentId);
						vertices.put(adjacentId, adjacent);
					}
					vertex.adjacencyList.add(adjacent);
				}
			}
		} catch(FileNotFoundException error) {
			System.err.println("Error loading " + location);
			
		} finally {
			if (scan != null)
				scan.close();
		}
		
		System.out.println("Completed loading of data.");
	}
	
	public static void main(String[] args) {
		int minCut = Integer.MAX_VALUE;
		for (int i = 0; i < 10000; i++) {
			KargerGraphMinCut graph = new KargerGraphMinCut("./data/kargerMinCut");
			int cut = graph.getMinimumCut();
			if (cut < minCut) {
				minCut = cut;
			}
			System.out.println("Iteration: " + i + " MinCut: " + minCut);
		}
		System.out.println(minCut);
	}
	
	
	
	private class Node implements Cloneable{
		private int id;
		private ArrayList<Node> adjacencyList;
		
		public Node(int id) {
			this(id, new ArrayList<Node>());
		}
		
		public Node(int id, ArrayList<Node> adjacencyList) {
			this.id = id;
			this.adjacencyList = adjacencyList;
		}
		
		@Override
		public boolean equals(Object other) {
			if (this == other) 
				return true;
		
			if (!(other instanceof Node)) 
				return false;
			
			if (((Node)other).id == this.id)
				return true;
			else
				return false;
		}
		
		@Override
		public int hashCode() {
			return this.id;
		}
	}

}