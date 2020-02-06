package search;

import java.util.Set;




public abstract class AbstractState  { 

	
	private AbstractState parent = null;
	private int distance = 0;
	
	public AbstractState() {
	}
	
	public AbstractState(AbstractState parent) {
		this.parent = parent;
		this.distance = parent.getDistance() + 1;
	}

	public AbstractState getParent() {
		return parent;
	}
	
	public int getDistance(){
		return distance;
	}
        
        public abstract boolean isSolution();
        
        public abstract Set<AbstractState> getPossibleMoves() ;
        
        public abstract double getHeuristic() ;

}
