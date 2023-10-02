package chapter2.agent_AB_task2;

import java.util.Random;

public class AgentProgram {
	/*
	 * 0: move up. 
	 * 1: move right. 
	 * 2: move down. 
	 * 3: move left. 
	 * Theo chieu kim dong ho!
	 */
	public Action execute(Percept p) {// location, status
		if (p.getLocationState().equals(Environment.LocationState.DIRTY)) {
			return Environment.SUCK_DIRT;
		} else {
			Random rand = new Random();
			int randomMove = rand.nextInt(4);
			if (randomMove == 0) {
				return Environment.MOVE_UP;
			} else if (randomMove == 1) {
				return Environment.MOVE_RIGHT;
			} else if (randomMove == 2) {
				return Environment.MOVE_DOWN;
			} else {
				return Environment.MOVE_LEFT;
			}

		}
	}
}