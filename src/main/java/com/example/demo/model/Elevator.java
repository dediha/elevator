package com.example.demo.model;

import java.util.LinkedList;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.ElevatorDirection;
import com.example.demo.exception.IllegalFloorException;

@Entity
@Table(name = "Elevator")
public class Elevator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private Integer minFloor;
	private Integer maxFloor;
	private Integer currentFloor;
	private Queue<Integer> destinationFloors;
	
	
	
	public long getId() {
		return id;
	}

	public Elevator(Integer minFloor, Integer maxFloor, Integer currentFloor,
			Queue<Integer> destinationFloors) {
				
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.currentFloor = currentFloor;
		if (destinationFloors != null) {
			this.destinationFloors = new LinkedList<Integer>();	
		} else {
			this.destinationFloors = destinationFloors;
		}
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMinFloor() {
		return minFloor;
	}

	public void setMinFloor(Integer minFloor) {
		this.minFloor = minFloor;
	}

	public Integer getMaxFloor() {
		return maxFloor;
	}

	public void setMaxFloor(Integer maxFloor) {
		this.maxFloor = maxFloor;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Queue<Integer> getDestinationFloors() {
		return destinationFloors;
	}

	public void setDestinationFloors(Queue<Integer> destinationFloors) {
		this.destinationFloors = destinationFloors;
	}

	public int nextDestionation(){
		return this.destinationFloors.peek();
	}

	public int currentFloor(){
		return this.currentFloor;
	}
	
	public void popDestination(){
		this.destinationFloors.remove();
	}
	  
	public void addNewDestination(Integer destination) throws IllegalFloorException {
		if (destination <= maxFloor || destination >= minFloor) {
			this.destinationFloors.add(destination);			
		} else {
			throw new IllegalFloorException("Floor request extends elevator's limits!!!");
		}
	}
	
	  
	public void moveUp() {
		currentFloor++;
  	}
	
	public void moveDown() {
		currentFloor--;
	}
	
	  
	public ElevatorDirection direction() {
		if (destinationFloors.size() > 0){
			if (currentFloor < destinationFloors.peek()){
				return ElevatorDirection.UP;
			} else if (currentFloor > destinationFloors.peek()) {
				return ElevatorDirection.DOWN;
			}
	    }
	    return ElevatorDirection.STANDING;
	}  
	  
}
