import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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
	public Node[] getMinimumCut() {
		Set<Entry<Integer, Node>> nodes = vertices.entrySet();
		
		for (Entry<Integer, Node> node : nodes) {
			System.out.println(node.getValue().id);
		}
		return null;
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
		KargerGraphMinCut graph = new KargerGraphMinCut("./data/kargerMinCut");
		graph.getMinimumCut();
	}
	
	
	
	private class Node {
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