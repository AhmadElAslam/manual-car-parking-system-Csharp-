/*
 * Which parameters we have for our car (Settings page)
 * */

public class clCar {
	
	double[] Array = new SettingsPage().GetArray(); /**/
	public double rMin = Array[2];// Minimum turn radius (m)
	public double maxAngle = Array[3];// Steering wheel max angle (degrees) (+/-) + Clockwise
	public double Length = Array[0];// Length of the car (m)
	public double Width = Array[1];;// Width of the car (m)
	public double minDistance = 0.2; // Minimal distance from the front + end car (border) (m)
	
	/* Test
	public double rMin = 4;// Minimum turn radius (m)
	public double maxAngle = 180;// Steering wheel max angle (degrees) (+/-) + Clockwise
	public double Length = 4.7;// Length of the car (m)
	public double Width = 1.9;// Width of the car (m)
	public double minDistance = 0.2; // Minimal distance from the front + end car (border) (m)
	*/
	
	public clState State = new clState(0, 0, 0);
	
	/*
	 * Checks if it's possible to turn the steering wheel into a certain degree
	 */
	public int errRadius(double angDeg)
    {
        angDeg = Math.abs(angDeg);
        if (angDeg > maxAngle) return 1;// impossible
        if (angDeg == 0) return 2;// infinity - we can't calculate radius for a direct movement (radius is infinite)
        return 0;
    }
	
	/*
	 * Calculates the radius for a certain degree of a steering wheel turn 
	 */
	public double dRadius(double angDeg)
	{
		angDeg = Math.abs(angDeg);
        return rMin * maxAngle / angDeg;
    }

	/*
	 * Calculates the degree of a steering wheel for a given radius
	 */
	public double angDeg_get(double r) {
		if (r <= 0)
			return 0;
		return maxAngle * rMin / r;
	}

	/*
	 * Calculates the maximum x for a center of the car, based on length of car and borders in the map 
	 */
	public double xMax(clMap map) {
		return map.xx1 - Length / 2 - minDistance;
	}

	/*
	 * Calculates the minimum x for a center of the car, based on length of car and borders in the map 
	 */
	public double xMin(clMap map) {
		return map.xx0 + Length / 2 + minDistance;
	}
}