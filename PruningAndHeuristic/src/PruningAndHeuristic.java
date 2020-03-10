import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class PruningAndHeuristic {
	private Scanner input;
	private Scanner input2;
	private Scanner input3;
	//���ļ��ж�ȡ������Ϣ������ID,����X������Y
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
	//��ȡ����VoronoiMin�е���Ϣ
	public ArrayList<Node> Readmin(int i){
		ArrayList<Node> unitnode=new ArrayList<Node>();
		File file=new File("voronoimin"+(i)+".txt");
		try {
			input2 = new Scanner(file);
			while(input2.hasNextLine()){
				//2596-0.0	2596;���ݸ�ʽ:���ID-������ڵ�ֵ �����
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
	//��ȡ����VoronoiMax�е���Ϣ
	public ArrayList<Node> Readmax(int i){
		ArrayList<Node> unitnode=new ArrayList<Node>();
		File file=new File("voronoimax"+(i)+".txt");
		try {
			input = new Scanner(file);
			while(input.hasNextLine()){
				//2596-0.0	2596,���ݸ�ʽ�����ID��������ڵ�ֵ�������
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
	
	
	//�õ�Node��������������֦����������Ϣ
	public ArrayList<Node> GetNode(ArrayList<Node> nodes){
        //�ṹArrayList<Node>,����Node(NodeId,ArrayList<Node>),���е�NodeΪNode(NodeId,minpoi,minweight,maxpoi,maxweight)
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
				//�õ�Node��min,max��Ϣ
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

