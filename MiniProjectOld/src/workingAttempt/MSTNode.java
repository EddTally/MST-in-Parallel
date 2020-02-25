package workingAttempt;

import java.util.HashSet;
import java.util.Set;

public class MSTNode {
	private Set<String> nodes = new HashSet<>();
	private String key;
	
	public MSTNode() {
		
	}
	
	public MSTNode(String node) {
		nodes.add(node);
	}

	public void addNode(String node) {
		this.nodes.add(node);
	}

	public Set<String> getNodes() {
		return nodes;
	}

	public void setNodes(Set<String> nodes) {
		this.nodes = nodes;
	}

	public String getKey() {
		if(this.key == null) {
			this.key = buildKey();
		}
		
		return this.key;
	}
	
	private String buildKey() {
		String key = null;
		
		for(String node: this.nodes) {
			if(key == null) {
				key = node;
			} else {
				key = key + ";" + node;
			}
		}
		
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
