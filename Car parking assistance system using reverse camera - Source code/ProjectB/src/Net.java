/*
 * Not Used - our old algorithm, we decided to go for a faster algorithm
 */

public class Net {
	Node[] Nodes;
	double xmax = 10;//m
	double ymax = 5;//m
	double delta = 0.1;//m
	double deltafi = 45;//5 // Degrees
	double carLength = 3;//m
	double carWidth = 1.5;//m
	double yCar1 = 3;//m
	double yCar2 = 3;//m
	double xCar1 = 2;//m
	double xCar2 = 10;//m
	double x0 = 2.5; //m
	double rMin = 0.2;// 4; // minimal radius of turning, m
	int nNodes = 0;
	int nNodesMax=1500000;
	Node NodeStart = new Node(0, 1.5, 0, 0);
	Node NodeEnd = new Node(7.5, 4, 0, 1);
	private int addr(double x, double y, double fi) {
		int i=-1;
		if (x>=0 && x<=xmax && y>=0 && y<=ymax && fi>=0 && fi<360) {
			int nx=(int) (xmax/delta);
			int ny=(int) (ymax/delta);
			int nf=(int) (360/deltafi);
			int kx=(int) (x/delta);
			int ky=(int) (y/delta);
			int kf=(int) (fi/deltafi);
			i=kx*ny*nf+ky*nf+kf+2;
		}
		return i;
	}

	public void main1()
	{
		Node Node1 = new Node(0,0,0,1);
		Node Node2 = new Node(0,0.5,45,2);
		boolean b = Neighbours(Node1, Node2);
		//if (true) return;
		System.out.println("BuildNet...");
		BuildNet();
		System.out.println("BuildNet...Done");
		System.out.println("ShortestPath...");
		Node[] Path = ShortestPath();
		System.out.println("ShortestPath...Done");
		printpath(Path);
	}
	
	public void BuildNet() {
		Nodes = new Node[nNodesMax];
		Nodes[0] = NodeStart;
		Nodes[1] = NodeEnd;
		nNodes = 2;
		double x = 0;
		while(x<xmax)
		{
			double y=0;
			while(y<ymax)
			{
				double fi=0;
				while(fi<360)
				{
					
					
					if (Possible(x, y, fi)) {
						Node node1=new Node(x, y, fi, nNodes);
						//if(nNodes>14999)
						//	System.out.println(nNodes +"\t" + x+"\t" + y +"\t" + fi);
						Nodes[nNodes] = node1;
						nNodes++;
					}
					fi = fi +deltafi;
				}
				y=y+delta;
				
			}
			
			x=x+delta;
		}
		/*for (double x = 0; x < xmax; x = x + delta) {
			for (double y = 0; x < ymax; y = y + delta) {
				for (double fi = 0; fi < 360; fi = fi + deltafi) {
					
					
				}
			}
		}*/
		
		/*
		for (int i = 0; i < nNodes; i++) {
			Nodes[i].NeighboursId = new int[10000];//[8 * 20];
		}
		for (int i = 0; i < nNodes; i++) {
			for (int j = i + 1; j < nNodes; j++) {
				if (Neighbours(Nodes[i], Nodes[j])) {
					int i1=Nodes[i].nNeighbours;
					int i2=Nodes[j].nNeighbours;
					Nodes[i].NeighboursId[i1] = Nodes[j].id;
					Nodes[j].NeighboursId[i2] = Nodes[i].id;
					Nodes[i].nNeighbours++;
					Nodes[j].nNeighbours++;
					
				}
			}

		}
		*/
	}

	private boolean Possible(double x, double y, double fi) {
		return true;
	}

	private double Distance(Node Node1, Node Node2) {
		double dx = Node1.x - Node2.x;
		double dy = Node1.y - Node2.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private double fiMaxRad(double d, double r) {
		return Math.acos(1 - (d * d / (2 * r * r))); // Check if returns a value from 0 to pi
	}

	private double dfi(Node Node1, Node Node2) {
		double d = Node1.fi - Node2.fi;
		if (d > 180)
			d = d - 360;
		if (d < -180)
			d = d + 360;

		return d; // return from -180 to 180
	}

	private boolean Neighbours(Node Node1, Node Node2) {
		double d = Distance(Node1, Node2);
		if (d > 0.9)
			return false;
		if (d==0)
			return false;

		double fiMaxRad = fiMaxRad(d, rMin);
		double fiMaxDeg = (fiMaxRad * 180) / Math.PI;
		double dfi = dfi(Node1, Node2);
		if (Math.abs(dfi) > fiMaxDeg)
			return false;
		return true;
	}

	private Node[] ShortestPath() {
		int[] NodesRank0 = new int[nNodesMax];
		int nRank0 = 1;
		NodesRank0[0] = 0;
		boolean OK = true;
		int[] Enter = new int[nNodes];
		int[] rank = new int[nNodes];
		int[] ar1 = new int[nNodes];
		double[] lengthPath =new double[nNodes];;
		for (int i = 0; i < nNodes; i++) {
			Enter[i] = -1; // not Set
			rank[i]= -1; // not Set
		}
		Enter[0] = 0;
		rank[0] = 0;
		lengthPath[0]=0;
		int r=0;
		while (OK) {
			int[] NodesRank1 = new int[nNodesMax];
			int nRank1 = 0;
			for (int i = 0; i < nRank0; i++) {
				int id1=NodesRank0[i];
				//for (int j = 0; j < Nodes[id1].nNeighbours; j++) {
				//	int id2 = Nodes[id1].NeighboursId[j];
				
				for (int j = 0; j < nNodes; j++) {
				//addr(double x, double y, double fi)
					int id2=j;
					boolean bNeedCheck=true;
					if (rank[j]>=0 && rank[j]<=r) bNeedCheck=false;
					if (bNeedCheck) bNeedCheck=Neighbours(Nodes[i], Nodes[j]);
					if (bNeedCheck)
					{
						double d=Distance(Nodes[id1],Nodes[id2]);
						double dd=lengthPath[id1]+d;
						boolean b=Enter[id2] < 0;
						if (!b) b=lengthPath[id2]>dd;
						if (b) {
							if (rank[id2]<0) {
								Enter[id2] = Nodes[id1].id;
								NodesRank1[nRank1] = id2;
								lengthPath[id2]=dd;
								rank[id2]=r+1;
								ar1[id2]=nRank1;
								nRank1++;
							}else {
								if (rank[id2]==r+1) {
									Enter[id2] = Nodes[id1].id;
									//NodesRank1[ar1[id2]] = id2;
									lengthPath[id2]=dd;
									//rank[id2]=r+1;
								}
							}
							
						}
					}
					
					
					/*
					if (Enter[id2] < 0) {
						Enter[id2] = Nodes[id1].id;
						NodesRank1[nRank1] = id2;
						nRank1++;
						
						//if (id2 == 1) {
						//	System.out.println("Arrived");
						//	OK = false;
						//}
					}
					*/
				}
			}
			if (nRank1==0) OK=false;

			nRank0 = nRank1;
			for (int i = 0; i < nRank0; i++) {
				NodesRank0[i] = NodesRank1[i];
			}
			
			r++;
			System.out.println(r+"\t"+nRank1+"\t of \t"+nNodes);
		}
		
		for(int i = 0;i<nNodes;i++)
		//for(int i = 0;i<200;i++)
		{
			System.out.println(i + "\t" + Nodes[i].id+"\t" + Nodes[i].x + "\t" + Nodes[i].y + "\t" + Nodes[i].fi + "\t" + Nodes[i].nNeighbours+"\t"+Enter[i]+"\t"+rank[i]+"\t"+lengthPath[i]);
		    if (Enter[i]<0 && i>100)i=nNodes;
		}
		int n = 1;
		int id = 1;
		while (id != 0) {
			id = Enter[id];
			n++;
		}
		Node[] Path = new Node[n];
		int w = n;
		id = 1;
		for (int i = 0; i < n; i++) {
			Path[w - 1] = Nodes[id];
			w--;
			id = Enter[id];
		}
		return Path;
	}

	public void printpath(Node[] Path)
	{
		System.out.println("Path:");
		int n = Path.length;
		for(int i=0;i<n;i++)
		{
			int id = Path[i].id; 
			double x = Path[i].x;
			double y = Path[i].y;
			double fi = Path[i].fi;
			double dfi = 0;
			double d = 0;
			double dd=0;
			if(i>0)
			{
				d = Distance(Path[i],Path[i-1]);
				dd=dd+d;
				dfi = dfi(Path[i],Path[i-1]);
			}
			System.out.println(i + "\t" + id + "\t" + +x +"\t"+ y +"\t"+ fi + "\t" + d + "\t" + dfi+"\t"+dd);
		}
	}
}
