import java.io.FileOutputStream;
import java.io.IOException;

/*
 * List of all maneuvers
 */

public class clManeuvers {
	
    public clManeuver[] move; // An array of all maneuvers
    public int nManeurs = 0; // Number of maneuvers
    public boolean bPossible = true;
    
    /*
     * Print all the movements
     * If d=0, we will print only the movements without printing the coordinates along the path
     * If d>0, we will print only the states (coordinates and angles) along the path for each distance step d 
     */
    public void print(double d) throws IOException 
    {
            String sLogFileName = "LogManeuvers.txt";
            if (d > 0) 
            	sLogFileName = "LogCoordinates.txt";
            FileOutputStream outputStream = new FileOutputStream(sLogFileName);
            
            /*
             * Header of the table
             */
            outputStream.write(("m" + clManeuver.sText0() + "\n").getBytes());
            if (nManeurs>0)
                if (d > 0)
                    outputStream.write(("start" + move[0].sTextNoMove()  + "\n").getBytes()); 
            
            /*
             * Rows of the table
             */
            for (int i = 0; i < nManeurs; i++) 
            {
                if (d<=0) 
                	outputStream.write((i + move[i].sText() + "\n").getBytes());
                if (d > 0) 
                {
                    clManeuver m = move[i].Copy();
                    boolean o = m.R >= 0;
                    int n = (int) (Math.abs(m.move) / d);
                    for (int k = 1; k < n; k++)
{
                        m = move[i].Copy();
                        double dd = d * k;
                        if (m.move < 0) dd = -dd;
                        if (o)
                        {
                            m.turn(dd);
                        }
                        else {
                            m.goStraight(dd);
                        }
                    	outputStream.write((m.sText() + "\n").getBytes());
                    }
                }
            }
            outputStream.close();
        }

    /*
     * Make a new list of maneuvers with only one maneuver - Go Straight
     */
    public void startStraight(double x0, double y0, double phi0, double d) 
    {
        clManeuver moveNew = new clManeuver();
        moveNew.StartState.X = x0;
        moveNew.StartState.Y = y0;
        moveNew.StartState.Phi = phi0;
        moveNew.goStraight(d);
        nManeurs = 1;
        move = new clManeuver[nManeurs];
        move[0] = moveNew;
    }
    
    /*
     * Add maneuver (to the end of the array) - Go Straight
     */
    public void addStraight(double d) {
        clManeuver moveNew = new clManeuver();
        moveNew.StartState.X = move[nManeurs - 1].FinishState.X;
        moveNew.StartState.Y = move[nManeurs - 1].FinishState.Y;
        moveNew.StartState.Phi = move[nManeurs - 1].FinishState.Phi;
        moveNew.goStraight(d);
        nManeurs++;
        clManeuver[] move1 = new clManeuver[nManeurs];
        for (int i = 0; i < nManeurs - 1; i++)
        {
            move1[i] = move[i];
        }
        move1[nManeurs - 1] = moveNew;
        move = move1;
    }
    
    /*
     * Make a new array of maneuvers, starting from turn
     */
    public void startTurn(double x0, double y0, double phi0,
                          double d, double SteeringAngleGrad_set, double xC0, double yC0, double rSet)
    {
        clManeuver moveNew = new clManeuver();
        moveNew.StartState.X = x0;
        moveNew.StartState.Y = y0;
        moveNew.StartState.Phi = phi0;
        moveNew.turn(d, SteeringAngleGrad_set, xC0, yC0, rSet);
        nManeurs = 1;
        move = new clManeuver[nManeurs];
        move[0] = moveNew;
    }
    
    /*
     * Add a maneuver (to the end of the array) - Turn
     */
    public void addTurn(double d, double SteeringAngleGrad_set, double xC0, double yC0, double rSet)
    {
        clManeuver moveNew = new clManeuver();
        moveNew.StartState.X = move[nManeurs - 1].FinishState.X;
        moveNew.StartState.Y = move[nManeurs - 1].FinishState.Y;
        moveNew.StartState.Phi = move[nManeurs - 1].FinishState.Phi;
        moveNew.turn(d, SteeringAngleGrad_set, xC0, yC0, rSet);
        nManeurs++;
        clManeuver[] move1 = new clManeuver[nManeurs];
        for (int i = 0; i < nManeurs - 1; i++)
        {
            move1[i] = move[i];
        }
        move1[nManeurs - 1] = moveNew;
        move = move1;
    }
    
    /*
     * Add array of maneuvers to current array of maneuvers
     */
    public void addToEnd(clManeuvers Maneurs) {
        int n = nManeurs + Maneurs.nManeurs;
        clManeuvers Maneurs1 = new clManeuvers();
        Maneurs1.move = new clManeuver[n];
        for (int i = 0; i < nManeurs; i++) {
            Maneurs1.move[i] = move[i];
        }
        for (int i = 0; i < Maneurs.nManeurs; i++)
        {
            Maneurs1.move[nManeurs + i] = Maneurs.move[i];
        }
        move = Maneurs1.move;
        nManeurs = n;
    }

}
