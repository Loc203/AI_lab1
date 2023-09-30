package chapter2.agent_AB;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
	private String location;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		this.location = location;
		envState.setAgentLocation(location);
		envState.getLocationState(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		if (agent.execute(getPerceptSeenBy()).equals(Environment.SUCK_DIRT)) {
			this.envState.setLocationState(envState.getAgentLocation(), Environment.LocationState.CLEAN);
		} else {
			if (agent.execute(getPerceptSeenBy()).equals(Environment.MOVE_RIGHT)) {
				envState.setAgentLocation(LOCATION_B);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_B));
			} else if (agent.execute(getPerceptSeenBy()).equals(Environment.MOVE_LEFT)) {
				envState.setAgentLocation(LOCATION_A);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_A));
			}
		}
		return this.envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		Percept percept = new Percept(envState.getAgentLocation(),
				envState.getLocationState(envState.getAgentLocation()));
		return percept;
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
