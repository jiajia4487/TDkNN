import java.util.ArrayList;


public class Adjacency {
	private int id;
	private ArrayList<Node> adjnode;
	public Adjacency(int id,ArrayList<Node> adjnode){
		this.id=id;
		this.adjnode=adjnode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Node> getAdjnode() {
		return adjnode;
	}

	public void setAdjnode(ArrayList<Node> adjnode) {
		this.adjnode = adjnode;
	}
	

}
