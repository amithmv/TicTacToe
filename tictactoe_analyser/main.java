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
	
	public static void main(String[] args) {
		List<String> state_temp = new ArrayList<>();
		List<Integer> play_temp = new ArrayList<>();
		
		List<String> state = new ArrayList<>();
		List<String> stateNext = new ArrayList<>();
		List<Integer> play = new ArrayList<>();
		System.out.println("Begin");
		// TODO Auto-generated method stub
		
		readfile(state_temp,play_temp);
		getState(state_temp,play_temp,state,stateNext,play);
		for(int i=0;i<state.size();i++)
		System.out.println(state.get(i) +" " + stateNext.get(i) + " "+ play.get(i));
		
	}

}
