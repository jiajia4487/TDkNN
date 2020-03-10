import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class initialization {
	private Scanner input,input1;
	File file=new File("savepoi.txt");
	File file1=new File("adjacencylistmin6.txt");
	//从文件savepoi中得到POI的ID,存入一个ArrayList<Node>中,为后续进行匹配
	public ArrayList<Node> GetPoi(){
		ArrayList<Node> poiId=new ArrayList<Node>();
		try {
			input = new Scanner(file);
			while(input.hasNextLine()){
				String[] str=input.nextLine().split(" ");
				int id=Integer.valueOf(str[0]);
				Node poi=new Node(id);
				poiId.add(poi);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return poiId;
		}
	//初始化，并匹配ID
	public ArrayList<Vertex> Initlist(){
		ArrayList<Vertex> Initlist=new ArrayList<Vertex>();
		try {
			input1 = new Scanner(file1);
			int cotr=0;
 			while(input1.hasNextLine()){
				cotr++;
				//String str1=input1.nextLine();
				String[] str=input1.nextLine().split(" ");
				ArrayList<Node> adj=new ArrayList<Node>();
				Vertex firstlistnode=new Vertex();
				for (String x:str){
					String[] Info=x.split("-");
					int nodeid=Integer.valueOf(Info[0]);
					double weight=Double.valueOf(Info[1]);
					System.out.println(nodeid+"-"+weight);
					if(cotr!=1){
						Node e=new Node();
						e.setNodeId(nodeid);
						e.setWeight(weight);
						adj.add(e);					
						//poi.setAdj(adj);
					}
					else{
						cotr++;
					}										
				}				
				String[] ID=str[0].split("-");
				int id=Integer.valueOf(ID[0]);
				firstlistnode.setID(id);
				firstlistnode.setAdj(adj);
				firstlistnode.setDgene(9999);
				firstlistnode.setSign(false);
				//ArrayList<Integer> NodeId=new ArrayList<Integer>();
				//NodeId.add(0);
				//firstlistnode.setVgeneNodeId(NodeId);
				firstlistnode.setVpred(null);
				Initlist.add(firstlistnode);
				cotr=0;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}			
		return Initlist;	
	}
	//从初始化的ArrayList<Vertex>中匹配POI,并对POI初始化
	public ArrayList<Vertex> match(ArrayList<Vertex> init,ArrayList<Node> poi){
		//ArrayList<Integer> NodeId=new ArrayList<Integer>();
		//NodeId.add(0);
		//ArrayList<Vertex> Vpred=new ArrayList<Vertex>();
		//兴趣点POI的前驱为虚拟点virtual
		Vertex virtual=new Vertex(0,null,null,null,0,true);
		for(int i=0;i<poi.size();i++){
			for(int j=0;j<init.size();j++){
				if(poi.get(i).getNodeId()==init.get(j).getID())
				{
					ArrayList<Integer> NodeId=new ArrayList<Integer>();
					ArrayList<Vertex> Vpred=new ArrayList<Vertex>();
					//兴趣点的dist置为0
					init.get(j).setDgene(0);
					NodeId.add(poi.get(i).getNodeId());
					init.get(j).setVgeneNodeId(NodeId);
					Vpred.add(virtual);
					init.get(j).setVpred(Vpred);
				}									
			}
		}
		return init;
		
	}
	
}