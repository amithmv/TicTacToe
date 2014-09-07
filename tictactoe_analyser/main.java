import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class main {

	
	private static final char X = 'X';
	private static final char O = 'O';
	private static final char EMPTY = '_';

public static void readfile (List<String> state,List<Integer> play)
{

	try {
		BufferedReader br = new BufferedReader(new FileReader("MyFile.txt"));
		String line;
		String state_temp;
		
			while (((line = br.readLine()) != null)) {
			   // process the line.
				String[] parts = line.split(" ");
				state_temp = parts[0];
				play.add(Integer.parseInt(parts[1]));
				state.add(parts[0]);
				
				//System.out.println(state.get(0) + " " + play.get(0));
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

}

public static int getNumMoves(String state)
{
int count=0;
	for(int i=0;i<9;i++)
	{
		if(state.charAt(i)!=EMPTY)
		{
			count++;
			
		}
				
	}
	return count;
	
}
	
	
public static void getState(List<String> state_temp,List<Integer> play_temp,List<String> state,List<String> stateNext,List<Integer> play)
{
	String currentState,oldState;
	int myPlay,hisPlay;
	
	String subState="";
	
	for(int i=0;i<state_temp.size()-1;i++)
	{
		oldState = state_temp.get(i);
		currentState = state_temp.get(i+1);
		myPlay = play_temp.get(i);
		
		
		int count =getNumMoves(currentState);
		int prevcount =getNumMoves(oldState);
		
		if(count>0)
		{
		
			if(count==1 && prevcount>0)
				oldState = "_________";
		
		for(int j=0;j<9;j++)
		{
			if(j == myPlay-1)
			{
				subState+=currentState.charAt(j);
				
			}
			/*else if(oldState.charAt(j) == currentState.charAt(j))
			{
				subState+=EMPTY;
				
			}*/
			else
			{
			
				subState+=oldState.charAt(j);
			}
			
		}
		
		state.add(subState);
		stateNext.add(currentState);
		
		for(int j=0;j<9;j++)
		{
			
			if(subState.charAt(j) != currentState.charAt(j))
			{
				play.add(j+1);
				
			}
		
			
		}
		
		//System.out.println(subState);
		subState="";
		
	}
		
	}
}




public static int findSymmetry(String state1,String state2)
{
	
	int[][] sym = new int[][]{
			{0,1,2,3,4,5,6,7,8},
			{2,5,8,1,4,7,0,3,6},
			{6,3,0,7,4,1,8,5,2},
			{8,7,6,5,4,3,2,1,0},
			{8,5,2,7,4,1,6,3,0},
			{2,1,0,5,4,3,8,7,6},
			{6,7,8,3,4,5,0,1,2},
			{0,3,6,1,4,7,2,5,8}
		};
	
	int count = 0;
	
	for(int j=0;j<8;j++)
	{
		count=0;
	for(int i=0;i<9;i++)
	{
		
		if(state1.charAt(i)==state2.charAt(sym[j][i]))
		{
			count++;
						
		}
		else break;
				
	}
	
	if(count == 9)
		return j;
	
	}
		
	return -1;
	
}


public static void createTable(List<String> stateInput,List<String> stateOutput,List<Integer> play,List<List<Integer>> playTable)
{
	
	List<Integer> playMoves = new ArrayList<>();
	for(int i=0;i<9;i++)
		playMoves.add(0);
	
	stateOutput.add(stateInput.get(0));
	
	
	for(int i=0;i<stateInput.size();i++)
	{
		int k=0;
		
		for(int m=0;m<stateOutput.size();m++)
		{
			
			 k = findSymmetry(stateInput.get(i),stateOutput.get(m));
			 System.out.println(m + " " + stateInput.get(i) + " " + stateOutput.get(m));
			 //k = findSymmetry("_________","_________");
			 if(k!=1)
			 {
				 break;
				 
			 }
						
		}
		
		//System.out.println(stateOutput.size() + " " + stateInput.get(i));
		
		if(k==-1)
		{
			stateOutput.add(stateInput.get(i));
			
		}
		
	}

	

	
	
}



	
	public static void main(String[] args) {
		
				
		List<String> state_temp = new ArrayList<>();
		List<Integer> play_temp = new ArrayList<>();
		
		List<String> state = new ArrayList<>();
		List<String> stateNext = new ArrayList<>();
		List<Integer> play = new ArrayList<>();
		
		List<String> stateOutput = new ArrayList<>();
		List<List<Integer>> playTable=new ArrayList<>();
		System.out.println("Begin");
		// TODO Auto-generated method stub
		
		readfile(state_temp,play_temp);
		getState(state_temp,play_temp,state,stateNext,play);
		
		createTable(state,stateOutput,play,playTable);
		
		for(int i=0;i<stateOutput.size();i++)
			System.out.println(stateOutput.get(i) + " "+ play.get(i));
			
		
		System.out.println(state.size() + " " + stateOutput.size() );
		
	//List<List<Integer>> temp = new ArrayList<>();
		//temp.add(arg0)
		
	}

}
