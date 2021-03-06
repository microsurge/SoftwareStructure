/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        //throw new RuntimeException("implement me!");
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        //throw new RuntimeException("implement me!");
    	return (sides - 2)*180/(double)sides;
    }
    
    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //throw new RuntimeException("implement me!");
    	return  (int)Math.round(360/(180 - angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        //throw new RuntimeException("implement me!");
    	for(int i = 0; i < sides ; i++) {
    		turtle.forward(sideLength);
    		turtle.turn(calculateRegularPolygonAngle(sides));
    	}
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
            int targetX, int targetY) {
			//throw new RuntimeException("implement me!");
			if(currentX == targetX && currentY == targetY)
				return 0;
			//????????????
			double tarang =90 - Math.atan2(targetY - currentY, targetX - currentX)*180/Math.PI;
			if(tarang < 0)
				tarang += 360;
			//????????????????????????????????
			double adjustment = tarang -currentBearing;
			if(adjustment < 0)
				adjustment += 360;
			return adjustment;
	}

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        //throw new RuntimeException("implement me!");
    	List<Double> ajustment = new ArrayList<Double>();
    	double angle = 0;
    	int num = xCoords.size();
    	//??????????????????????????
    	if(num == 1)
    		return ajustment;
    	for(int i = 1; i < num; i++) {
    		ajustment.add(calculateBearingToPoint(angle,xCoords.get(i - 1), yCoords.get(i - 1), xCoords.get(i), yCoords.get(i)));
    		angle = (angle + calculateBearingToPoint(angle,xCoords.get(i - 1), yCoords.get(i - 1), xCoords.get(i), yCoords.get(i)))%360;
    	}
    	return ajustment;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
        //throw new RuntimeException("implement me!");
    	//????4????????
    	if(points.size() < 4)
    		return points;
    	Set<Point> ans = new HashSet<Point>();
    	Point leftmost = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
    	Point current;
    	Point temp = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
    	/*
    	 * ????????????????????????????
    	 * ????????????????????????????????????????????
    	 * */
    	for(Point p : points)//????????
    		if(p.x() < leftmost.x() || p.x() == leftmost.x() && p.y() < leftmost.y())
    			leftmost = p;
    	//????????????????????????????????????????
    	ans.add(leftmost);
    	current = leftmost;
    	double angle = 0;
    	do {
    		double min_adjustment = Double.MAX_VALUE;
    		for(Point p : points) {
    			//????????????????
    			if(p.equals(current))
    				continue;
    			//????????????????????????temp??
    			double adjustment = calculateBearingToPoint(angle,(int)current.x(), (int)current.y(), (int)p.x(), (int)p.y());
    			if(adjustment <min_adjustment ) {
    				min_adjustment = adjustment;
    				temp = p;
    			}
    			//??????????????????????????????????????????????????????????
    			else if(adjustment == min_adjustment && (p.x()<temp.x()&&temp.x()<current.x()||current.x()<temp.x()&&temp.x()<p.x()))
    					temp = p;
    		}
    		current = temp;
    		//????????????
    		angle = (angle + min_adjustment)%360;
    		ans.add(current);
    	}while(!current.equals(leftmost));
    	return ans;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
    	turtle.turn(90);
    	for(int j = 1; j <=5 ;j++) {
    		for(int i = 0; i < 5 ; i++) {
        		turtle.forward(200);
        		turtle.turn(144);
        	}
    		turtle.turn(72);
    	}
    	return;
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
        
    }

}
