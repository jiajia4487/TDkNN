import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
	
public class TDNEA {
	DataProcessing dataprocess=new DataProcessing();
	List<Node> poi=new ArrayList<Node>();
	List<Edge> edges=new ArrayList<Edge>();
	double INFINITY=9999;
	public  Comparator<Vertex> dgeneComparator = new Comparator<Vertex>(){ 
		@Override
		public int compare(Vertex c1, Vertex c2) {
			//return  (int) (c1.getDist()- c2.getDist());
			if (c1.getDist()- c2.getDist()>=0)
				return 1;
			else
			return   -1;
			}
		};
					
	//匹配点ID
	public static Vertex MatchNodeId(List<Vertex> adj,int NodeId){
		int label = 0;
		for(int i=0;i<adj.size();i++){
			if(adj.get(i).getID()==NodeId)
				label=i;
			}
		return adj.get(label);
		}
	//匹配边ID
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
		Vertex query=new Vertex();
		List<Vertex> NN=new ArrayList<Vertex>();
		PriorityQueue<Vertex> temporarylist=new PriorityQueue<Vertex>(7, dgeneComparator);
		int Nodenumber=0;
		//得到查询点的邻接点，加入到temporarylist中
		for(int i=0;i<Adjacency.size();i++){
			if(Adjacency.get(i).getID()==querynodeid){
				query=Adjacency.get(i);
				for(int j=0;j<query.getAdj().size();j++){
					Nodenumber++;
					double At=0;
					double Tt=0;
					double dist=0;
					int poimin=10000;
					Vertex Vk=new Vertex();
					Edge edge=new Edge();	
					Vk=MatchNodeId(Adjacency,query.getAdj().get(j).getNodeId());
					edge=MatchEdgeId(edges,query.getAdj().get(j).getEdgeId());
					for(int k1=0;k1<edge.getWeightfunction().size();k1++){
						if(edge.getWeightfunction().get(k1).getTimeStart()<=searchtime&&searchtime<=edge.getWeightfunction().get(k1).getTimeEnd()){
							Tt=searchtime*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();
							At=searchtime+Tt;
							dist=Tt+query.getAdj().get(j).getNodes().get(0).getMinweight();
							poimin=query.getAdj().get(j).getNodes().get(0).getMinpoi();
							break;
						}
					}
					Vk.setPath(query);
					Vk.setNodeTt(Tt);
					Vk.setTt(Tt);
					Vk.setAt(At);
					Vk.setDist(dist);
					Vk.setPoiid(poimin);
					temporarylist.add(Vk);
					}
				break;
				}
			}
		//寻找最近邻
		while(NN.size()<k){
			double ArriveTime,AllTt;
			Vertex Vmin=new Vertex();
			Vmin=temporarylist.peek();
			Vmin.setSign(true);
			ArriveTime=(Vmin.getAt())%1440;
			AllTt=Vmin.getTt();
			temporarylist.remove();
			Nodenumber++;
			//便遍历最小的邻接点
			
 			for(int i=0;i<Vmin.getAdj().size();i++){
 				Vertex Vk=new Vertex();
				double dist=0;
				double Tt=0;
				Vk=MatchNodeId(Adjacency,Vmin.getAdj().get(i).getNodeId());
				if(Vk.getID()!=query.getID()&&Vk.isSign()==false){
					Edge edge=new Edge();
					double At=0;
					double NodeTt=0; 
					Nodenumber++;
					Vk.setPath(Vmin);
					edge=MatchEdgeId(edges,Vmin.getAdj().get(i).getEdgeId());
					for(int k1=0;k1<edge.getWeightfunction().size();k1++){
						if(edge.getWeightfunction().get(k1).getTimeStart()<=ArriveTime&&ArriveTime<=edge.getWeightfunction().get(k1).getTimeEnd()){
							NodeTt=ArriveTime*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();
						    At=NodeTt+ArriveTime;
						    Tt=NodeTt+AllTt;
						    dist=Tt+Vmin.getAdj().get(i).getNodes().get(0).getMinweight();
						    break;
						    }
						}
					//笔记备注说明，为何先删除，再添加	
					if(temporarylist.contains(Vk)&&Vk.getDist()>dist){
						temporarylist.remove(Vk);
					    Vk.setDist(dist);
					    Vk.setNodeTt(NodeTt);
					    Vk.setTt(Tt);
					    Vk.setAt(At);
					    Vk.setDist(dist);
					    temporarylist.add(Vk);
					    }
					else{
						Vk.setDist(dist);
						Vk.setNodeTt(NodeTt);
						Vk.setTt(Tt);
						Vk.setAt(At);
						Vk.setDist(dist);
						temporarylist.add(Vk);
						}
					}
				if((Tt==dist)&&(!NN.contains(Vk))&&(Vk.isIfPoi()==true)){
					NN.add(Vk);
					}
				}
 			}
		long endTime=System.nanoTime()/1000000L;
		//System.out.println("Time2： "+(endTime-startTime)+"ms"+";"+"ExpNode： "+Nodenumber);
		System.out.println((endTime-startTime)+";"+Nodenumber);
		//System.out.println("扩展点数： "+Nodenumber);
		return NN;
		}
	}

	
	


