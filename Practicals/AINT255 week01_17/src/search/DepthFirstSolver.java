package search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class defines a simple stack-based depth-first solver.
 *
 * @author Marcello
 */
public class DepthFirstSolver extends AbstractSolver {

    private Stack<AbstractState> openStack = new Stack<AbstractState>();
    private Set<AbstractState> closedSet = new HashSet<AbstractState>();

    public DepthFirstSolver() {
        solverName = "Depth First Seach Solver";
    }

    public List<AbstractState> solve(AbstractState initialState) {
        // Reset closed set
        closedSet.clear();
        clearOpenStack();

        addStateToOpenStack(initialState);

        while (openStackIsNotEmpty()) {

            // get the next state
            AbstractState nextSate = getStateFromTopOfOpenStack();

            // is this state is a solution
            // then find the path to the solution
            // as a list and return that list
            if (nextSate.isSolution()) {
                return findPath(nextSate);
            }

            // this state is not a solution 
            // so add it to the closed list
            closedSet.add(nextSate);

            // now get all the possible moves from the nextstate  
            Iterable<AbstractState> nextPossiblemoves = nextSate.getPossibleMoves();

            // go through all possible moves
            // if the move is not on the closed list add it to the open list
            for (AbstractState move : nextPossiblemoves) {
                if (!closedSet.contains(move)) {
                    addStateToOpenStack(move);
                }
            }
        }
        return null;
    }

    public int getVisitedStateCount() {
        return closedSet.size();
    }

    public int getToVisitStateCount() {
        return openStack.size();
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
     * Stack specific methods
     */
    protected void addStateToOpenStack(AbstractState s) {
            openStack.push(s);
    }

    protected boolean openStackIsNotEmpty() {
        return !openStack.empty();
    }

    protected AbstractState getStateFromTopOfOpenStack() {
        return openStack.pop();
    }

    protected void clearOpenStack() {
        openStack.clear();
    }


}
