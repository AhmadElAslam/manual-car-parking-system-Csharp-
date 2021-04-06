/*
 * Not Used - our old algorithm, we decided to go for a faster algorithm
 */

public class Node {
	
	double x;
	double y;
	double fi; //from 0 to 360
	int id;
	int[] NeighboursId;
	int nNeighbours;
	
	Node(double xSet, double ySet, double fiSet, int idSet)
	{
		x = xSet;
		y = ySet;
		fi = fiSet;
		id = idSet;
		nNeighbours = 0;
	}
}
