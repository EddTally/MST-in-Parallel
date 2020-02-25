package workingAttempt;
/*package workingAttempt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class MSTreeLambdaVersion {
	private final Set<MSTNode> nodes;
	private List<MSTEdge> minEdges = new ArrayList<>();
	private Map<String, List<MSTEdge>> nodes2Edges = new HashMap<>();

	public Set<MSTNode> getNodes() {
		return nodes;
	}

	public MSTreeLambdaVersion(Set<MSTNode> nodes) {
		this.nodes = nodes;

	}

	public MSTreeLambdaVersion withEdges(List<MSTEdge> edges) {
		for (MSTEdge mstEdge : edges) {
			if (!nodes2Edges.containsKey(mstEdge.getStartNode())) {
				nodes2Edges.put(mstEdge.getStartNode(), new ArrayList<MSTEdge>());
			}

			nodes2Edges.get(mstEdge.getStartNode()).add(mstEdge);
		}

		return this;
	}

	public MSTreeLambdaVersion(Set<MSTNode> nodes, List<MSTEdge> minEdges) {
		this(nodes);

		this.minEdges = minEdges;
	}

	public MSTreeLambdaVersion trimTree() {
		List<MSTEdge> minEdges = findMinEdges();
		Set<MSTNode> mergedNodes = getNodesForMinEdges(minEdges);

		List<MSTEdge> combinedMinEdges = new ArrayList<>();
		combinedMinEdges.addAll(this.minEdges);
		combinedMinEdges.addAll(minEdges);

		return new MSTreeLambdaVersion(mergedNodes, combinedMinEdges).withUpdateEdges(this.nodes2Edges);
	}

	public List<MSTEdge> findMinEdges() {
		return this.nodes.parallelStream().map(node -> getMinEdgeForNode(node)).collect(Collectors.toList());
	}

	public List<MSTEdge> getMinEdges() {
		return minEdges;
	}

	public void setMinEdges(List<MSTEdge> minEdges) {
		this.minEdges = minEdges;
	}

		private MSTEdge getMinEdgeForNode(MSTNode node) {
		return nodes2Edges.get(node.getKey()).stream().min(new Comparator<MSTEdge>() {
			@Override
			public int compare(MSTEdge edge1, MSTEdge edge2) {
				return Integer.compare(edge1.getWeight(), edge2.getWeight());
			}
		}).get();

	}

		private Set<MSTNode> getNodesForMinEdges(List<MSTEdge> edges) {
		Set<MSTNode> mstNodes = new HashSet<>();

		for (MSTEdge edge : edges) {
			Optional<MSTNode> node = mstNodes.stream().filter(mstNode -> mstNode.getNodes().contains(edge.getStartNode()) || mstNode.getNodes().contains(edge.getEndNode()))
			.findFirst();
			
			if(node.isPresent()) {
				node.get().addNode(edge.getStartNode());
				node.get().addNode(edge.getEndNode());
			} else {
				MSTNode mstNode = new MSTNode(edge.getStartNode());
				mstNode.addNode(edge.getEndNode());
				mstNodes.add(mstNode);
			}
		}

		return mstNodes;
	}
	
		private MSTreeLambdaVersion withUpdateEdges(Map<String, List<MSTEdge>> oldEdges) {
		this.nodes2Edges.clear();

		Map<String, MSTNode> helperMap = new HashMap<>();
		Map<String, List<MSTEdge>> tempEdges = new HashMap<>();

		oldEdges.values().stream().flatMap(oldEdgesInMap -> oldEdgesInMap.stream())
				.filter(oldEdge -> !isNodeInternalEdge(oldEdge)).forEach(oldEdge -> {
					MSTNode startNode = helperMap.getOrDefault(oldEdge.getStartNode(),
							findMSTNodeForNode(oldEdge.getStartNode()));
					MSTNode endNode = helperMap.getOrDefault(oldEdge.getEndNode(),
							findMSTNodeForNode(oldEdge.getEndNode()));

					MSTEdge edge = new MSTEdge(startNode.getKey(), endNode.getKey(), oldEdge.getWeight());

					if (!tempEdges.containsKey(edge.getStartNode())) {
						tempEdges.put(edge.getStartNode(), new ArrayList<>());
					}

					tempEdges.get(edge.getStartNode()).add(edge);
				});
		this.nodes2Edges = tempEdges;

		return this;
	}
	

	private MSTNode findMSTNodeForNode(String node) {
		return nodes.stream().filter(mstNode -> mstNode.getNodes().contains(node)).findFirst().get();
	}
	// check to see if correct
	*//**
	 * Check if the given Edge is connecting shared Nodes
	 * 
	 * @param edge
	 * @return
	 *//*
	public boolean isNodeInternalEdge(MSTEdge edge) {
		return nodes.stream().anyMatch(
				node -> node.getNodes().contains(edge.getStartNode()) && node.getNodes().contains(edge.getEndNode()));
	}
}
*/