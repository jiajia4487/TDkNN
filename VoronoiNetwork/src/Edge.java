
public class Edge {
	private int EdgeId,StartId,EndId,Type;
	private double weight;
	public Edge(){
		
	}
	public Edge(int EdgeId,int StartId,int EndId,int Type,double weight){
		this.EdgeId=EdgeId;
		this.StartId=StartId;
		this.EndId=EndId;
		this.Type=Type;
		this.weight=weight;
	}
	public Edge(int EdgeId,int StartId,int EndId,double weight){
		this.EdgeId=EdgeId;
		this.StartId=StartId;
		this.EndId=EndId;
		this.weight=weight;
	}
	public Edge(int EdgeId,double weight){
		this.EdgeId=EdgeId;
		this.weight=weight;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getStartId() {
		return StartId;
	}
	public void setStartId(int startId) {
		StartId = startId;
	}
	public int getEdgeId() {
		return EdgeId;
	}
	public void setEdgeId(int edgeId) {
		EdgeId = edgeId;
	}
	public int getEndId() {
		return EndId;
	}
	public void setEndId(int endId) {
		EndId = endId;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
