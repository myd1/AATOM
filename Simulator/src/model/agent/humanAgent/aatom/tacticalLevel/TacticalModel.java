package model.agent.humanAgent.aatom.tacticalLevel;

import java.util.Collection;
import java.util.List;

import model.agent.humanAgent.HumanAgent;
import model.agent.humanAgent.aatom.operationalLevel.action.movement.MovementModule;
import model.agent.humanAgent.aatom.operationalLevel.observation.ObservationModule;
import model.agent.humanAgent.aatom.strategicLevel.reasoning.planning.ActivityPlanner;
import model.agent.humanAgent.aatom.tacticalLevel.activity.Activity;
import model.agent.humanAgent.aatom.tacticalLevel.activity.ActivityModule;
import model.agent.humanAgent.aatom.tacticalLevel.navigation.NavigationModule;
import model.environment.position.Position;
import model.map.Map;
import simulation.simulation.util.Updatable;

/**
 * The tactical model of a human agent.
 * 
 * @author S.A.M. Janssen
 */
public abstract class TacticalModel implements Updatable {

	/**
	 * The activity module.
	 */
	private ActivityModule activityModule;
	/**
	 * The navigation module.
	 */
	private NavigationModule navigationModule;

	/**
	 * Creates a new tactical model.
	 * 
	 * @param activityModule
	 *            The activity module.
	 * @param navigationModule
	 *            The navigation module.
	 */
	public TacticalModel(ActivityModule activityModule, NavigationModule navigationModule) {
		this.activityModule = activityModule;
		this.navigationModule = navigationModule;
	}

	/**
	 * Gets the active {@link Activity}.
	 * 
	 * @return The active {@link Activity}.
	 */
	public Activity getActiveActivity() {
		return activityModule.getActiveActivity();
	}

	/**
	 * Gets a {@link Collection} of {@link Activity}s.
	 * 
	 * @return The {@link Activity}s.
	 */
	public Collection<Activity> getActivities() {
		return activityModule.getActivities();
	}

	/**
	 * Gets the activity module.
	 * 
	 * @return The activity module.
	 */
	public ActivityModule getActivityModule() {
		return activityModule;
	}

	/**
	 * Gets the goal {@link Position}.
	 * 
	 * @return The goal position.
	 */
	public Position getGoalPosition() {
		return navigationModule.getGoalPosition();
	}

	/**
	 * Gets the list of goal positions {@link Position}.
	 * 
	 * @return The goal positions.
	 */
	public List<Position> getGoalPositions() {
		return navigationModule.getGoalPositions();
	}

	/**
	 * Gets the navigation module.
	 * 
	 * @return The navigation module.
	 */
	public NavigationModule getNavigationModule() {
		return navigationModule;
	}

	/**
	 * Checks if the agent reached its goal {@link Position}.
	 * 
	 * @return True if the goal is reached, false otherwise.
	 */
	public boolean getReachedGoal() {
		return navigationModule.getReachedGoal();
	}

	/**
	 * Initializes the {@link HumanAgent}.
	 * 
	 * @param map
	 *            The map.
	 * @param agent
	 *            The agent.
	 * @param movement
	 *            The movement model.
	 * @param observations
	 *            The observation module.
	 * @param planner
	 *            The activity planner.
	 * @param activities
	 *            The activities.
	 */
	public void init(Map map, HumanAgent agent, MovementModule movement, ObservationModule observations,
			ActivityPlanner planner, Collection<Activity> activities) {
		navigationModule.init(map, movement, activityModule, observations);
		activityModule.init(map, agent, movement, observations, activities, planner, navigationModule);
	}

	/**
	 * Determines if the agent is queuing.
	 * 
	 * @return True if he is queuing, false otherwise.
	 */
	public boolean isQueuing() {
		return activityModule.isQueuing();
	}

	/**
	 * Sets the goal position.
	 * 
	 * @param position
	 *            The goal position.
	 */
	public void setGoal(Position position) {
		navigationModule.setGoal(position);
	}

	/**
	 * Sets the agent in front of the queue.
	 */
	public void setInFrontOfQueue() {
		activityModule.setInFrontOfQueue();
	}

	/**
	 * Sets the agents queuing mode.
	 * 
	 * @param time
	 *            The time. If the time equals -1, the agent queues
	 *            indefinitely.
	 */
	public void setQueuing(double time) {
		activityModule.setQueuing(time);
	}

	/**
	 * Sets a short term goal {@link Position} for the agent. It is to be
	 * executed right away.
	 * 
	 * @param position
	 *            The goal position.
	 */
	public void setShortTermGoal(Position position) {
		navigationModule.setShortTermGoal(position);
	}

	/**
	 * Sets a set of short term goal {@link Position}s for the agent. It is to
	 * be executed right away.
	 * 
	 * @param positions
	 *            The goal positions.
	 */
	public void setShortTermGoals(List<Position> positions) {
		navigationModule.setShortTermGoals(positions);
	}

	@Override
	public void update(int timeStep) {
		// activity
		activityModule.update(timeStep);

		// navigation
		navigationModule.update(timeStep);
	}
}
