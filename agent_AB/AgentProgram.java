package chapter2.agent_AB;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		if(p.getLocationState().equals(Environment.LocationState.DIRTY)) {
			return Environment.SUCK_DIRT;
		} else {
			if (p.getAgentLocation().equals(Environment.LOCATION_A)) {
				return Environment.MOVE_RIGHT;
			}
			else {
				return Environment.MOVE_LEFT;
			}
		}
		
	}
}