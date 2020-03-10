import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {
	static PruningAndHeuristic pah=new PruningAndHeuristic();
	public static void main(String[] args){
		ArrayList<Node> node=new ArrayList<Node>();
		ArrayList<Node> resultnode=new ArrayList<Node>();
		node=pah.ReadNode();
		resultnode=pah.GetNode(node);
		System.out.print("≤‚ ‘");
	
		File file=new File("PruningandheuristicAllDay.txt");
		PrintWriter output = null;
		try {
			output = new PrintWriter(file);
			for(int j=0;j<resultnode.size();j++){
				output.print(resultnode.get(j).getNodeId()+"\t");
				System.out.print(resultnode.get(j).getNodeId()+"\t");
				for(int i=0;i<resultnode.get(j).getNodes().size();i++){
					output.print(resultnode.get(j).getNodes().get(i).getNodeId()+"-"+resultnode.get(j).getNodes().get(i).getMinpoi()+"-"+resultnode.get(j).getNodes().get(i).getMinweight()+"-"+resultnode.get(j).getNodes().get(i).getMaxpoi()+"-"+resultnode.get(j).getNodes().get(i).getMaxweight()+"\t");
					
					System.out.print(resultnode.get(j).getNodes().get(i).getNodeId()+"-"+resultnode.get(j).getNodes().get(i).getMinpoi()+"-"+resultnode.get(j).getNodes().get(i).getMinweight()+"-"+resultnode.get(j).getNodes().get(i).getMaxpoi()+"-"+resultnode.get(j).getNodes().get(i).getMaxweight()+"\t");				
				
				}
				output.print("\n");
				System.out.print("\n");
			}
				 
				 } catch (FileNotFoundException e) {
					e.printStackTrace();
					}
			output.close();
			
			
		}
}
