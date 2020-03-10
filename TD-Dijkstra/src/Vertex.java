import java.util.ArrayList;

public class Vertex {
	private ArrayList<Node> adj;//该节点的邻接表
	private boolean sign;//标记节点是否被访问
	private boolean IfPoi;
	private double dist,At,Tt;
	private Vertex path;
	private int ID;
	public Vertex(){
		
	}
	public Vertex(int ID,ArrayList<Node> adj,boolean sign,double dist,double At,double Tt,Vertex path,boolean IfPoi){
		this.ID=ID;
		this.adj=adj;
		this.dist=dist;
		this.At=At;
		this.Tt=Tt;
		this.sign=sign;
		this.path=path;
		this.IfPoi=IfPoi;
		
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	public Vertex getPath() {
		return path;
	}
	public void setPath(Vertex path) {
		this.path = path;
	}
	public boolean isSign() {
		return sign;
	}
	public void setSign(boolean sign) {
		this.sign = sign;
	}
	public ArrayList<Node> getAdj() {
		return adj;
	}
	public void setAdj(ArrayList<Node> adj) {
		this.adj = adj;
	}
	public boolean isIfPoi() {
		return IfPoi;
	}
	public void setIfPoi(boolean ifPoi) {
		IfPoi = ifPoi;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getAt() {
		return At;
	}
	public void setAt(double at) {
		At = at;
	}
	public double getTt() {
		return Tt;
	}
	public void setTt(double tt) {
		Tt = tt;
	}

	}


