/*
 * Initialize our map in a numerical way
 * */

public class clMap 
{
         public clState State0 = new clState(0, 0, 180); // Start state
         public clState State1 = new clState(5, 2, 180); // End state (5, 10, 180)
         public boolean isPossibleMoveForward = false; // If it's possible to move forward
         public double xx0 = 1; // Front border	1.5; //end of left car < 5-5/2-0.2-0.5 
         public double xx1 = 8.5;//8.5; // End border 18.5; //start of right car  //>5+5/2+0.2+0.5
         

         public clMap copy() 
         {
        	 clMap map1=new clMap();
        	 map1.xx0=xx0;
        	 map1.xx1=xx1;
        	 map1.isPossibleMoveForward=isPossibleMoveForward;
        	 map1.State0=State0.Copy();
        	 map1.State1=State1.Copy();
        	 return map1;
         }
}


