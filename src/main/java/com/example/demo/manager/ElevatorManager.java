package com.example.demo.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.example.demo.interfaces.ElevatorSystemFacade;
import com.example.demo.model.Elevator;
import com.example.demo.thread.ElevatorRunningThread;

public class ElevatorManager implements ElevatorSystemFacade {

	private List<Elevator> elevators;
	private Map<String, ElevatorRunningThread> runningElevatorThreads;
		
	
	public ElevatorManager(List<Elevator> elevators, Queue<Integer> elevatorOuterRequests) {
		initElevators();	
		startElevatorsThreads(elevators);
	}
	
	private void initElevators() {
		// read elevators from repository
		//TODO
		
	}

	private void startElevatorsThreads(List<Elevator> elevators) {
		// TODO Auto-generated method stub
		runningElevatorThreads = new HashMap<String, ElevatorRunningThread>();
		
		for (Elevator elevator : elevators) {
			String key = String.valueOf(elevator.getId());
			ElevatorRunningThread elevatorThread = new ElevatorRunningThread(elevator);
			Thread thread = new Thread(elevatorThread);
			thread.start();
			runningElevatorThreads.put(key, elevatorThread);
		}
	}

	/**
	 *  	handle calling an elevator from a lobby button 
	 */
	public void addOuterRequest(Integer floor) {
		
	}
	
	/**
	 *  	handle calling an elevator from inside the elevator 
	 */
	public void addInsideRequest(Integer elevatorId, Integer floor) {
		
	}
	
	public void addElevator(Integer minFloor, Integer maxFloor, Integer currentFloor,
					Queue<Integer> destinationFloors) {
		elevators.add(
				new Elevator(minFloor, maxFloor, currentFloor, destinationFloors));
	}

	
	
}
