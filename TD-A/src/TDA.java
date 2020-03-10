import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
	
public class TDA {
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
		
		public  Comparator<Umax> dgeneComparator1 = new Comparator<Umax>(){ 
			@Override
			public int compare(Umax c1, Umax c2) {
				//return  (int) (c1.getPurningweight()- c2.getPurningweight());
				if (c1.getPurningweight()- c2.getPurningweight()>=0)
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
	
	public int PaH(double At){
		int number=6;
		At=At%1440;
		if((0<=At)&&(At<420)){
			number=0;
		}
		if((420<=At)&&(At<540)){
			number=1;
		}
		if((540<=At)&&(At<1020)){
			number=2;
		}
		if((1020<=At)&&(At<1140)){
			number=3;
		}
		if((1140<=At)&&(At<1320)){
			number=4;
		}
		if((1320<=At)&&(At<1440)){
			number=5;
		}
		return number;
	} 
	
	public boolean IdExist(List<Vertex> list,int id){
		for(Vertex V:list){
			if(V.getID()==id)
				return true;
			}	
		return false;
	}
	
	public PriorityQueue<Umax> UmExist(PriorityQueue<Umax> Un,Umax Um){
		boolean exist=false;
		for(Umax V:Un){
			if(V.getPoi()==Um.getPoi())
			{
				exist=true;
				if (V.getPurningweight()>Um.getPurningweight())
				{
					Un.remove(V);
					Un.add(Um);
				}
			}
			}
		if (!exist)
		{
			Un.add(Um);
		}
		return Un;	
		
	}
	
	public List<Vertex> SearchNN(List<Edge> edges,List<Vertex> Adjacency,double searchtime,int querynodeid,int k) throws IOException{
		//edges=dataprocess.Getedgefunction();
		long startTime=System.currentTimeMillis();   //获取开始时间
		List<Vertex> NN=new ArrayList<Vertex>();
		PriorityQueue<Vertex> temporarylist=new PriorityQueue<Vertex>(7, dgeneComparator);
		PriorityQueue<Umax> Un=new PriorityQueue<Umax>(2, dgeneComparator1);		
		Vertex query=new Vertex();
		//得到查询点的邻接点，加入到temporarylist中
       for(int i=0;i<Adjacency.size();i++){
    	   if(Adjacency.get(i).getID()==querynodeid){
    		   query=Adjacency.get(i);
    		   for(int j=0;j<query.getAdj().size();j++){
    			   double At=0;
    			   double Tt=0;
    			   double dist=0;
    			   double purningweight=0;
    			   int poimax=10000;
    			   int poimin=10000;
    			   Vertex Vk=new Vertex();
    			   Umax Um=new Umax();
    			   Edge edge=new Edge();
    			   Vk=MatchNodeId(Adjacency,query.getAdj().get(j).getNodeId());
    			   edge=MatchEdgeId(edges,query.getAdj().get(j).getEdgeId());
    			   for(int k1=0;k1<edge.getWeightfunction().size();k1++){
    				   if(edge.getWeightfunction().get(k1).getTimeStart()<=searchtime&&searchtime<=edge.getWeightfunction().get(k1).getTimeEnd()){
    					   Tt=searchtime*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();
    					   At=searchtime+Tt;
    					   int number;
    					   number=PaH(At);
    					   dist=Tt+query.getAdj().get(j).getNodes().get(number).getMinweight();
    					   poimin=query.getAdj().get(j).getNodes().get(number).getMinpoi();
    					   purningweight=At+query.getAdj().get(j).getNodes().get(number).getMaxweight();
    					   poimax=query.getAdj().get(j).getNodes().get(number).getMaxpoi();
    					   break;
    					   }
    				   }
    			   Vk.setPath(query);
    			   Vk.setTt(Tt);
    			   Vk.setAt(At);
    			   Vk.setDist(dist);
    			   Vk.setPoiid(poimin);
    			   temporarylist.add(Vk);
    			   Um.setPoi(poimax);
    			   Um.setPurningweight(purningweight);
    			   if (Un.isEmpty())
    			   {
    				   Un.add(Um);
    			   }
    			   else
    			   {
    				   Un=UmExist(Un,Um);
    				  
    			   }
   				   }
    		   break;
    		   }
    	   }
       
       
       
       
       
       
       //寻找最近邻
       int next=0;
       while(NN.size()<k){
    	   double ArriveTime,AllTt;
    	   Vertex Vmin=new Vertex();
    	   Vmin=temporarylist.peek();
    	   Vmin.setSign(true);
    	   ArriveTime=Vmin.getAt();
    	   AllTt=Vmin.getTt();
    	   temporarylist.remove();
    	   //遍历最小点的邻接点
    	   for(int i=0;i<Vmin.getAdj().size();i++){
    		   Vertex Vk=new Vertex();
    		   double dist=0;
    		   double Tt=0;
    		   Vk=MatchNodeId(Adjacency,Vmin.getAdj().get(i).getNodeId());
    		   if(Vk.getID()!=query.getID()&&Vk.isSign()==false){
					Edge edge=new Edge();
					double At=0;
					double EachTt=0;
					double purningweight=0;
					int poimax=10000;
					int poimin=10000;
					Umax Um=new Umax();
					Vk.setPath(Vmin);
					edge=MatchEdgeId(edges,Vmin.getAdj().get(i).getEdgeId());
					for(int k1=0;k1<edge.getWeightfunction().size();k1++){
						if((edge.getWeightfunction().get(k1).getTimeStart()<=ArriveTime)&&(ArriveTime<=edge.getWeightfunction().get(k1).getTimeEnd())){
							EachTt=ArriveTime*edge.getWeightfunction().get(k1).getK()+edge.getWeightfunction().get(k1).getB();
							At=EachTt+ArriveTime;
							Tt=EachTt+AllTt;
							int number;
							number=PaH(At);
							dist=Tt+Vmin.getAdj().get(i).getNodes().get(number).getMinweight();
							poimin=Vmin.getAdj().get(i).getNodes().get(number).getMinpoi();
							purningweight=Tt+Vmin.getAdj().get(i).getNodes().get(number).getMaxweight();
							poimax=Vmin.getAdj().get(i).getNodes().get(number).getMaxpoi();
							break;
							}
						}
					Um.setPoi(poimax);
				    Um.setPurningweight(purningweight);
				    PriorityQueue<Umax> tempUn1=new PriorityQueue<Umax>(1, dgeneComparator1);
				    PriorityQueue<Umax> tempUn2=new PriorityQueue<Umax>(1, dgeneComparator1);
				    if(!IdExist(NN,poimax)){
				    	boolean eq=false;
				    	Umax U1=Um;
				    	for(Umax U:Un){
				    		if(Um.getPoi()==U.getPoi()&&Um.getPurningweight()<U.getPurningweight()){
				    			eq=true;
							    U1=U;
							    }
				    		}
				    	if(eq)
				    	{
				    		tempUn1.add(U1);
				    		tempUn2.add(Um);
				    		}
				    	else
				    		tempUn2.add(Um);
				    	}
				    Un.removeAll(tempUn1);
 				    Un.addAll(tempUn2);
 				    tempUn1.clear();
 				    tempUn2.clear();
 				    Umax Pru=new Umax();
 				    for(Umax U:Un){
 				    	if(U.getPoi()==poimin){
 				    		Pru=U;
 				    		break;
 				    		}
 				    	}
 				    //笔记备注说明，为何先删除，再添加。如果Un中没有当前结点的启发的兴趣点，也需要加入到temporarylist
 				    if((dist<=Pru.getPurningweight())||(Pru.getPoi()==0&&Pru.getPurningweight()==0)){
 				    	if(temporarylist.contains(Vk)&&Vk.getDist()>dist){
					    temporarylist.remove(Vk);
					    Vk.setDist(dist);
					    Vk.setTt(Tt);
					    Vk.setAt(At);
					    Vk.setDist(dist);
					    Vk.setPoiid(poimin);
					    temporarylist.add(Vk);
					    }
 				    	else{
 				    		Vk.setDist(dist);
 				    		Vk.setTt(Tt);
 				    		Vk.setAt(At);
 				    		Vk.setDist(dist);
 				    		Vk.setPoiid(poimin);
 				    		temporarylist.add(Vk);
 				    		}
 				    	}
 				    if((Tt==dist)&&(!NN.contains(Vk))){
 				    	NN.add(Vk);
 				    	PriorityQueue<Umax> tempUn=new PriorityQueue<Umax>(2, dgeneComparator1);
 					    for(Umax U:Un){
 					    	if(U.getPoi()==Vk.getID())
 					    		tempUn.add(U);
 					    	}
 					    Un.removeAll(tempUn);
 					    }
 				    }
    		   }
    	   System.out.println(next++);
    	   }
       long endTime=System.currentTimeMillis(); //获取结束时间
       System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
       return NN;
       }
	}
