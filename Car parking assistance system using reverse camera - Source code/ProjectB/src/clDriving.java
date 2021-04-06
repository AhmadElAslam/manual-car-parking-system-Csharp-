import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Gets the start and the end states, and builds all the maneuvers and instructions for the user
 */
public class clDriving
{
	/*
	Testing for getting results
	public void ResultLoop() throws IOException 
	{
        clMap map = new clMap(); // Take the map with the start state, end state, and restrictions (Need to take from interface)
        clCar car = new clCar(); // Take parameters of the car (Need to take from interface)
        clManeuvers maneuvers = new clManeuvers(); // Make a new list of maneuvers
        FileOutputStream outputStream = new FileOutputStream("nManeuvers.txt");
        String s="y\\x";
        int m=100;
        for(int i=0;i<=m;i++)
        {
        	map.xx1=3.5+(10-3.5)*i/m;
        	s=s+"\t"+map.xx1+"";
        }
        outputStream.write((s+"\n").getBytes());
        for(int j=0;j<=20;j++)
        {
        	map.State1.Y=1.0+(10-1.0)*j/20; //
        	s=""+map.State1.Y+"";
        	for(int i=0;i<=m;i++)
        	{
        		map.xx1=3.5+(10-3.5)*i/m; // second coordinate runs from 3.5 to 10 m
        		if (i==m && j==4) {
        			i=i;
        		}
        		clMap map1=map.copy();
        		maneuvers = ParallelParking(map1, car, 0);
        		int n=maneuvers.nManeurs;
        		if (!maneuvers.bPossible)n=0;
        		s=s+"\t"+n+"";
               
        		
                
        	}
        	outputStream.write((s+"\n").getBytes());
        }
        outputStream.close();
        maneuvers = ParallelParking(map, car, 0); // Calculate maneuvers
        maneuvers.print(0.1); // print all coordinates along the path - each 0.1 meter = 10 cm
        maneuvers.print(0); // print all maneuvers
	}
	*/
	
	/*
	 * The main method, making all the work
	 */
        public void run() throws IOException {
            clMap map = new clMap(); // Take the map with the start state, end state, and restrictions (Need to take from interface)
            clCar car = new clCar(); // Take parameters of the car (Need to take from interface)
            clManeuvers maneuvers = new clManeuvers(); // Make a new list of maneuvers
            maneuvers = ParallelParking(map, car, 0); // Calculate maneuvers
            maneuvers.print(0.1); // print all coordinates along the path - each 0.1 meter = 10 cm
            maneuvers.print(0); // print all maneuvers
            //ResultLoop(); Testing for getting results
        }

        /*
         * Makes the list of the maneuvers in the simple case - it is enough to go straight
         */
        private clManeuvers ParallelParking_Straight(clMap map)
        {
            boolean bBackward = (map.State0.Phi > 90) && (map.State0.Phi < 270); // Check car orientation
            double dx = map.State1.X - map.State0.X; // Calculate distance to go
            if (bBackward) // take orientation into account
            	dx = -dx;
            clManeuvers maneuvers = new clManeuvers(); // Take the new list maneuvers
            maneuvers.startStraight(map.State0.X, map.State0.Y, map.State0.Phi, dx);
            return maneuvers;
        }
        
        /*
         * Make a list of two maneuvers
         * First one - go in an arch
         * Second one - go in an opposite arch 
         */
        private clManeuvers ParallelParking_Single(clMap map, clCar car, double r) {
            //S
            boolean bBackward = (map.State0.Phi > 90) && (map.State0.Phi < 270);
            double dx = map.State1.X - map.State0.X;
            double dy = map.State1.Y - map.State0.Y;
            clManeuvers maneuvers = new clManeuvers();

            double aRad = aRad_get(Math.abs(dx), Math.abs(dy), r);
            double d = r * aRad; //Math.PI*2

            double angDegR = car.angDeg_get(r);
            double a1, a2, d1, d2, xc1, yc1, xc2, yc2;
            xc1 = map.State0.X;
            xc2 = map.State1.X;
            if ((dy <= 0 && !bBackward) || (dy > 0 && bBackward))
            {
                a1 = angDegR;
                a2 = -angDegR;
                yc1 = map.State0.Y + r;
                yc2 = map.State1.Y - r;
            }
            else
            {
                a1 = -angDegR;
                a2 = angDegR;
                yc1 = map.State0.Y - r;
                yc2 = map.State1.Y + r;
            }

            double c = 1;
            if (bBackward && dx > 0) c = -1;
            if (!bBackward && dx < 0) c = -1;
            d1 = c * d;
            d2 = c * d;
            maneuvers.startTurn(map.State0.X, map.State0.Y, map.State0.Phi, d1, a1, xc1, yc1, r); // First turn
            maneuvers.addTurn(d2, a2, xc2, yc2, r); // Second turn
            return maneuvers;
        }
        
        /*
         * Preparing for such movement (stage 1 only):
         * 
         * Make list of maneuvers to go from start to end by the following way
         * 1) Go forward - to have enough space
         * 2) Turn
         * 3) Opposite turn
         */
        private clManeuvers ParallelParking_WithMoveForward(clMap map, clCar car)
        {
            boolean bBackward = (map.State0.Phi > 90) && (map.State0.Phi < 270);
            double dx = map.State1.X - map.State0.X;
            double dy = map.State1.Y - map.State0.Y;
            double ady = Math.abs(dy); 
            double adxMin = Math.sqrt(4 * car.rMin * ady - dy * dy); //4 R dy = dy^2 + dx^2 (see above)
            double ddd = 0;
            if (dx >= 0)
            {
                ddd = dx - adxMin;//[- adxMin,0]
            }
            else
            {
                ddd = adxMin + dx;//[0,adxMin)
            }
            double c = 1;
            if (bBackward && dx > 0) c = -1;
            if (!bBackward && dx < 0) c = -1;
            ddd = c * ddd;
            clManeuvers maneuvers = new clManeuvers();
            maneuvers.startStraight(map.State0.X, map.State0.Y, map.State0.Phi, ddd); // Go straight
            if (bBackward) ddd = -ddd;
            map.State0.X = map.State0.X + ddd;
            return maneuvers;
        }
        
        /*
		 * Find the path from start to end
         */
        private clManeuvers ParallelParking(clMap map, clCar car, int iRecursion)
        {
            double dy = map.State1.Y - map.State0.Y;
            if (map.State1.X>car.xMax(map)) {
            	clManeuvers maneuvers = new clManeuvers();
            	maneuvers.bPossible = false;
                return maneuvers;
            }
            if (dy == 0) // If possible to park by a single straight movements 
            	return ParallelParking_Straight(map);

            double dx = map.State1.X - map.State0.X;
            boolean bBackward = (map.State0.Phi > 90) && (map.State0.Phi < 270);            
            double r = r_get(dx,dy);
            
            if (r >= car.rMin) // If it's possible to do the parking in 2 steps
            {
                return ParallelParking_Single(map,car,r);
            }
            else {
                if (map.isPossibleMoveForward)
                {
                   /*
                	* Make list of maneuvers to go from start to end by the following way
                    * 1) Go forward - to have enough space
                    * 2) Turn
                    * 3) Opposite turn
                    */
                    clManeuvers maneuvers = new clManeuvers();
                    map.isPossibleMoveForward = false;
                    maneuvers = ParallelParking_WithMoveForward(map, car);
                    return maneuvers;
                }
                else 
                {
                	/*
                	 * Complicated case
                	 */
                    clManeuvers maneuvers = new clManeuvers();
                    double xCurrent = map.State0.X;
                    double yCurrent = map.State0.Y;
                    double xMax = car.xMax(map);
                    double xMin = car.xMin(map);
                    if (xMin >= xMax) 
                    {
                        maneuvers.bPossible = false;
                        return maneuvers;
                    }
                    double dxRight = xMax - xCurrent;
                    double dxLeft = xCurrent- xMin;
                    if (dxLeft < car.minDistance && dxRight < car.minDistance) 
                    {
                    	maneuvers.bPossible = false;
                        return maneuvers;
                    }
                    if (iRecursion > 100) {
                    	maneuvers.bPossible = false;
                        return maneuvers;
                    }
                    dx = dxRight;
                    if (dxRight < dxLeft) dx = dxLeft;
                    double dyMax = dyMax_get(Math.abs(dx), car);
                    if (dyMax < 0 && dy>2*car.rMin) 
                    {
                        //dx>2r
                        //dy>2r
                        r = car.rMin;
                        double a1 = car.angDeg_get(r);
                        double d1 = 2 * Math.PI * r / 4;
                        double d2 = Math.abs(dy) - 2 * r;
                        double c1 = 1;
                        double c2 = 1;
                        if ((bBackward && dx > 0) || (!bBackward && dx < 0)) {
                            c1 = -1;
                        }
                        if (dy < 0) c2 = -1;
                        d1 = c1*d1;
                        d2 = c1*d2;
                        double xc1 = map.State0.X;
                        double xc2 = map.State0.X+2*r;
                        if (dx < 0) {
                            xc2 = map.State0.X - 2 * r;
                        }
                        double yc1 = map.State0.Y + c2*r;
                        double yc2 = map.State1.Y - c2*r;
                        maneuvers.startTurn(map.State0.X, map.State0.Y, map.State0.Phi, 
                            d1, a1, xc1, yc1, r);
                        maneuvers.addStraight(d2);
                        maneuvers.addTurn(d1, -a1, xc2, yc2, r);
                        double d = map.State1.X-xc2;
                        if (bBackward) d = -d;
                        maneuvers.addStraight(d);
                        return maneuvers;
                    }
                    double dyReal = dy;
                    
                    clMap map1 = map.copy();
                    map1.State0.X = xCurrent;
                    map1.State0.Y = yCurrent;
                    if (dyMax >= 0) {
                    	if (dyReal > dyMax) dyReal = dyMax;                                       
                    } else {
                    	dx=dxMin_get(Math.abs(dyReal),car);
                    }
                    if (dxRight >= dxLeft)
                    {
                        map1.State1.X = xCurrent + dx;
                    }
                    else {
                        map1.State1.X = xCurrent - dx;
                    } 
                    map1.State1.Y = map.State0.Y + dyReal;
                    map1.isPossibleMoveForward = false;
                    map1.xx0 = map.xx0;
                    map1.xx1 = map.xx1; 
                    maneuvers = ParallelParking(map1, car, iRecursion + 1);
                    if (maneuvers.bPossible) {
                        map.State0.X = map1.State1.X;
                        map.State0.Y = map1.State1.Y;
                        map.isPossibleMoveForward = false;
                        clManeuvers maneuvers1 = ParallelParking(map, car,iRecursion+1);
                        if (maneuvers1.bPossible) {
                            maneuvers.addToEnd(maneuvers1);
                        } else {
                        	maneuvers.bPossible = false;
                        }
                    }
                    return maneuvers;
                }
            }
        }
        
        /*
         * Calculate radius in case of 2 turnings
         */
        private double r_get(double dx, double dy) 
        {
            //R cos a = R - dy/2
            //R sin a = dx/2
            //R^2=(R - dy/2)^2+(dx/2)^2
            //2R(dy/2)=(dy/2)^2+(dx/2)^2
            //4 R dy=dy^2 + dx^2
            return (dx * dx + dy * dy) / (4 * Math.abs(dy));
        }
        
        /*
         * Calculate the angle in case of 2 turnings
         */
        private double aRad_get(double dxAbs, double dyAbs, double r) 
        {
            double aRad=Math.asin(dxAbs / (2 * r));
            if (dxAbs < dyAbs) aRad = Math.PI - aRad;
            return aRad;
        }
        
        /*
         * Calculates which change in y is possible for a given restrictions on x
         */
        private double dyMax_get(double dxAbs, clCar car) 
        {
            //4 R dy = dy ^ 2 + dx ^ 2
            //dy ^ 2 - 4 R dy + dx ^ 2=0
            //dy = 2R(+/-)Sqrt(4R^2-dx ^ 2) // case of "+" doesn't matter
            double dMargine= 0.00000000001;
            double r = car.rMin;
            if (dxAbs > 2 * r) return -1;// all possible
            return 2 * r * (1 - Math.sqrt(1 - dxAbs * dxAbs / (4 * r * r)))-dMargine;
        }
        private double dxMin_get(double dyAbs, clCar car) 
        {
            //4 R dy = dy ^ 2 + dx ^ 2
        	//dx=Sqrt(4 R dy - dy ^ 2)
            
            double dMargine= 0.00000000001;
            double r = car.rMin;
            if (dyAbs > 2 * r) return -1;// all possible
            return Math.sqrt(4*r*dyAbs-dyAbs*dyAbs)+dMargine;
        }
}



