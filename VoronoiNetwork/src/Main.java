import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main {
	static ArrayList<Vertex> Plabel=new ArrayList<Vertex>();
	static ArrayList<ArrayList<Vertex>> TimePlabel=new ArrayList<ArrayList<Vertex>>();
	static ArrayList<Node> poi=new ArrayList<Node>();
	static ArrayList<Vertex> init=new ArrayList<Vertex>();
	static initialization ital=new initialization();
	static double min;
	static int minID;
	//IDƥ��
	public static Vertex MatchId(ArrayList<Vertex> adj,int NodeId){
		int label = 0;
		for(int i=0;i<adj.size();i++){
			if(adj.get(i).getID()==NodeId)
				label=i;
		}
		return adj.get(label);	
	}
	 public static Comparator<Vertex> dgeneComparator = new Comparator<Vertex>(){
		 
	        @Override
	        public int compare(Vertex c1, Vertex c2) {
	            //return  (int) (c1.getDgene() - c2.getDgene());
	        	if ((c1.getDgene() - c2.getDgene())>=0)
					return 1;
				else
					return   -1;
	        }
	    };
	    
	static PriorityQueue<Vertex> Vtlabel=new PriorityQueue<Vertex>(7, dgeneComparator);

	public static void main(String[] args){
		//�õ���Ȥ��POI
		poi=ital.GetPoi();
		//���ļ��ж�ȡ�ڽӱ����Ϣ
		init=ital.Initlist();
		//�õ���ʼ����ArrayList<Vertex>����POIƥ�䵽��Ӧ�ĵ�
		init=ital.match(init, poi);
		for(int i=0;i<init.size();i++){
			//����Ȥ����뵽Vtlabel
			if(init.get(i).getDgene()==0){
				Vtlabel.add( init.get(i));
			}
		}
		//������Ȥ���Ƿ�������һ��
		System.out.println(Vtlabel.size()+"�ϵ�");
		long startTime=System.nanoTime()/1000000L;
		while(Vtlabel.size()!=0){
			Vertex Vmin=new Vertex();
			Vmin=Vtlabel.peek();
			min=Vtlabel.peek().getDgene();
			minID=Vtlabel.peek().getID();
			Vtlabel.remove();
			Vmin.setSign(true);
			Plabel.add(Vmin);
			for(int i=0;i<init.size();i++){
				if(init.get(i).getID()==minID){
					for(int j=0;j<init.get(i).getAdj().size();j++){
						Vertex Vk=new Vertex();
						//�õ��ڽӵ��ID��ƥ�䵽AaaryList<Vertex>��
						Vk=MatchId(init,init.get(i).getAdj().get(j).getNodeId());
						if(Vk.isSign()==true){
							continue;
							}
						else {
							double D=init.get(i).getDgene()+init.get(i).getAdj().get(j).getWeight();
							if(Vk.getDgene()==9999){
							ArrayList<Vertex> Vpred=new ArrayList<Vertex>();
							Vpred.add(Vmin);
							Vk.setVgeneNodeId(Vmin.getVgeneNodeId());
							Vk.setVpred(Vpred);
							Vk.setDgene(D);
							Vtlabel.add(Vk);
						}
						else if(Vk.getDgene()>D){
							Vtlabel.remove(Vk);
							ArrayList<Vertex> Vpred=new ArrayList<Vertex>();
							Vpred.add(Vmin);
							Vk.setVgeneNodeId(Vmin.getVgeneNodeId());
							Vk.setVpred(Vpred);
							Vk.setDgene(D);
							Vtlabel.add(Vk);
						}
						else if(Vk.getDgene()==D){
							ArrayList<Integer> NodeId=new ArrayList<Integer>();
							NodeId.addAll(Vmin.getVgeneNodeId());
							NodeId.addAll(Vk.getVgeneNodeId());
							//�õ�ǰ���������������
							ArrayList<Vertex> Vpred=new ArrayList<Vertex>();
							Vpred.add(Vmin);
							for(Vertex v:Vk.getVpred()){
								Vpred.add(v);
							}
							Vk.setVgeneNodeId(NodeId);
							Vk.setVpred(Vpred);
							}
						}
					}
					
				break;
				}			
			}
		}
long endTime=System.nanoTime()/1000000L;
System.out.println("Time1�� "+(endTime-startTime)+"ms");
		/*
		 System.out.println("Plabel�ĳ��ȣ�"+Plabel.size()); 	 
		 File file=new File("voronoi.txt");
			PrintWriter output = null;
			try {
				output = new PrintWriter(file);
				for(int i=0;i<poi.size();i++){
					System.out.print("�� "+i+"��Ԫ�ص�IDΪ"+poi.get(i).getNodeId()+",����Ϊ��������ĵ��IDΪ��");
					for(int j=0;j<Plabel.size();j++){
						if(Plabel.get(j).getVgeneNodeId().contains(poi.get(i).getNodeId())){
							 output.print(Plabel.get(j).getID()+"-"+Plabel.get(j).getDgene()+"\t");
							 System.out.print(Plabel.get(j).getID()+"-"+Plabel.get(j).getDgene()+"\t");
						 }
					 }	
					 System.out.print('\n');
					 output.print('\n');
				 }
				 } catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();*/
		System.out.println("Plabel�ĳ��ȣ�"+Plabel.size());
		File file=new File("voronoimin6.txt");
		PrintWriter output = null;
		try {
			output = new PrintWriter(file);
			for(int j=0;j<Plabel.size();j++){
				output.print(Plabel.get(j).getID()+"-"+Plabel.get(j).getDgene()+"\t");
				output.print(Plabel.get(j).getVgeneNodeId().get(0));
				/*
				for(Iterator<Integer> it=Plabel.get(j).getVgeneNodeId().iterator();it.hasNext();)
					output.print(it.next());
					*/
				
				
				
				//System.out.print(Plabel.get(j).getID()+"-"+Plabel.get(j).getDgene()+"\t");
				for(Iterator<Integer> it=Plabel.get(j).getVgeneNodeId().iterator();it.hasNext();)
					System.out.print(it.next());
					// System.out.print('\n');
					 output.print('\n');
			}
				 
				 } catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();
		
		
		
		
		
		
		}	
}


