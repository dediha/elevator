package com.example.demo.thread;

import com.example.demo.constants.ElevatorConstants;
import com.example.demo.enums.ElevatorDirection;
import com.example.demo.model.Elevator;

public class ElevatorRunningThread implements Runnable {
	private Elevator elevator;
	private boolean doStop = false;
	
	public ElevatorRunningThread(Elevator elevator) {
		setElevator(elevator);
	}

	public Elevator getElevator() {
		return elevator;
	}

	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	public boolean isDoStop() {
		return doStop;
	}

	public void setDoStop(boolean doStop) {
		this.doStop = doStop;
	}

	public synchronized void doStop() {
        this.doStop = true;
    }
	
	private synchronized boolean keepRunning() {
        return this.doStop == false;
    }


	@Override
	public void run() {
		while (keepRunning()) {
			try {
				// there is nothing to do
				ElevatorDirection currentDirection  = elevator.direction();
				if (currentDirection.equals(ElevatorDirection.STANDING)) {
					// time for thread to rest between iteration
					Thread.sleep(ElevatorConstants.ELEVATOR_CHECK_INTERVAL);				
				} else {
					// wait to simulate elevator moving
					Thread.sleep(ElevatorConstants.ELEVATOR_FLOOR_COMPLETION_TIME);	
					if (currentDirection.equals(ElevatorDirection.DOWN)) {
						// moving down
						elevator.moveDown();
					} else {
						// moving up
						elevator.moveUp();
					}
				}			
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
