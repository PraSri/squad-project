/**
 * 
 */
package com.squad.parkinglot.strategy;

/**
 * @author Prakhar
 * 
 */
public interface ParkingStrategy {

	public void add(int i);

	public int getSlot();

	public void removeSlot(int slot);

}
