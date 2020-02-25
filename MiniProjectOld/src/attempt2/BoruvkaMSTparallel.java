package attempt2;

import attempt2.MyThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoruvkaMSTparallel {
	
	
	void MST(Map<String, Node> nodeMap) {
	
		// This is where the resulting map will be stored
		Map<String, Node> MST = new ConcurrentHashMap<>();

		// Update this when new nodes get added
		List<String> mapStrings = new ArrayList<>();
		mapStrings.add("A");
		mapStrings.add("B");
		mapStrings.add("C");
		mapStrings.add("D");
		mapStrings.add("E");
		mapStrings.add("F");
		
		MST = updateMSTnodeMap(mapStrings, MST, nodeMap);

		printMST(MST, mapStrings);

	}
	
	void printMST(Map<String, Node> MST, List<String> mapStrings) {

		System.out.println("Node \tEdge \tWeight");
		for(int i = 0; i < MST.size(); i++) {
			System.out.println("Node " + mapStrings.get(i) + 
					"\t" + MST.get(mapStrings.get(i)).getNodeEdges().get(0).getEdgeName() + 
					"\t" + MST.get(mapStrings.get(i)).getNodeEdges().get(0).getEdgeWeight());
		}
	}

	
	/** If the node is not already included in the result this function adds it 
	 * 
	 * @param mapStrings, result Map, node Map
	 * @return The resulting Map
	 * */
	synchronized Map<String, Node> updateMSTnodeMap(List<String> mapStrings, Map<String,
			Node> MST, Map<String, Node> nodeMap){
		
		for(int i = 0; i<mapStrings.size(); i++) {
			if(MST.containsKey(mapStrings.get(i))) {
				System.out.println("node already added");
			}else {
				Node n = nodeMap.get(mapStrings.get(i));
				n.setNodeEdges(getMinEdge(n, mapStrings));
				MST.put(mapStrings.get(i), n);
			}
		}	
		return MST;
	}
		
	/** Gets the minimum edge of the node
	 * 
	 * @param node
	 * @param edgeMap
	 * @param mapStrings
	 * @return minEdge
	 */
	Edge getMinEdge(Node node, List<String> mapStrings) {
		
		Edge minEdge = new Edge(0);
		minEdge.setEdgeWeight(Integer.MAX_VALUE);
		for(int i = 0; i<node.getNodeEdges().size(); i++) {
			if(node.getNodeEdges().get(i).getEdgeWeight() < minEdge.getEdgeWeight()) {
				minEdge = node.getNodeEdges().get(i);
			}
		}
		return minEdge;
	}
	

public static void main(String[] args) {
		
	long startTime = System.nanoTime();
	
	
		int numThreads;
		
		//get input parameters
				try {
					numThreads =  Integer.parseInt(args[0]);//number of threads
				}catch(Exception e) {
					throw(e);
				}
				
			//generate thread list
			List<MyThread> myThreads = new ArrayList<MyThread>();
			for(int j = 0; j < numThreads; j++) {
				MyThread myThread = new MyThread(j);
				myThreads.add(myThread);
			}
			//for(MyThread t:myThreads) {
			//t.run();
			//}
						
			// Creating Edges for nodes
			Edge edge1 = new Edge(0, 5);
			Edge edge2 = new Edge(1, 6);
			Edge edge3 = new Edge(2, 2);
			Edge edge4 = new Edge(3, 9);
			Edge edge5 = new Edge(4, 8);
			Edge edge6 = new Edge(5, 2);
			Edge edge7 = new Edge(6, 3);
			Edge edge8 = new Edge(7, 5);
			Edge edge9 = new Edge(8, 14);
			
			// Populating Map with Nodes
			Node nodeA = new Node(0, edge1, edge2);
			Node nodeB = new Node(1, edge1, edge3, edge4);
			Node nodeC = new Node(2, edge2, edge3, edge5, edge9);
			Node nodeD = new Node(3, edge4, edge5, edge6, edge7);
			Node nodeE = new Node(4, edge6, edge8);
			Node nodeF = new Node(5, edge7, edge8, edge9);
			
			Map<String, Node> nodeMap = new ConcurrentHashMap<>();
			nodeMap.put("A", nodeA);
			nodeMap.put("B", nodeB);
			nodeMap.put("C", nodeC);
			nodeMap.put("D", nodeD);
			nodeMap.put("E", nodeE);
			nodeMap.put("F", nodeF);			
			
			// Simple Debug			
			
			BoruvkaMSTparallel mst = new BoruvkaMSTparallel();
			mst.MST(nodeMap);
			
			long endTime = System.nanoTime();
			double time = ((double)(endTime - startTime))/1000000000.0;
			System.out.println("Execution of Sequential Program took: " + time + " seconds)");
				
	}
}

