package P3;

import java.util.ArrayList;
import java.util.List;


public class FriendshipGraph {
	public FriendshipGraph() {
		
	}
	final int arrSize = 100;
	private int [][] relationShip = new int [arrSize][arrSize];
	private List<Person> nameTable= new ArrayList<Person>();
	
	public boolean addVertex(Person guy) {
		//检测是否已经存在
		for(int i = 0; i < nameTable.size() ; i ++)
			if(guy.getPersonName() == nameTable.get(i).getPersonName()) 
				return false;
		nameTable.add(guy);
		return true;
	}
	
	
	public void addEdge(Person x, Person y) {
		int x_index = nameTable.indexOf(x);
		int y_index = nameTable.indexOf(y);
		relationShip[x_index][y_index]=1;
	}
	
	public int getDistance(Person x, Person y) {
		if(x.getPersonName() == y.getPersonName())
			return 0;
		int x_index = nameTable.indexOf(x);
		int y_index = nameTable.indexOf(y);
		int[][] temp = new int [nameTable.size()][nameTable.size()];
		//初始化数组
		for(int i = 0; i < nameTable.size(); i++) {
			for(int j = 0; j < nameTable.size(); j++) {
				if(relationShip[i][j]==0)
					temp[i][j] = Integer.MAX_VALUE;
				else
					temp[i][j] = 1;
			}
		}
		//Floyd
		for(int k = 0; k < nameTable.size(); k++) 
			for(int i = 0; i < nameTable.size(); i++) 
				for(int j = 0; j < nameTable.size(); j++) 
				{
					if(temp[i][k] < Integer.MAX_VALUE && temp[k][j] < Integer.MAX_VALUE)
						if(temp[i][j] == Integer.MAX_VALUE)
							temp[i][j] = temp[i][k] + temp[k][j];
						else if(temp[i][j] > temp[i][k] + temp[k][j])
							temp[i][j] = temp[i][k] + temp[k][j];
				}
					
						
		if(temp[x_index][y_index]==Integer.MAX_VALUE)
			return -1;
		return temp[x_index][y_index];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}


