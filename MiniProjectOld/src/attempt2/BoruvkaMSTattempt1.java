package attempt2;
/*package attempt2;

import attempt2.MyThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoruvkaMST {
	
	
	void MST(Map<String, Node> nodeMap, Map<String, List<Edge>> edgeMap) {
	
		// This is where the resulting map will be stored
		Map<String, Node> MSTnodes = new ConcurrentHashMap<>();
		Map<String, Edge> MSTedges= new ConcurrentHashMap<>();
		
		----------- Testing Stuff ----------------

		//result.put("A", nodeMap.get("A"));
		if(result.keySet().contains("A")) {
			System.out.println("Node A is in the resulting MST");
		}else {
			System.out.println("Node A is NOT in the resulting MST");
		}
		
		
		for(Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			System.out.println(entry.getKey());
		}
		System.out.println();
		for(Map.Entry<String, List<Edge>> entry : edgeMap.entrySet()) {
			System.out.println("Edge Name: " + entry.getValue().get(0).getEdgeName() + " Weight: " +
					entry.getValue().get(0).getEdgeWeight());
		}
		
		for(Map.Entry<String, List<Edge>> entry : edgeMap.entrySet()) {
			System.out.println("Edge Name: " + entry.toString() + " Weight: " +
					entry.getValue().get(0).getEdgeWeight());
		}
		
		for(int i = 0; i < edgeMap.entrySet().size(); i++) {
			System.out.println(edgeMap.get(1).get(1).getEdgeName());
		}
		System.out.println(nodeMap.toString());
		System.out.println(edgeMap.toString());
		
		System.out.println(edgeMap.get("1").get(1).getEdgeNodes().get(1));
		System.out.println(edgeMap.get("1"));
		
		for(Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			if(result)
			System.out.println(entry.getKey());
		}
		
		// Update this when new nodes get added
		List<String> mapStrings = new ArrayList<>();
		mapStrings.add("A");
		mapStrings.add("B");
		mapStrings.add("C");
		mapStrings.add("D");
		mapStrings.add("E");
		mapStrings.add("F");
		
	
		//MSTnodes = updateResultMap(mapStrings, MSTnodes, nodeMap.get("A"));
		
		MSTnodes = updateMSTnodeMap(mapStrings, MSTnodes, 
				getNodeOfMinimumEdge(getMinimumEdgeOfNode(nodeMap.get("A"), edgeMap, mapStrings), nodeMap.get("A")));
		MSTedges = updateMSTedgeMap();
		
		//System.out.println(mapStrings.get(1) +" "+ nodeMap.get(mapStrings.get(1).toString()));
		System.out.println(MSTnodes.toString());
			
		
	}
	
	*//** If the node is not already included in the result this function adds it 
	 * 
	 * @param mapStrings, result Map, node Map
	 * @return The resulting Map
	 * *//*
	Map<String, Node> updateMSTnodeMap(List<String> mapStrings, Map<String, Node> MSTnodes, Node node){
		for(int i = 0; i<mapStrings.size(); i++) {
			if(MSTnodes.containsKey(mapStrings.get(i))) {
				System.out.println("node already added");
			}else {
				MSTnodes.put(mapStrings.get(i), node);
			}
		}	
		return MSTnodes;
	}
	
	Map<String, Edge> updateMSTedgeMap(Map<String, Edge> MSTedges, Edge edge){
		
		for(int i = 0; i<mapStrings.size(); i++) {
			if(MSTedges.containsKey(mapStrings.get(i))) {
				System.out.println("edge already added");
			}else {
				MSTedges.put(mapStrings.get(i), edge);
			}
		}	
		return MSTedges;
	}
	
	*//** Gets the minimum edge of the node via iteration 
	 * 
	 * @param node
	 * @param edgeMap
	 * @param mapStrings
	 * @return minEdge
	 *//*
	Edge getMinimumEdgeOfNode(Node node, Map<String, List<Edge>> edgeMap, List<String> mapStrings) {
		Iterator<Edge> itr = edgeMap.get(mapStrings.get(node.getNodeID())).iterator();
		int i = 0;
		Edge minEdge = new Edge(0);
		minEdge.setEdgeWeight(Integer.MAX_VALUE);
		while(itr.hasNext()) {
			if(edgeMap.get(mapStrings.get(node.getNodeID())).get(i).getEdgeWeight() < minEdge.getEdgeWeight()) {
				minEdge = edgeMap.get(mapStrings.get(node.getNodeID())).get(i);
			}
			itr.next();
		}
		return minEdge;
	}
	
	*//** This function makes accessing the other node connecting to the edge easier
	 * 
	 * @param edge
	 * @param node
	 * @return minNode
	 *//*
	Node getNodeOfMinimumEdge(Edge edge, Node node) {
		Node minNode = new Node(0);

		if(edge.getEdgeNodes().get(0).getNodeID() == node.getNodeID()) {
			minNode = edge.getEdgeNodes().get(1);
		}else {
			if(edge.getEdgeNodes().get(1).getNodeID() == node.getNodeID()) {
				minNode = edge.getEdgeNodes().get(0);
			}
		}
		return minNode;
	}
	
public static void main(String[] args) {
		
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
			
			// Populating Map with Nodes
			Node nodeA = new Node(0);
			Node nodeB = new Node(1);
			Node nodeC = new Node(2);
			Node nodeD = new Node(3);
			Node nodeE = new Node(4);
			Node nodeF = new Node(5);
			
			Map<String, Node> nodeMap = new ConcurrentHashMap<>();
			nodeMap.put("A", nodeA);
			nodeMap.put("B", nodeB);
			nodeMap.put("C", nodeC);
			nodeMap.put("D", nodeD);
			nodeMap.put("E", nodeE);
			nodeMap.put("F", nodeF);
			
			
			// Creating Edges for nodes
			Edge edge1 = new Edge(0, nodeA, nodeB, 5);
			Edge edge2 = new Edge(1, nodeA, nodeC, 6);
			Edge edge3 = new Edge(2, nodeB, nodeC, 2);
			Edge edge4 = new Edge(3, nodeB, nodeD, 9);
			Edge edge5 = new Edge(4, nodeC, nodeD, 8);
			Edge edge6 = new Edge(5, nodeD, nodeE, 2);
			Edge edge7 = new Edge(6, nodeD, nodeF, 3);
			Edge edge8 = new Edge(7, nodeE, nodeF, 5);
			Edge edge9 = new Edge(8, nodeF, nodeC, 14);
			
			List<Edge> aEdges = new ArrayList<Edge>();
			aEdges.add(edge1);
			aEdges.add(edge2);
			
			List<Edge> bEdges = new ArrayList<Edge>();
			bEdges.add(edge1);
			bEdges.add(edge3);
			
			List<Edge> cEdges = new ArrayList<Edge>();
			cEdges.add(edge2);
			cEdges.add(edge3);
			cEdges.add(edge5);
			cEdges.add(edge9);
			
			List<Edge> dEdges = new ArrayList<Edge>();
			dEdges.add(edge4);
			dEdges.add(edge5);
			dEdges.add(edge6);
			dEdges.add(edge7);
			
			List<Edge> eEdges = new ArrayList<Edge>();
			eEdges.add(edge6);
			eEdges.add(edge8);
			
			List<Edge> fEdges = new ArrayList<Edge>();
			fEdges.add(edge7);
			fEdges.add(edge8);
			fEdges.add(edge9);
			
			Map<String, List<Edge>> edgeMap = new ConcurrentHashMap<>();
			edgeMap.put("A", aEdges);
			edgeMap.put("B", bEdges);
			edgeMap.put("C", cEdges);
			edgeMap.put("D", dEdges);
			edgeMap.put("E", eEdges);
			edgeMap.put("F", fEdges);
			
			
			// Simple Debug			
			
			BoruvkaMST mst = new BoruvkaMST();
			mst.MST(nodeMap, edgeMap);
				
	}
}


*/