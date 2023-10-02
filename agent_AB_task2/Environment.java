package chapter2.agent_AB_task2;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public int point = 0;

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		this.envState.setAgentLocation(location);
		this.envState.getLocationState(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		if (agent.execute(getPerceptSeenBy()).equals(Environment.SUCK_DIRT)) {
			this.envState.setLocationState(envState.getAgentLocation(), Environment.LocationState.CLEAN);
			point = point + 500;
		}
		// Move right
		if (action.equals(Environment.MOVE_RIGHT)) {
			if (envState.getAgentLocation().equals(Environment.LOCATION_A)) {
				this.envState.setAgentLocation(LOCATION_B);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_B));
				point = point - 10;
			} else if (envState.getAgentLocation().equals(Environment.LOCATION_D)) {
				this.envState.setAgentLocation(LOCATION_C);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_C));
				point = point - 10;
			} else {
				point = point - 100;
			}
		}
		// ---------------
		// Move left
		if (action.equals(Environment.MOVE_LEFT)) {
			if (envState.getAgentLocation().equals(Environment.LOCATION_B)) {
				this.envState.setAgentLocation(LOCATION_A);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_A));
				point = point - 10;
			} else if (envState.getAgentLocation().equals(Environment.LOCATION_C)) {
				this.envState.setAgentLocation(LOCATION_D);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_D));
				point = point - 10;
			} else {
				point = point - 100;
			}
		}
		// ----------------
		// Move up
		if (action.equals(Environment.MOVE_UP)) {
			if (envState.getAgentLocation().equals(Environment.LOCATION_C)) {
				this.envState.setAgentLocation(LOCATION_B);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_B));
				point = point - 10;
			} else if (envState.getAgentLocation().equals(Environment.LOCATION_D)) {
				this.envState.setAgentLocation(LOCATION_A);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_A));
				point = point - 10;
			} else {
				point = point - 100;
			}
		}
		// -----------------
		// Move down
		if (action.equals(Environment.MOVE_DOWN)) {
			if (envState.getAgentLocation().equals(Environment.LOCATION_A)) {
				this.envState.setAgentLocation(LOCATION_D);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_D));
				point = point - 10;
			} else if (envState.getAgentLocation().equals(Environment.LOCATION_B)) {
				this.envState.setAgentLocation(LOCATION_C);
				this.envState.setLocationState(envState.getAgentLocation(), envState.getLocationState(LOCATION_C));
				point = point - 10;
			} else {
				point = point - 100;
			}
		}
		// ------------

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

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction + "\tPoint: " + this.point);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
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
		int i = 1;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
			System.out.println("----------------------");
//			if (i == 20) {
//				break;
//			}
		}
	}
}
