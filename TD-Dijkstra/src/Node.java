
public class Node {
	 private int NodeId,NodeX,NodeY;
	 private Long EdgeId;
	 public Node(){
		 
	 }
	 public Node(int NodeId,int NodeX,int NodeY){
		this.NodeId=NodeId;
		this.NodeX=NodeX;
		this.NodeY=NodeY;
	 }
	 public Node(int NodeId){
		 this.NodeId=NodeId;		 
	 }
	 public Node(int NodeId,Long EdgeId){
		 this.NodeId=NodeId;
		 this.EdgeId=EdgeId;		 
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
	public Long getEdgeId() {
		return EdgeId;
	}
	public void setEdgeId(Long edgeId) {
		EdgeId = edgeId;
	}
}
