/*
 * Divides the path movement into simple movements (moves straight or on arch)
 */

public class clManeuver {
            public double move=0; // Instruction - How many meters we need to move (m)
            public double SteeringAngleDeg=0; // Instruction - Steering wheel turn (degrees)
            public double CenterX; // x coordinate of the center of the circle (m)
            public double CenterY; // y coordinate of the center of the circle (m)
            public double R; // radius between the center of the circle and the center of the car (m)
            public clState StartState=new clState(0, 0, 0);
            public clState FinishState=new clState(0, 0, 0);

            /*
             * Copy the maneuver of the car 
             */
            public clManeuver Copy() 
            {
                clManeuver m = new clManeuver();
                m.move = move;
                m.SteeringAngleDeg = SteeringAngleDeg;
                m.CenterX = CenterX;
                m.CenterY = CenterY;
                m.R = R;
                m.StartState = StartState.Copy();
                m.FinishState = FinishState.Copy();
                return m;
            }
            
            /*
             * Print the header of the movements table 
             */
            public static String sText0()
            {
                return "\t" + clState.sText0() 
                     + "\t" + clState.sText0() 
                     + "\t" + "move" + "\t" + "ang"
                     + "\t" + "R" + "\t" + "xC" + "\t" + "yC";
            }
            
            /*
             * Print the rows of the movements table
             */
            public String sText()
            {
                return "\t" + "S"+StartState.sText()
                     + "\t" + "F"+FinishState.sText()
                     + "\t" + move + "\t" + SteeringAngleDeg
                     + "\t" + R + "\t" + CenterX + "\t" + CenterY;
            }
            
            /*
             * Print a specific case of the movements table rows (Start state) 
             */
            public String sTextNoMove()
            {
                return "\t" + "S" + StartState.sText()
                     + "\t" + "S" + StartState.sText();
            }

            /*
             * Go straight maneuver for distance d (forward or backward)
             */
            public void goStraight(double d) {
                SteeringAngleDeg = 0; // Going forward/backward doesn't need an angle
                R = -1; // we need to initialize the radius to -1 because we don't need a radius in case of a straight movement
                move = d; // The distance we need to move straight
                double phiRad = StartState.Phi * Math.PI / 180; // phi in rad
                double dx = d * Math.cos(phiRad); // Changes in x coordinate
                double dy = d * Math.sin(phiRad); // Changes in y coordinate
                FinishState.X = StartState.X + dx; // Calculate the x coordinate of the end of maneuver
                FinishState.Y = StartState.Y + dy; // Calculate the y coordinate of the end of maneuver
                FinishState.Phi = StartState.Phi; // Phi doesn't change because we are going straight
            }

            /*
             * Turning maneuver for distance d (forward or backward)
             */
            public void turn(double d, double SteeringAngleDeg_set, double x0, double y0, double rSet)
            {
                double dPhiRad = d / rSet; // initial direction of the car counter clockwise (rSet is the radius of the turn)
               
                /*
                 * Checks if the car is directed in a clockwise angle or a counter clockwise angle 
                 */
                boolean o1 = (StartState.X - x0) * (StartState.Phi - 180) > 0;
                boolean o2 = (StartState.Y > y0) && (StartState.Phi < 90) || (StartState.Phi > 270);
                boolean o3 = (StartState.Y < y0) && ((StartState.Phi > 90) && (StartState.Phi < 270));
                boolean o = o1 || ((StartState.X == x0) && (o2 || o3));
                
                if (o) // if it's clockwise
                	dPhiRad = -dPhiRad; // we need to turn the car into the opposite angle

                double alphaRad = Math.acos((StartState.X - x0) / rSet); // Calculate in which angle (related to center of the circle) we are starting
                
                if ((StartState.Y < y0) && alphaRad<Math.PI) // If the angle should be larger than pi
                	alphaRad = alphaRad + Math.PI; // Turning the angle to [0 2pi) interval

                double PhiRad = alphaRad + dPhiRad; // Calculate the angle after the turn

                move = d; // Instruction - move by distance d
                SteeringAngleDeg = SteeringAngleDeg_set; // Instruction - The angle of the steering wheel
                CenterX = x0; // Hidden to user - x coordinate of the center of the circle (m)
                CenterY = y0; // Hidden to user - y coordinate of the center of the circle (m)
                R = rSet; // Hidden to user - Radius
                FinishState.X = x0 + rSet * Math.cos(PhiRad); // x coordinate of the Finish state of a single movement
                FinishState.Y = y0 + rSet * Math.sin(PhiRad); // y coordinate of the Finish state of a single movement
               
                /*
                 * Phi of the finish state of a single movement and converting to Degrees
                 */
                FinishState.Phi = PhiRad * 180 / Math.PI; 
                if (o)
                {
                    FinishState.Phi = PhiRad * 180 / Math.PI - 90;
                }
                else {
                    FinishState.Phi = PhiRad * 180 / Math.PI + 90;
                }
                while (FinishState.Phi >= 360) FinishState.Phi = FinishState.Phi - 360;
                while (FinishState.Phi < 0) FinishState.Phi = FinishState.Phi + 360;
            }
            
            
            /*
             * Specific case of the previous method, in case we already have the parameters excluding d
             */
            public void turn(double d) 
            {
                turn(d, SteeringAngleDeg, CenterX, CenterY, R);
            }
        }