package chapter2.agent_AB_task2;

import chapter2.agent_AB_task2.Environment.LocationState;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY, Environment.LocationState.DIRTY, Environment.LocationState.CLEAN);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_A);

		env.stepUntilDone();
	}
}
