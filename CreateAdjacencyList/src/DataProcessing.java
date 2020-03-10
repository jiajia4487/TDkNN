import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataProcessing {
	public List<Edge> edges;//����ߵ���Ϣ��ID����ʼ�㣬�յ㣬����
	public List<Node> nodes;
	public List<Edge> edgesweights;//����ߵ�Ȩֵ
	private Scanner input1,input2,input3;
	//��ȡ����Ϣ
	public List<Edge> ReadEdge(){
		edges=new ArrayList<Edge>();
		File file = new File("oldenburgedge.txt");
		try {
			input1 = new Scanner(file);
			while(input1.hasNextLine()){
				String[] str=input1.nextLine().split(" ");
				Long id=Long.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				int t=Integer.valueOf(str[3]);
				Edge e=new Edge(id,x,y,t);
				edges.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return edges;
		}
	//��ȡ�����Ϣ
	public List<Node> ReadNode(){
		nodes=new ArrayList<Node>();
		File file = new File("oldenburgnode.txt");
		try {
			input2 = new Scanner(file);
			while(input2.hasNextLine()){
				String[] str=input2.nextLine().split(" ");
				int id=Integer.valueOf(str[0]);
				int x=Integer.valueOf(str[1]);
				int y=Integer.valueOf(str[2]);
				Node e=new Node(id,x,y,0);
				nodes.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return nodes;
		}
	//��ȡ�ߵ�Ȩֵ
	public List<Edge> Readweight(int i){
		List<Edge> weights=new ArrayList<Edge>();
		File file = new File("everyweightmin.txt");
		try {
			input3 = new Scanner(file);
			while(input3.hasNextLine()){
				String[] str=input3.nextLine().split("\t");
				//int size=str.length;
				Long id=Long.valueOf(str[0]);
				double weight=Double.valueOf(str[i]);
				Edge e=new Edge(id,weight);
				weights.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return weights;
		}
	
	
	
	
	
	
	
	//��ȡ�ߵ�Ȩֵ
	public List<Edge> Readweight(){
		List<Edge> weights=new ArrayList<Edge>();
		File file = new File("weightmax.txt");
		try {
			input3 = new Scanner(file);
			while(input3.hasNextLine()){
				String[] str=input3.nextLine().split("\t");
				//int size=str.length;
				Long id=Long.valueOf(str[0]);
				double weight=Double.valueOf(str[1]);
				Edge e=new Edge(id,weight);
				weights.add(e);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
		return weights;
		}
	
	//Ϊ�߸�Ȩֵ
	public List<Edge> setEdgeweight(List<Edge> edgesweights,List<Edge> edges){
		for(int i=0;i<edgesweights.size();i++){
			edges.get(i).setWeight(edgesweights.get(i).getWeight());
		}
		return edges;	
	}
}