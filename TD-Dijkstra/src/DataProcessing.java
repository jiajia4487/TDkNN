import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataProcessing {
	public List<Node> nodes1;//Nodeid,x,y
	public List<Edge> edges1;//Edgeid,Sid,Eid
	public List<Node> poi;
	public List<Node> nodes2;//id,Edgeid
	public List<Edge> edges2;//id,function
	//private Scanner input1,input2,input3,input4;
	//从文件中读取边的信息，包括ID,起点ID，终点ID，用于构建图graph
	public List<Edge> ReadEdge() throws IOException{
		edges1=new ArrayList<Edge>();
		File file = new File("oldenburgedge.txt");		
		try {		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			//input1 = new Scanner(file);
			while((s=bfr.readLine())!=null){
				//String[] str=input1.nextLine().split(" ");
				String[] str=s.split(" ");
				Long id=Long.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				Edge e=new Edge(id,x,y); 
				edges1.add(e);
				}
			
			
			bfr.close();
			fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return edges1;
		}
	//从文件中读取结点的信息，包括ID,坐标X，坐标Y
	public List<Node> ReadNode() throws IOException{
		nodes1=new ArrayList<Node>();
		File file = new File("oldenburgnode.txt");
		try {
			//input2 = new Scanner(file);
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			//input1 = new Scanner(file);
			while((s=bfr.readLine())!=null){
				
			
			//while(input2.hasNextLine()){
				String[] str=s.split(" ");
				int id=Integer.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				Node e=new Node(id,x,y);
				nodes1.add(e);
				}
			bfr.close();
			fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return nodes1;
		}
	//从文件savepoi中得到POI的ID,存入一个ArrayList<Node>中,为后续进行匹配
	public List<Node> GetPoi() throws  IOException{
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
			bfr.close();
			fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return poiId;
		}
	//从文件中得到边的时间函数,用于计算NN扩展路网
	public List<Edge> Getedgefunction() throws  IOException{
		List<Edge> edgefunction=new ArrayList<Edge>();
		File file = new File("edgefunction.txt");
		try {
			//input4= new Scanner(file);
			
			
			
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			String s=null;
			//input1 = new Scanner(file);
			while((s=bfr.readLine())!=null){
			
			
			int cotr=0;
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
			bfr.close();
			fr.close();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return edgefunction;
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
					Node node=new Node(nodeid,edgeid);
					adjNode.add(node);
					}
				else if(nodes.get(i).getNodeId()==edges.get(j).getEndId()){
					Long edgeid=edges.get(j).getEdgeId();;
					int nodeid=edges.get(j).getStartId();
					Node node=new Node(nodeid,edgeid);
					adjNode.add(node);
					}
				}
			Adjacency adj=new Adjacency(nodes.get(i).getNodeId(), adjNode);
			graph.add(adj);
			}
		return graph;
		}
}
	
	
	
	
	
	
