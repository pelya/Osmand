package net.osmand.router;

import net.osmand.binary.BinaryMapDataObject;
import net.osmand.binary.BinaryMapIndexReader.TagValuePair;
import net.osmand.osm.MapRenderingTypes;
import net.osmand.router.BinaryRoutePlanner.RouteSegment;

public abstract class VehicleRouter {

	/**
	 * Accepts line to use it for routing
	 * 
	 * @param pair
	 * @return
	 */
	public abstract boolean acceptLine(TagValuePair pair);

	/**
	 * Accepts point to use it for routing
	 * 
	 * @param pair
	 * @return
	 */
	public abstract boolean acceptPoint(TagValuePair pair);

	
	public boolean isOneWay(int highwayAttributes) {
		return MapRenderingTypes.isOneWayWay(highwayAttributes) || MapRenderingTypes.isRoundabout(highwayAttributes);
	}

	/**
	 * return delay in seconds
	 */
	public double defineObstacle(BinaryMapDataObject road, int point) {
		// no obstacles
		return 0;
	}

	/**
	 * return speed in m/s for vehicle
	 */
	public abstract double defineSpeed(BinaryMapDataObject road);

	/**
	 * Used for A* routing to calculate g(x)
	 * 
	 * @return minimal speed at road in m/s
	 */
	public abstract double getMinDefaultSpeed();

	/**
	 * Used for A* routing to predict h(x) : it should be great any g(x)
	 * 
	 * @return maximum speed to calculate shortest distance
	 */
	public abstract double getMaxDefaultSpeed();

	/**
	 * Calculate turn time 
	 */
	public abstract double calculateTurnTime(int middley, int middlex, int x, int y, RouteSegment segment, RouteSegment next, int j) ;
}