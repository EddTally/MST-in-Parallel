package workingAttempt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import workingAttempt.MSTEdge;
import workingAttempt.MSTNode;
import workingAttempt.MSTree;

public class Main {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		int numThreads;
		//get input parameters
		try {
			numThreads =  Integer.parseInt(args[0]);//number of threads
		}catch(Exception e) {
			throw(e);
		}
		
		new Main().runMain(numThreads);
		
		long endTime = System.nanoTime();
		double time = ((double)(endTime - startTime))/1000000000.0;
		System.out.println("Execution of Program took: " + time + " seconds");
	}

	protected void runMain(int numThreads) {
		

		MSTree mstTree = initStartMSTree();
		mstTree.setThreadCount(numThreads);

		do {
			mstTree = mstTree.trimTree();
		} while (mstTree.getNodes().size() != 1);

		Set<MSTEdge> minEdges = new HashSet<>(mstTree.getMinEdges());

		System.out.println("Node - Node - Edge Weight");
		int i = 0;
		for (MSTEdge leftOverEdge : minEdges) {
			System.out.println(String.format("%s ------ %s \t %d", leftOverEdge.getOriginalStart(), leftOverEdge.getOriginalEnd(),
					leftOverEdge.getWeight()));
			i = i + leftOverEdge.getWeight();
		}
		System.out.println("Overall Weight of tree is: " + i);
	}

	private MSTree initStartMSTree() {
		Set<MSTNode> mstNodes = new HashSet<>();
		List<MSTEdge> mstEdges = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			mstNodes.add(new MSTNode(String.valueOf(i)));
		}

		addEdges(mstEdges, "0", "1", 4);
		addEdges(mstEdges, "0", "7", 8);
		addEdges(mstEdges, "1", "2", 8);
		addEdges(mstEdges, "1", "7", 11);
		addEdges(mstEdges, "7", "8", 7);
		addEdges(mstEdges, "7", "6", 1);
		addEdges(mstEdges, "6", "5", 2);
		addEdges(mstEdges, "6", "8", 6);
		addEdges(mstEdges, "8", "2", 2);
		addEdges(mstEdges, "5", "4", 10);
		addEdges(mstEdges, "5", "3", 14);
		addEdges(mstEdges, "5", "4", 10);
		addEdges(mstEdges, "2", "5", 4);
		addEdges(mstEdges, "2", "3", 7);
		addEdges(mstEdges, "3", "5", 14);
		addEdges(mstEdges, "3", "4", 9);

		return new MSTree(mstNodes).withEdges(mstEdges);
	}

	private void addEdges(List<MSTEdge> mstEdges, String start, String end, int weight) {
		mstEdges.add(new MSTEdge(start, end, weight));
		mstEdges.add(new MSTEdge(end, start, weight));
	}
}
