import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class PruningAndHeuristic {
	private Scanner input;
	private Scanner input2;
	private Scanner input3;
	//从文件中读取结点的信息，包括ID,坐标X，坐标Y
	public ArrayList<Node> ReadNode(){
		ArrayList<Node> nodes1=new ArrayList<Node>();
		File file = new File("oldenburgnode.txt");
		try {
			input3 = new Scanner(file);
			while(input3.hasNextLine()){
				String[] str=input3.nextLine().split(" ");
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
	//读取最大的VoronoiMin中的信息
	public ArrayList<Node> Readmin(int i){
		ArrayList<Node> unitnode=new ArrayList<Node>();
		File file=new File("voronoimin"+(i)+".txt");
		try {
			input2 = new Scanner(file);
			while(input2.hasNextLine()){
				//2596-0.0	2596;数据格式:点的ID-到最近邻的值 最近邻
				String[] str=input2.nextLine().split("\t");
				int poiid=Integer.valueOf(str[1]);
				String[] str1=str[0].split("-");
				int Nodeid=Integer.valueOf(str1[0]);
				double weight=Double.valueOf(str1[1]);
				Node e=new Node(Nodeid,(long)0,poiid,weight,0,0);
				unitnode.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return unitnode;
		}
	//读取最大的VoronoiMax中的信息
	public ArrayList<Node> Readmax(int i){
		ArrayList<Node> unitnode=new ArrayList<Node>();
		File file=new File("voronoimax"+(i)+".txt");
		try {
			input = new Scanner(file);
			while(input.hasNextLine()){
				//2596-0.0	2596,数据格式，点的ID，到最近邻的值，最近邻
				String[] str=input.nextLine().split("\t");
				int poiid=Integer.valueOf(str[1]);
				String[] str1=str[0].split("-");
				int Nodeid=Integer.valueOf(str1[0]);
				double weight=Double.valueOf(str1[1]);
				Node e=new Node(Nodeid,(long)0,0,0,poiid,weight);
				unitnode.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return unitnode;
		}
	
	
	//得到Node的链表，并包含剪枝和启发的信息
	public ArrayList<Node> GetNode(ArrayList<Node> nodes){
        //结构ArrayList<Node>,其中Node(NodeId,ArrayList<Node>),其中的Node为Node(NodeId,minpoi,minweight,maxpoi,maxweight)
		ArrayList<Node> node=new ArrayList<Node>();
		//ArrayList<Node> minmaxnode=new ArrayList<Node>();
		for(int j=0;j<nodes.size();j++){
		ArrayList<Node> minmaxnode=new ArrayList<Node>();	
		for(int i=0;i<1;i++){
		//for(int i=0;i<nodes.size();i++){
			ArrayList<Node> minnode=new ArrayList<Node>();
			ArrayList<Node> maxnode=new ArrayList<Node>();
			minnode=Readmin(i+1);
			maxnode=Readmax(i+1);
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
			System.out.println(j);
		}
		
		return node;
	}	
	
	
	
	

}

