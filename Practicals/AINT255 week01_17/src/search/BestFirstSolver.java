package search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This class extends {@link BreadthFirstSolver} to add a heuristic-based
 * priority queue (instead of a vanilla queue).
 * 
 * @author Marcello
 */

public class BestFirstSolver extends BreadthFirstSolver {
	
	private PriorityQueue<AbstractState> queue = null;
	
	public BestFirstSolver() {
		queue = new PriorityQueue<AbstractState>(1,new Comparator<AbstractState>(){
			public int compare(AbstractState s1, AbstractState s2) {
				// Calculate the f(x) for each state using the sum of distance
				// and heuristic.
				return Double.compare(s1.getDistance()+s1.getHeuristic(),
									  s2.getDistance()+s2.getHeuristic());
			}
		});
	}
	

	protected void addState(AbstractState s) {
		if (!queue.contains(s))
			queue.offer(s);
	}

	protected boolean hasElements() {
		return !queue.isEmpty();
	}

	protected AbstractState nextState() {
		return queue.remove();
	}
	
	protected void clearOpen(){
		queue.clear();
	}
}
