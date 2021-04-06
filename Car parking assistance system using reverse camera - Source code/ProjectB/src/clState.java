/*
 * Car state (Start/End/Current...)
 * */ 

class clState {
        public double X; // Coordinate in X axis (m)
        public double Y; // Coordinate in Y axis (m)
        public double Phi;// (degrees) [0,360) x=0, Counter clockwise (0 is in x axis direction)

        public clState(double xSet, double ySet, double phiSet) // Constructor
        {
            X = xSet;
            Y = ySet;
            Phi = phiSet;
        }
        public static String sText0() // Print to string - table header
        {
            return "\t" + "X" + "\t" + "Y" + "\t" + "Phi";
        }
        public String sText() // Print to string - table rows
        {
            return "\t" + X + "\t" + Y + "\t" + Phi;
        }
        public clState Copy() // Copy of a class
        {
            return new clState(X, Y, Phi);
        }

    }