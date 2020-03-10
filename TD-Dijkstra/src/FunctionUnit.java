
public class FunctionUnit {
	private double k,b;
	int TimeStart,TimeEnd;
	public FunctionUnit(double k,double b,int TimeStart,int TimeEnd){
		this.k=k;
		this.b=b;
		this.TimeStart=TimeStart;
		this.TimeEnd=TimeEnd;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public int getTimeEnd() {
		return TimeEnd;
	}

	public void setTimeEnd(int timeEnd) {
		TimeEnd = timeEnd;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public int getTimeStart() {
		return TimeStart;
	}

	public void setTimeStart(int timeStart) {
		TimeStart = timeStart;
	}

}
