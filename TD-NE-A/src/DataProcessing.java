import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProcessing {
	public ArrayList<Node> nodes1;//Nodeid,x,y
	public List<Edge> edges1;//Edgeid,Sid,Eid
	public List<Node> poi;
	public List<Node> nodes2;//id,Edgeid
	public List<Edge> edges2;//id,function
	//private Scanner input1,input2,input3,input4;
	double INFINITY=9999;
	//PruningAndHeuristic pah=new PruningAndHeuristic();
	//从文件中读取边的信息，包括ID,起点ID，终点ID，用于构建图graph
	public List<Edge> ReadEdge() throws IOException{
		edges1=new ArrayList<Edge>();
		File file = new File("oldenburgedge.txt");
		try {
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			while((s=bfr.readLine())!=null){
			
			
			
			
			
			//input1 = new Scanner(file);
			//while(input1.hasNextLine()){
				String[] str=s.split(" ");
				Long id=Long.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				Edge e=new Edge(id,x,y); 
				edges1.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return edges1;
		}
	
	//从文件中读取结点的信息，包括ID,坐标X，坐标Y
	public ArrayList<Node> ReadNode() throws IOException{
		nodes1=new ArrayList<Node>();
		File file = new File("oldenburgnode.txt");
		try {
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			while((s=bfr.readLine())!=null){
			//input2 = new Scanner(file);
			//while(input2.hasNextLine()){
				String[] str=s.split(" ");
				int id=Integer.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				Node e=new Node(id,x,y);
				nodes1.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return nodes1;
		}
	
	//从文件savepoi中得到POI的ID,存入一个ArrayList<Node>中,为后续进行匹配
	public List<Node> GetPoi() throws IOException{
		List<Node> poiId=new ArrayList<Node>();
		File file = new File("savepoi.txt");
		try {
			//input3= new Scanner(file);
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			//input1 = new Scanner(file);
			while((s=bfr.readLine())!=null){
			
			
			
			
			
			//while(input3.hasNextLine()){
				String[] str=s.split(" ");
				int id=Integer.valueOf(str[0]);
				Node poi=new Node(id);
				poiId.add(poi);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return poiId;
		}
	
	//从文件中得到边的时间函数,用于计算NN扩展路网
	public List<Edge> Getedgefunction() throws IOException{
		List<Edge> edgefunction=new ArrayList<Edge>();
		File file = new File("edgefunction.txt");
		try {
			//input4= new Scanner(file);
			int cotr=0;
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			while((s=bfr.readLine())!=null){
			
			
			
			
			
			//while(input4.hasNextLine()){
				cotr++;
				Long edgeid;
				double k,b;
				int Stime,Etime;
				String[] str=s.split("\t");
				//边的ID
				edgeid=Long.valueOf(str[0]);
				//边的分段函数
				ArrayList<FunctionUnit> piecewise=new ArrayList<FunctionUnit>();
				for (String x:str){
					String[] Info=x.split(" ");
					if(cotr!=1){
						k=Double.valueOf(Info[0]);
						b=Double.valueOf(Info[1]);
						Stime=Integer.valueOf(Info[2]);
						Etime=Integer.valueOf(Info[3]);
						//边的分段函数的一个分段的斜率，截距，时间区间
						FunctionUnit unit=new FunctionUnit(k,b,Stime,Etime);
						piecewise.add(unit);
						
						}
					else{
						cotr++;
						}
					}
				Edge e=new Edge(edgeid,piecewise);
				edgefunction.add(e);
				cotr=0;
				} 
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return edgefunction;
	}
	/*
	//得到Node的链表，并包含剪枝和启发的信息
	public ArrayList<Node> GetNode(ArrayList<Node> nodes){
        //结构ArrayList<Node>,其中Node(NodeId,ArrayList<Node>),其中的Node为Node(NodeId,minpoi,minweight,maxpoi,maxweight)
		ArrayList<Node> node=new ArrayList<Node>();
		ArrayList<Node> minmaxnode=new ArrayList<Node>();
		for(int j=0;j<nodes.size();j++){
		for(int i=0;i<6;i++){
		//for(int i=0;i<nodes.size();i++){
			ArrayList<Node> minnode=new ArrayList<Node>();
			ArrayList<Node> maxnode=new ArrayList<Node>();
			minnode=pah.Readmin(i+1);
			maxnode=pah.Readmax(i+1);
			//ArrayList<Node> minmaxnode=new ArrayList<Node>();			 
			//for(int j=0;j<nodes.size();j++){
			//for(int j=0;j<6;j++){
				//minnode=Readmin(j+1);
				//maxnode=Readmax(j+1);
				Node e=new Node();
				//ArrayList<Node> minmaxnode=new ArrayList<Node>();
				//得到Node的min,max信息
				for(int k=0;k<minnode.size();k++){
					if(nodes.get(j).getNodeId()==minnode.get(k).getNodeId()){
						e.setNodeId(nodes.get(j).getNodeId());
						e.setMinpoi(minnode.get(k).getMinpoi());
						e.setMinweight(minnode.get(k).getMinweight());
					}
					if(nodes.get(j).getNodeId()==maxnode.get(k).getNodeId()){
						e.setNodeId(nodes.get(j).getNodeId());
						e.setMaxpoi(maxnode.get(k).getMaxpoi());
						e.setMaxweight(maxnode.get(k).getMaxweight());
					}	
				}
				//minmaxnode.add(e);
				//Node e1=new Node(nodes.get(i).getNodeId(),minmaxnode);	
				//node.add(e1);
				minmaxnode.add(e);
			}
			
			Node e1=new Node(nodes.get(j).getNodeId(),minmaxnode);	
			node.add(e1);
			//System.out.println(j);
		}
		
		return node;
	}	
	*/	
	
	
	public ArrayList<Node> GetNode() throws IOException{
        //结构ArrayList<Node>,其中Node(NodeId,ArrayList<Node>),其中的Node为Node(NodeId,minpoi,minweight,maxpoi,maxweight)
		ArrayList<Node> node=new ArrayList<Node>();
		//ArrayList<Node> minmaxnode=new ArrayList<Node>();
		File file = new File("PruningandheuristicALLDay.txt");
		try {
			//input2 = new Scanner(file);
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			while((s=bfr.readLine())!=null){
			
			
			
			//while(input2.hasNextLine()){
				String[] str=s.split("\t");
				int id=Integer.valueOf(str[0]);
				ArrayList<Node> minmaxnode=new ArrayList<Node>();
				for(int i=1;i<str.length;i++){
					String[] str1=str[i].split("-");
					int id1=Integer.valueOf(str1[0]);
					int minpoi=Integer.valueOf(str1[1]);
					double minweight=Double.valueOf(str1[2]);
					int maxpoi=Integer.valueOf(str1[3]);
					double maxweight=Double.valueOf(str1[4]);
					Node e=new Node(id,(long)10,minpoi,minweight,maxpoi,maxweight);
					minmaxnode.add(e);
				}
				
				Node nodes=new Node(id,minmaxnode);
				node.add(nodes);
				
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return node;
		}
	
	
	
	public  Node MatchNodeId(List<Node> nodes,int NodeId){
		int label = 0;
		for(int i=0;i<nodes.size();i++){
			if(nodes.get(i).getNodeId()==NodeId)
				label=i;
			}
		return nodes.get(label);
		}	
	//通过文件中传入的边和点的信息，构建图信息(即邻接表，包含点和边的id,为了扩展网络时，快速得到边的ID)
	public ArrayList<Adjacency> CreateGraph(List<Edge> edges,List<Node> nodes){
		ArrayList<Adjacency> graph=new ArrayList<Adjacency>();
		for(int i=0;i<nodes.size();i++){
			ArrayList<Node> adjNode=new ArrayList<Node>();
			for(int j=0;j<edges.size();j++){
				if(nodes.get(i).getNodeId()==edges.get(j).getStartId()){
					Long edgeid=edges.get(j).getEdgeId();
					int nodeid=edges.get(j).getEndId();					
					Node node1=MatchNodeId(nodes,nodeid);
					Node node= new Node(nodeid,node1.getNodes(),edgeid);
					adjNode.add(node);
					}
				else if(nodes.get(i).getNodeId()==edges.get(j).getEndId()){
					Long edgeid=edges.get(j).getEdgeId();
					int nodeid=edges.get(j).getStartId();
					Node node1=MatchNodeId(nodes,nodeid);
					Node node= new Node(nodeid,node1.getNodes(),edgeid);
					adjNode.add(node);
					}
				}
			Adjacency adj=new Adjacency(nodes.get(i).getNodeId(), adjNode);
			graph.add(adj);
			}
		return graph;
		}
	
	//建立邻接表，用A星算法求NN时用的邻接表,并标记出兴趣点
	public List<Vertex> createAdjacency(ArrayList<Adjacency> AdjacencyLists) throws IOException{
		poi=GetPoi();
		List<Vertex> Adjacency=new ArrayList<Vertex>();
		for(int j=0;j<AdjacencyLists.size();j++){
			Vertex vertex=new Vertex(AdjacencyLists.get(j).getId(),AdjacencyLists.get(j).getAdjnode(),false,INFINITY,INFINITY,INFINITY,INFINITY,1000000,null,false);
			Adjacency.add(vertex);		
		}
		for(int i=0;i<poi.size();i++){
			for(int j=0;j<Adjacency.size();j++){
				if(Adjacency.get(j).getID()==poi.get(i).getNodeId()){
					Adjacency.get(j).setIfPoi(true);
					}
				}
			}
		return Adjacency;
		}
	
}
