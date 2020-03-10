import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	static List<Node> nodes;
	static List<Edge> edges;
	static List<Edge> edges1;
	static ArrayList<Adjacency> graph;
	static TDINE td=new TDINE();
	static DataProcessing dataprocess=new DataProcessing();
	static List<Vertex> nnlist=new ArrayList<Vertex>();
	static List<Vertex> Adjacency=new ArrayList<Vertex>();
	private static Scanner input1;
	public static void main(String[] args) throws IOException{
		
	/*
		for(int m=0;m<30;m++){			
			int k=1;
			double time=Math.random()*1440;
			nodes=dataprocess.ReadNode();
			int querynodeid=nodes.get((int)(Math.random()*nodes.size())).getNodeId();
			
		*/
		
			
		input1 = new Scanner(System.in);
		System.out.println("需要查找的K近邻个数：");
		int k=input1.nextInt();
		System.out.println("发起查询的时间为：");
		double time=input1.nextDouble();
		System.out.println("发起点的ID：");
		int querynodeid=input1.nextInt();
			
			
			
			
			long startTime=System.nanoTime()/1000000L; 
			edges1=dataprocess.Getedgefunction();
			edges=dataprocess.ReadEdge();
			nodes=dataprocess.ReadNode();
			graph=dataprocess.CreateGraph(edges, nodes);
			Adjacency=td.createAdjacency(graph);
			nnlist=td.SearchNN(edges1,Adjacency, time, querynodeid, k);
			long endTime=System.nanoTime()/1000000L;
			//System.out.println("Time1： "+(endTime-startTime)+"ms");	
			
			
			/*
			for(int k1=0;k1<nnlist.size();k1++){	
				System.out.print("路径为：");
				Vertex tem=new Vertex();
				tem=nnlist.get(k1);
				System.out.print(nnlist.get(k1).getID()+"("+nnlist.get(k1).getTt()+")");
				while(tem.getPath()!=null){
					System.out.print("-");
					System.out.print(tem.getPath().getID()+"("+tem.getPath().getTt()+")");
					tem=tem.getPath();
					}
				System.out.print("花费时间"+nnlist.get(k1).getDist());
				System.out.println("\t");	
				
			}
			*/
			for(int k1=0;k1<nnlist.size();k1++){
				System.out.print(nnlist.get(k1).getID()+"\t");
				}
			//System.out.println();
		}
		
		}
	//}
