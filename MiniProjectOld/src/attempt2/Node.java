package attempt2;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	int ID;
	List<Edge> nodeEdges = new ArrayList<>();
	
	Node(int ID){
		this.ID = ID;
	}
	
	Node(int ID, Edge...edges){
		this.ID = ID;
		
		for (Edge arg : edges) {
			this.nodeEdges.add(arg);
		}
	}
	
	public int getNodeID() {
		return this.ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public List<Edge> getNodeEdges(){
		return this.nodeEdges;
	}
	public void clearNodeEdges() {
		this.nodeEdges.clear();
	}
	public void setNodeEdges(Edge e) {
		this.nodeEdges.add(e);
	}
	
}