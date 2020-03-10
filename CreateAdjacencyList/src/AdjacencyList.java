
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class AdjacencyList {
	static DataProcessing data=new DataProcessing();
    static List<Node> nodes=new ArrayList<Node>();
    static List<Edge> edges=new ArrayList<Edge>();
    static List<Edge> edgesweights=new ArrayList<Edge>();
    static List<Edge> weights=new ArrayList<Edge>();

    
	public static void main(String[] args){
		nodes=data.ReadNode();
		edges=data.ReadEdge();
		
		/*
		for(int k=0;k<=5;k++){	
			weights=data.Readweight(k+1);
			edgesweights=data.setEdgeweight(weights,edges);
			ArrayList<ArrayList<Node>> graph=getGraph(edgesweights,nodes);

			File file=new File("adjacencylist"+(k+1)+".txt");
			PrintWriter output = null;
			try {
				output = new PrintWriter(file);
				for(int i=0;i<nodes.size();i++){
					for(int j=0;j<graph.get(i).size();j++){
						System.out.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
						output.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
					}
					System.out.println("");
					output.println("");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();
		}
		*/
		
		
		
		for(int k=0;k<=5;k++){	
			weights=data.Readweight(k+1);
			edgesweights=data.setEdgeweight(weights,edges);
			ArrayList<ArrayList<Node>> graph=getGraph(edgesweights,nodes);

			File file=new File("adjacencylistmin"+(k+1)+".txt");
			PrintWriter output = null;
			try {
				output = new PrintWriter(file);
				for(int i=0;i<nodes.size();i++){
					for(int j=0;j<graph.get(i).size();j++){
						System.out.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
						output.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
					}
					System.out.println("");
					output.println("");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();
		}
		
		
		/*
		//for(int k=0;k<=5;k++){	
			weights=data.Readweight();
			edgesweights=data.setEdgeweight(weights,edges);
			ArrayList<ArrayList<Node>> graph=getGraph(edgesweights,nodes);

			File file=new File("adjacencylistmax.txt");
			PrintWriter output = null;
			try {
				output = new PrintWriter(file);
				for(int i=0;i<nodes.size();i++){
					for(int j=0;j<graph.get(i).size();j++){
						System.out.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
						output.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
					}
					System.out.println("");
					output.println("");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();
		//}
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		nodes=data.ReadNode();
		edges=data.ReadEdge();
		weights=data.Readweight();
		edgesweights=data.setEdgeweight(weights,edges);
		ArrayList<ArrayList<Node>> graph=getGraph(edgesweights,nodes);


		File file=new File("adjacencylist.txt");
		PrintWriter output = null;
		try {
			output = new PrintWriter(file);
			for(int i=0;i<nodes.size();i++){
				for(int j=0;j<graph.get(i).size();j++){
					System.out.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
					output.print(graph.get(i).get(j).getNodeId()+"-"+graph.get(i).get(j).getWeight()+" ");
				}
				System.out.println("");
				output.println("");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		output.close();
		*/
	}
	
	
	
	
	
	
	
	protected static ArrayList<ArrayList<Node>> graph;
	public AdjacencyList(){
	}
	public AdjacencyList(List<Edge> Edges,List<Node> initNodes,List<Node> Nodes){
		getGraph(Edges,Nodes);
	}
	//根据边的起点ID，得到具有该ID的点
	public static Node StartgetNode(Edge edge,List<Node> Nodes){
		for(Node node:Nodes)
			if(edge.getStartId()==node.getNodeId())
				return node;
		return null;
	}
	//根据边的终点ID，得到具有该ID的点
	public static Node EndgetNode(Edge edge,List<Node> Nodes){
		for(Node node:Nodes)
			if(edge.getEndId()==node.getNodeId())
				return node;
		return null;
		
	}

	public static ArrayList<ArrayList<Node>> getGraph(List<Edge> Edges,List<Node> Nodes){
		graph=new ArrayList<ArrayList<Node>>();
		for(Node node:Nodes){
			ArrayList<Node> queue=new ArrayList<Node>();
			queue.add(node);
			graph.add(queue);
		}
		for(int i=0;i<Nodes.size();i++){
			for(Edge e:Edges){
				Node node=new Node();
				if(graph.get(i).get(0).getNodeId()==e.getStartId()){
					node=EndgetNode(e,Nodes);
					Node node1= new Node(node.getNodeId(),node.getNodeX(),node.getNodeY(),e.getWeight());
					graph.get(i).add(node1);
				}
				else if(graph.get(i).get(0).getNodeId()==e.getEndId()){
					node=StartgetNode(e,Nodes);
					Node node1= new Node(node.getNodeId(),node.getNodeX(),node.getNodeY(),e.getWeight());
					graph.get(i).add(node1);
					}
				}
			}
		return  graph;
		}
	}

