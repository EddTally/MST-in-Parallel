package workingAttempt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class withUpdateEdgesThread implements Runnable{

		private Map<String, MSTNode> helperMap;
		private Map<String, List<MSTEdge>> tempEdges;
		private MSTEdge internalEdge;
		private Map<String, List<MSTEdge>> nodes2Edges;
		private final Set<MSTNode> nodes;
		
		withUpdateEdgesThread(Map<String, MSTNode> helperMap, Map<String, List<MSTEdge>> tempEdges, 
				MSTEdge internalEdge, Map<String, List<MSTEdge>> nodes2Edges, Set<MSTNode> nodes){
			this.helperMap = helperMap;
			this.tempEdges = tempEdges;
			this.internalEdge = internalEdge;
			this.nodes2Edges = nodes2Edges;
			this.nodes = nodes;
		}

		@Override
		public void run() {	
			nodes2Edges.clear();
			nodes2Edges.putAll(withUpdatesEdgesThread(helperMap, tempEdges, internalEdge, nodes));
		}
		
		private synchronized Map<String, List<MSTEdge>> withUpdatesEdgesThread(Map<String, MSTNode> helperMap, Map<String, List<MSTEdge>> tempEdges, 
				MSTEdge internalEdge, Set<MSTNode> nodes){
			if(!isNodeInternalEdge(internalEdge)) {

			MSTNode startNode = new MSTNode();
			if(helperMap.containsKey(internalEdge.getStartNode())) {
				startNode = helperMap.get(internalEdge.getStartNode());
			}else {
				startNode = findMSTNodeForNode(internalEdge.getStartNode());
			}

			MSTNode endNode = new MSTNode();
			if(helperMap.containsKey(internalEdge.getStartNode())) {
				endNode = helperMap.get(internalEdge.getEndNode());
			}else {
				endNode = findMSTNodeForNode(internalEdge.getEndNode());
			}

			MSTEdge edge = new MSTEdge(startNode.getKey(), endNode.getKey(), internalEdge.getWeight())
					.withOriginalStartAndEnd(internalEdge.getOriginalStart(), internalEdge.getOriginalEnd());
			
			if (!tempEdges.containsKey(edge.getStartNode())) {
				tempEdges.put(edge.getStartNode(), new ArrayList<MSTEdge>());
			}

			tempEdges.get(edge.getStartNode()).add(edge);
		}
			return tempEdges;
	}
	/**
	 * Check if the given Edge is connecting shared Nodes
	 * 
	 * @param edge
	 * @return
	 */
	public boolean isNodeInternalEdge(MSTEdge edge) {

		for(MSTNode node: nodes) {
			if(node.getNodes().contains(edge.getStartNode()) && node.getNodes().contains(edge.getEndNode())) {
				return true;
			}
		}
		return false;
}

	private MSTNode findMSTNodeForNode(String node) {
		MSTNode n = new MSTNode();
		for(MSTNode mstNode: nodes) {
			if(mstNode.getNodes().contains(node)) {
				n = mstNode;
				break;
			}			
		}
		return n;
	}

}
