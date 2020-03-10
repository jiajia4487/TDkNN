import java.util.ArrayList;


public class Vertex {
	private ArrayList<Node> adj;//�õ���ڽӵ�
	private double dgene;//��㵽������������·������
	private int ID;
	private ArrayList<Vertex> Vpred;//���ǰ��
	private ArrayList<Integer> VgeneNodeId;//��������
	private boolean sign;//��ǣ�����Vp-label���Ϊtrue
	public Vertex(){
		
	}
	public Vertex(int ID,ArrayList<Node> adj,ArrayList<Integer> VgeneNodeId,ArrayList<Vertex> Vpred,double dgene,boolean sign){
		this.ID=ID;
		this.adj=adj;
		this.Vpred=Vpred;
		this.VgeneNodeId=VgeneNodeId;
		this.Vpred=Vpred;
		this.sign=sign;
	}
	public Vertex(ArrayList<Node> adj,ArrayList<Integer> VgeneNodeId,ArrayList<Vertex> Vpred,double dgene){
		this.adj=adj;
		this.Vpred=Vpred;
		this.VgeneNodeId=VgeneNodeId;
		this.Vpred=Vpred;
	}
	public ArrayList<Node> getAdj() {
		return adj;
	}
	public void setAdj(ArrayList<Node> adj) {
		this.adj = adj;
	}
	public double getDgene() {
		return dgene;
	}
	public void setDgene(double dgene) {
		this.dgene = dgene;
	}
	public ArrayList<Vertex> getVpred() {
		return Vpred;
	}
	public void setVpred(ArrayList<Vertex> vpred) {
		Vpred = vpred;
	}
	public ArrayList<Integer> getVgeneNodeId() {
		return VgeneNodeId;
	}
	public void setVgeneNodeId(ArrayList<Integer> vgeneNodeId) {
		VgeneNodeId = vgeneNodeId;
	}
	public boolean isSign() {
		return sign;
	}
	public void setSign(boolean sign) {
		this.sign = sign;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	

}
