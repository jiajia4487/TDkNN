
public class Node {
	 private int NodeId,NodeX,NodeY;
	 private double weight;
	 public Node(){
		 
	 }
	 public Node(int NodeId,int NodeX,int NodeY,double weight){
		this.NodeId=NodeId;
		this.NodeX=NodeX;
		this.NodeY=NodeY;
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
	 

}
