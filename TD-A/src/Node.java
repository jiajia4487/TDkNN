import java.util.ArrayList;


public class Node {
	 private int NodeId,NodeX,NodeY;
	 private double weight;
	 private double minweight;
	 private double maxweight;
	 private int minpoi;
	 private int maxpoi;
	 private Long EdgeId;
	 private ArrayList<Node> nodes;
	
	 public Node(){ 
		 
	 }
	 
	 public Node(int NodeId,int NodeX,int NodeY){
			this.NodeId=NodeId;
			this.NodeX=NodeX;
			this.NodeY=NodeY;
	}
	 
	 public Node(int NodeId,int NodeX,int NodeY,double weight){
		this.NodeId=NodeId;
		this.NodeX=NodeX;
		this.NodeY=NodeY;
		this.weight=weight;
	 }
	 
	 public Node(int NodeId,Long EdgeId,int minpoi,double minweight,int maxpoi,double maxweight){
		 this.NodeId=NodeId;
		 this.EdgeId=EdgeId;
		 this.minpoi=minpoi;
		 this.minweight=minweight;
		 this.maxpoi=maxpoi;
		 this.maxweight=maxweight;
	 }
	 
	 public Node(int NodeId,ArrayList<Node> nodes){
		 this.NodeId=NodeId;
		 this.nodes=nodes;
	 }
	 
	 
	 
	 public Node(int NodeId,ArrayList<Node> nodes,Long edgeid){
		 this.EdgeId=edgeid;
		 this.NodeId=NodeId;
		 this.nodes=nodes;
	 }
	  
	 public Node(int NodeId,double weight){
		 this.NodeId=NodeId;
		 this.weight=weight;
	}
	 	 
	 
	 public Node(int NodeId){
		 this.NodeId=NodeId;		 
	 }

	public int getNodeY() {
		return NodeY;
	}

	public void setNodeY(int nodeY) {
		NodeY = nodeY;
	}

	public int getNodeX() {
		return NodeX;
	}

	public void setNodeX(int nodeX) {
		NodeX = nodeX;
	}

	public int getNodeId() {
		return NodeId;
	}

	public void setNodeId(int nodeId) {
		NodeId = nodeId;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getMinweight() {
		return minweight;
	}

	public void setMinweight(double minweight) {
		this.minweight = minweight;
	}

	public double getMaxweight() {
		return maxweight;
	}

	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
	}

	public int getMinpoi() {
		return minpoi;
	}

	public void setMinpoi(int minpoi) {
		this.minpoi = minpoi;
	}

	public int getMaxpoi() {
		return maxpoi;
	}

	public void setMaxpoi(int maxpoi) {
		this.maxpoi = maxpoi;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public Long getEdgeId() {
		return EdgeId;
	}

	public void setEdgeId(Long edgeId) {
		EdgeId = edgeId;
	}
	 

}
