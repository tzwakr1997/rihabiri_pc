package daire_data_upload;

public class Angle_Calculation {
	double acceleration;
	double angle;
	
	public double Angle_Caluculation(double acceleration) {
		angle=(Math.acos(acceleration))*(180/Math.PI);
		return angle;
	}
}

