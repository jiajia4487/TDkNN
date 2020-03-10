import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	static ArrayList<Node> nodes;
	static List<Node> pahnodes;
	static List<Edge> edges;
	static List<Edge> edges1;
	static ArrayList<Adjacency> graph;
	static TDA td=new TDA();
	static DataProcessing dataprocess=new DataProcessing();
	static List<Vertex> nnlist=new ArrayList<Vertex>();
	static List<Vertex> Adjacency=new ArrayList<Vertex>();
	private static Scanner input1;
	public static void main(String[] args) throws IOException{
		input1 = new Scanner(System.in);
		System.out.println("��Ҫ���ҵ�K���ڸ�����");
		int k=input1.nextInt();
		System.out.println("�����ѯ��ʱ��Ϊ��");
		double time=input1.nextDouble();
		System.out.println("������ID��");
		int querynodeid=input1.nextInt();
		edges1=dataprocess.Getedgefunction();
		edges=dataprocess.ReadEdge();
		pahnodes=dataprocess.GetNode();
		graph=dataprocess.CreateGraph(edges, pahnodes);
		
		Adjacency=dataprocess.createAdjacency(graph);
				
		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
		
		nnlist=td.SearchNN(edges1,Adjacency, time, querynodeid, k);
		
		long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
		
		for(int k1=0;k1<nnlist.size();k1++){
			System.out.print("·��Ϊ��");
			Vertex tem=new Vertex();
			tem=nnlist.get(k1);
			System.out.print(nnlist.get(k1).getID()+"("+nnlist.get(k1).getTt()+")");
			double sum=nnlist.get(k1).getTt();
		while(tem.getPath()!=null){
			System.out.print("-");
			System.out.print(tem.getPath().getID()+"("+tem.getPath().getTt()+")");			
			sum=sum+tem.getPath().getTt();
				tem=tem.getPath();
			}
		sum=sum-9999;
		System.out.print("����ʱ��"+sum);
		System.out.println("\t");		
		}	
		
		for(int k1=0;k1<nnlist.size();k1++){
			Vertex tem=new Vertex();
			tem=nnlist.get(k1);
		System.out.print(nnlist.get(k1).getID()+"("+nnlist.get(k1).getTt()+")"+"\t");			
		}	

		
		
	}

}
