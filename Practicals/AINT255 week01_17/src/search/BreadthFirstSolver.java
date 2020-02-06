package search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * This class defines a simple queue-based breadth first solver.
 *
 * @author Marcello
 */
public class BreadthFirstSolver extends AbstractSolver {

    // this is the open list represented as a queue
    private Queue<AbstractState> openQueue = new LinkedList<AbstractState>();

    private Set<AbstractState> closedSet = new HashSet<AbstractState>();

    public BreadthFirstSolver() {

        solverName = "Breadth First Solver";
    }

    public List<AbstractState> solve(AbstractState initialState) {

        // Reset closed set
        closedSet.clear();

        clearOpenQueue();

        addStateToEndofOpenQueue(initialState);

        while (openQueueIsNotEmpty()) {

            // get the next state
            AbstractState nextState = getStateFromHeadOfOpenQueue();

            // if this state is a solution
            // then find the path to the solution
            // as a list and return that list
            if (nextState.isSolution()) {
                return findPath(nextState);
            }

            // this state is not a solution 
            // so add it to the closed list
            closedSet.add(nextState);

            // now get all the possible moves from
            // the state  nextState
            Iterable<AbstractState> moves = nextState.getPossibleMoves();

            for (AbstractState move : moves) {

                if (!closedSet.contains(move)) {

                    addStateToEndofOpenQueue(move);
                }

            }
        }
        return null;
    }

    public int getVisitedStateCount() {
        return closedSet.size();
    }

    public int getToVisitStateCount() {
        return openQueue.size();
    }

    private List<AbstractState> findPath(AbstractState solution) {

        LinkedList<AbstractState> path = new LinkedList<AbstractState>();

        while (solution != null) {
            path.addFirst(solution);
            solution = solution.getParent();
        }
        return path;
    }

    /*
     * Queue specific methods
     */
    protected boolean openQueueIsNotEmpty() {
        return !openQueue.isEmpty();
    }

    protected void addStateToEndofOpenQueue(AbstractState s) {
       
            //  Inserts the specified element into this queue 
            // if it is possible to do so immediately without violating capacity restrictions.
            openQueue.offer(s);
            
    }

    protected AbstractState getStateFromHeadOfOpenQueue() {
        // Retrieves and removes the head of this queue.
        return openQueue.remove();
    }

    protected void clearOpenQueue() {
        openQueue.clear();
    }
}
