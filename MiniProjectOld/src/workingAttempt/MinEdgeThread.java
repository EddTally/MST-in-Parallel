package workingAttempt;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinEdgeThread implements Runnable {
	
	private List<MSTEdge> list;
	private List<MSTEdge> nodes2EdgeList;
	
	MinEdgeThread(List<MSTEdge> list, List<MSTEdge> nodes2EdgeList){
		this.nodes2EdgeList = nodes2EdgeList;
		this.list = list;
	}

	@Override
	public void run() {	
		list.add(getMinEdgeForNode(nodes2EdgeList));
	}
	
	private MSTEdge getMinEdgeForNode(List<MSTEdge> edgeList) {
		return Collections.min(edgeList, new Comparator<MSTEdge>() {
			@Override
			public int compare(MSTEdge edge1, MSTEdge edge2) {
				return Integer.compare(edge1.getWeight(), edge2.getWeight());
			}
		});
	}
	
}
