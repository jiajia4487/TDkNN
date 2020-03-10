import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class TDINE {
	DataProcessing dataprocess=new DataProcessing();
	List<Node> poi=new ArrayList<Node>();
	List<Edge> edges=new ArrayList<Edge>();
	double INFINITY=9999;
	//����Vertex���͵��ڽӱ�,��������ڼ��㡣����ǳ���Ȥ��
	public List<Vertex> createAdjacency(ArrayList<Adjacency> AdjacencyLists) throws IOException{
		poi=dataprocess.GetPoi();
		List<Vertex> Adjacency=new ArrayList<Vertex>();
		for(int j=0;j<AdjacencyLists.size();j++){
			Vertex vertex=new Vertex(AdjacencyLists.get(j).getId(),AdjacencyLists.get(j).getAdjnode(),false,INFINITY,INFINITY,INFINITY,null,false);
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
	public  Comparator<Vertex> dgeneComparator = new Comparator<Vertex>(){ 
		@Override
		public int compare(Vertex c1, Vertex c2) {
			if (c1.getDist()- c2.getDist()>=0)
				return 1;
			else
			return   -1;
			}
		};
	//IDƥ��
	public static Vertex MatchNodeId(List<Vertex> adj,int NodeId){
		int label = 0;
		for(int i=0;i<adj.size();i++){
			if(adj.get(i).getID()==NodeId)
				label=i;
			}
		return adj.get(label);
		}
	public static Edge MatchEdgeId(List<Edge> adj,Long EdgeId){
		int label = 0;
		for(int i=0;i<adj.size();i++){
			if(adj.get(i).getEdgeId().equals(EdgeId))
				label=i;
			}
		return adj.get(label);
		}
	
	public List<Vertex> SearchNN(List<Edge> edges,List<Vertex> Adjacency,double searchtime,int querynodeid,int k) throws IOException{
		long startTime=System.nanoTime()/1000000L;
		List<Vertex> NN=new ArrayList<Vertex>();		
		PriorityQueue<Vertex> temporarylist=new PriorityQueue<Vertex>(7, dgeneComparator);
		Vertex query=new Vertex();
		int Nodenumber=0;
		//�õ���ѯ����ڽӵ㣬���뵽temporarylist��
		for(int i=0;i<Adjacency.size();i++){
			if(Adjacency.get(i).getID()==querynodeid){
				double dist=0;
				double At=0;
				query=Adjacency.get(i);
				for(int j=0;j<query.getAdj().size();j++){
					Nodenumber++;
					Vertex Vk=new Vertex();
					Edge edge=new Edge();	
					Vk=MatchNodeId(Adjacency,query.getAdj().get(j).getNodeId());
					edge=MatchEdgeId(edges,query.getAdj().get(j).getEdgeId());
					for(int k1=0;k1<edge.getWeightfunction().size();k1++){
						if(edge.getWeightfunction().get(k1).getTimeStart()<=searchtime&&searchtime<=edge.getWeightfunction().get(k1).getTimeEnd()){
							dist=searchtime*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();
							At=(dist+searchtime)%1440;
							break;
						}		
					}
					Vk.setPath(query);
					Vk.setDist(dist);
					Vk.setTt(dist);
					Vk.setAt(At);
					temporarylist.add(Vk);
					}
				break;
				}
			}
		
		//����K����
		while(NN.size()<k){
			double Tt,At;
			Vertex Vmin=new Vertex();
			Vmin=temporarylist.peek();			
			temporarylist.remove();
			Vmin.setSign(true);
			if((Vmin.isIfPoi()==true)&&(!NN.contains(Vmin))){
				NN.add(Vmin);
				}
			Tt=Vmin.getDist();
			At=Vmin.getAt();
			Nodenumber++;
			for(int i=0;i<Vmin.getAdj().size();i++){
				Vertex Vk=new Vertex();	
				Vk=MatchNodeId(Adjacency,Vmin.getAdj().get(i).getNodeId());
				if(Vk.getID()!=query.getID()&&Vk.isSign()==false){
					Nodenumber++;
					Edge edge=new Edge();
					double NodeTt=0;
					double dist=0;
					double NodeAt=0;
					Vk.setPath(Vmin);
					edge=MatchEdgeId(edges,Vmin.getAdj().get(i).getEdgeId());
					//ͨ���ߵĺ����õ�ͨ�������õ�ʱ��NodeTt
					for(int k1=0;k1<edge.getWeightfunction().size();k1++){
						if(edge.getWeightfunction().get(k1).getTimeStart()<=At&&At<=edge.getWeightfunction().get(k1).getTimeEnd()){
							NodeTt=At*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();						
						    NodeAt=(NodeTt+At)%1440;
						    dist=NodeTt+Tt;
						break;
						}
					}
					//�ʼǱ�ע˵����Ϊ����ɾ���������
					if((temporarylist.contains(Vk))&&(Vk.getDist()>dist)){
						temporarylist.remove(Vk);
						Vk.setTt(NodeTt);
					    Vk.setDist(dist);
					    Vk.setAt(NodeAt);
					    temporarylist.add(Vk);
					    }
					else{
						Vk.setTt(NodeTt);
						Vk.setDist(dist);
						Vk.setAt(NodeAt);
						temporarylist.add(Vk);
						}
					}
				}
			}
		//long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
		 long endTime=System.nanoTime()/1000000L;
		//System.out.println("Time2�� "+(endTime-startTime)+"ms"+";"+"ExpNode�� "+Nodenumber);
		System.out.println((endTime-startTime)+";"+Nodenumber);
		//System.out.println("��չ������ "+Nodenumber);
		return NN;
		}
	}

		
		
		
		
		
		
		
		
		
	