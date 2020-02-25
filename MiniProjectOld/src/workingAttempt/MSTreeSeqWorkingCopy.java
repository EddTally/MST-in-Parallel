package workingAttempt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MSTreeSeqWorkingCopy {
	private final Set<MSTNode> nodes;
	private List<MSTEdge> minEdges = new ArrayList<>();
	private Map<String, List<MSTEdge>> nodes2Edges = new HashMap<>();
	private int threadCount = 1;

	
	public void setThreadCount(int t) {
		this.threadCount = t;
	}
	public Set<MSTNode> getNodes() {
		return nodes;
	}

	public MSTreeSeqWorkingCopy(Set<MSTNode> nodes) {
		this.nodes = nodes;
	}

	public MSTreeSeqWorkingCopy withEdges(List<MSTEdge> edges) {
		for (MSTEdge mstEdge : edges) {
			if (!nodes2Edges.containsKey(mstEdge.getStartNode())) {
				nodes2Edges.put(mstEdge.getStartNode(), new ArrayList<MSTEdge>());
			}

			nodes2Edges.get(mstEdge.getStartNode()).add(mstEdge);
		}

		return this;
	}

	public MSTreeSeqWorkingCopy(Set<MSTNode> nodes, List<MSTEdge> minEdges) {
		this(nodes);
		
		this.minEdges = minEdges;
	}

	public MSTreeSeqWorkingCopy trimTree() {
		List<MSTEdge> minEdges = findMinEdges();
		Set<MSTNode> mergedNodes = getNodesForMinEdges(minEdges);

		List<MSTEdge> combinedMinEdges = new ArrayList<>();
		combinedMinEdges.addAll(this.minEdges);
		combinedMinEdges.addAll(minEdges);

		return new MSTreeSeqWorkingCopy(mergedNodes, combinedMinEdges).withUpdateEdges(this.nodes2Edges);
	}

	// Parallelise this
	public List<MSTEdge> findMinEdges() {
		
		List<MSTEdge> list = Collections.synchronizedList(new ArrayList<MSTEdge>());
		
			for(MSTNode node: this.nodes) {
				list.add(getMinEdgeForNode(node));
			}
		return list;
	}
	
	public List<MSTEdge> getMinEdges() {
		return minEdges;
	}

	public void setMinEdges(List<MSTEdge> minEdges) {
		this.minEdges = minEdges;
	}

	private MSTEdge getMinEdgeForNode(MSTNode node) {
		return Collections.min(nodes2Edges.get(node.getKey()), new Comparator<MSTEdge>() {
			@Override
			public int compare(MSTEdge edge1, MSTEdge edge2) {
				return Integer.compare(edge1.getWeight(), edge2.getWeight());
			}
		});
	}

	/* Check that this is correct */
	private Set<MSTNode> getNodesForMinEdges(List<MSTEdge> edges) {
		Set<MSTNode> mstNodes = new HashSet<>();

		for (MSTEdge edge : edges) {
		    MSTNode mstNode = null;
		    for(MSTNode node:mstNodes) {
		        if(node.getNodes().contains(edge.getStartNode()) 
		            || node.getNodes().contains(edge.getEndNode())) {
		            mstNode = node;
		            break;
		        }   
		    }
		    
		    if(mstNode == null) {
		        mstNode = new MSTNode();
		        mstNodes.add(mstNode);
		    }
		    
		    mstNode.addNode(edge.getStartNode());
		    mstNode.addNode(edge.getEndNode());
		}
		return mstNodes;
	}

	private MSTreeSeqWorkingCopy withUpdateEdges(Map<String, List<MSTEdge>> oldEdges) {
		this.nodes2Edges.clear();

		Map<String, MSTNode> helperMap = new HashMap<>();
		Map<String, List<MSTEdge>> tempEdges = new HashMap<>();

		List<MSTEdge> oldEdgesInMap = new ArrayList<>();

		for(String key: oldEdges.keySet()) {
			oldEdgesInMap.addAll(oldEdges.get(key));
		}

		for(MSTEdge internalEdge:oldEdgesInMap) {
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
		}
		this.nodes2Edges = tempEdges;

		return this;
	}
	
	// Check if correct
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
	
	// check to see if correct
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
}
