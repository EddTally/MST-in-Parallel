package workingAttempt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MSTEdge {
	private String startNode;
	private String endNode;
	private int weight;

	private String originalStart;
	private String originalEnd;

	public MSTEdge(String startNode, String endNode, int weight) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}

	public MSTEdge withOriginalStartAndEnd(String originalStart, String originalEnd) {
		this.setOriginalStart(originalStart);
		this.setOriginalEnd(originalEnd);

		return this;
	}

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setOriginalEnd(String originalEnd) {
		this.originalEnd = originalEnd;
	}

	public String getOriginalStart() {
		if (originalStart != null) {
			return originalStart;
		} else {
			return startNode;
		}
	}

	public void setOriginalStart(String originalStart) {
		this.originalStart = originalStart;
	}

	public String getOriginalEnd() {
		if (originalEnd != null) {
			return originalEnd;
		} else {
			return endNode;
		}
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof MSTEdge)) {
			return false;
		}
		MSTEdge edge = (MSTEdge) o;

		return (startNode.equals(edge.getStartNode()) && endNode.equals(edge.getEndNode()))
				|| (endNode.equals(edge.getStartNode()) && startNode.equals(edge.getEndNode()))
						&& weight == edge.getWeight();
	}

	@Override
	public int hashCode() {
		List<String> nodes = Arrays.asList(startNode, endNode);
		Collections.sort(nodes);

		return Objects.hash(nodes.get(0), nodes.get(1), weight);
	}
}