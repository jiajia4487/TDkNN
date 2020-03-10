import java.util.ArrayList;


public class Edge {
	private Long EdgeId;
	private int StartId,EndId;
	private ArrayList<FunctionUnit> weightfunction;
	
    public Edge(){
		
	}

	public Edge(Long EdgeId,ArrayList<FunctionUnit> weightfunction){
		this.EdgeId=EdgeId;
		this.weightfunction=weightfunction;
	}
	
	public Edge(Long EdgeId,int StartId,int EndId){
		this.EdgeId=EdgeId;
		this.StartId=StartId;
		this.EndId=EndId;
	}
	public int getStartId() {
		return StartId;
	}
	public void setStartId(int startId) {
		StartId = startId;
	}
	public Long getEdgeId() {
		return EdgeId;
	}
	public void setEdgeId(Long edgeId) {
		EdgeId = edgeId;
	}
	public int getEndId() {
		return EndId;
	}
	public void setEndId(int endId) {
		EndId = endId;
	}
	public ArrayList<FunctionUnit> getWeightfunction() {
		return weightfunction;
	}
	public void setWeightfunction(ArrayList<FunctionUnit> weightfunction) {
		this.weightfunction = weightfunction;
	}

}
