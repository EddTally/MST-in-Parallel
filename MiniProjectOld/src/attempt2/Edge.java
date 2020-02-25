package attempt2;

public class Edge{
	
	int ID, weight;
	
	Edge(int ID){
		this.ID = ID;
	}
	
	Edge(int ID, int weight){
		this.ID = ID;
		this.weight = weight;
	}
	
	public int getEdgeName() {
		return this.ID;
	}
	
	public int getEdgeWeight() {
		return this.weight;
	}
	public void setEdgeWeight(int weight) {
		this.weight = weight;
	}
	
}
