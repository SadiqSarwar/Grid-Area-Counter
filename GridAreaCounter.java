import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridAreaCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		
		int option = -1;
		while(option != 0){
			char [] [] list = new char [10][10];
			char [] [] markedList = new char [10][10];

			System.out.println("[Grid Area Counter]");
			System.out.println("    1 - Grid #1");
			System.out.println("    2 - Grid #2");
			System.out.println("    3 - Grid #3");
			System.out.println("    4 - Grid #4");
			System.out.println("    0 - Exit");
			System.out.println("----------------------------");
			System.out.print("Enter Option #: ");
			option = CheckInput.checkInt();
			list = readFromFile(list, option);
			if(option != 0){
				markedList = displayArray(list, markedList);
				System.out.println();
			} // if(option != 0)
			traverseGrid(list, markedList);
		} // while(option != 0)
		

	} // End of main method
	
	/**
	 * Traverses through the given lists
	 * @param list imported 2D list containing grid
	 * @param markedList 2D list containing marked o's
	 */
	public static void traverseGrid(char [][] list, char [][] markedList){
		int areaCount = 1;
		
		for(int i = 0; i < list.length; i++){
			for( int j = 0; j < list[i].length; j++){
				markedList = recursiveTracker(list, i, j, markedList);
				int hash = 0;
				for(int r = 0; r < markedList.length; r++){
					for(int c = 0; c < markedList[i].length; c++){
						if(markedList[r][c]=='#'){
							hash++ ;
						} // if(markedList[r][c]=='#')
					} // for(int c = 0; c < markedList[i].length; c++)
				} // for(int r = 0; r < markedList.length; r++)
				markedList = counter(markedList, areaCount);
				int newHash = 0;
				for(int r = 0; r < markedList.length; r++){
					for(int c = 0; c < markedList[i].length; c++){
						if(markedList[r][c]=='#'){
							newHash++ ;
						} // if(markedList[r][c]=='#')
					} // for(int c = 0; c < markedList[i].length; c++)
				} // for(int r = 0; r < markedList.length; r++)
				if(hash != newHash){
					areaCount++;
				}
			} // for( int j = 0; j < list[0].length; j++)
		} // for(int i = 0; i < list.length; i++)
		System.out.println("----------------------------");
	} // End of traverseGrid method
	
	/**
	 * Traverses within the grid, and marks for o's
	 * @param list imported 2D list containing grid
	 * @param i row iteration
	 * @param j column iteration
	 * @param markedList 2D list containing marked o's
	 * @return markedList of x's
	 */
	public static char [] [] recursiveTracker(char [] [] list, int i, int j, char [] [] markedList){
		if(i < list.length && j < list[i].length){
			if(markedList[i][j] == 'o'){
				markedList[i][j] = 'x';
				
				if(markedList[i-1][j]=='o'){
					recursiveTracker(list, i-1, j, markedList);
				} // if(markedList[i-1][j]=='o')
				if(markedList[i][j+1]=='o'){
					recursiveTracker(list, i, j+1, markedList);
				} // if(markedList[i][j+1]=='o')
				if(markedList[i][j-1]=='o'){
					recursiveTracker(list, i, j-1, markedList);
				} // if(markedList[i][j-1]=='o')
				if(markedList[i+1][j]=='o'){
					recursiveTracker(list, i+1, j, markedList);
				} // if(markedList[i+1][j]=='o')
			}else{
				recursiveTracker(list, i, j+1, markedList);
			} // else
		
		} // if(i < list.length && j < list[i].length)
		return markedList;
	} // End of recursiveTracker method
	
	/**
	 * Counts the number of marked x's within the marked list
	 * @param markedList 2D list containing marked o's
	 * @param areaCount number of area iteration
	 * @return number of marks within the markedList
	 */
	public static char [] [] counter(char [] [] markedList, int areaCount){
		int count = 0;
		for(int i = 0; i < markedList.length; i++){
			for( int j = 0; j < markedList[i].length; j++){
				if(markedList[i][j] == 'x'){
					count++;
					markedList[i][j] = '#';
				} // if(markedList[i][j] == 'x')
			} // for( int j = 0; j < list[0].length; j++)
		} // for(int i = 0; i < list.length; i++)
		if(count != 0){
			System.out.println("Area " + areaCount + ": " + count);
		} // if(count != 0)
		return(markedList);
	} // End of counter method
	
	/**
	 * Reads in the grid file
	 * @param list empty 2D list
	 * @param option choice of grid
	 * @return list imported 2D list containing grid
	 */
	public static char[][] readFromFile(char [][] list, int option){
		if(option!=0){
			boolean TestInput = true;
			while(TestInput){
				try{
					// Try opening the file 'values.txt'
					Scanner Read = new Scanner(new File("grid"+option+".txt"));
					for(int i = 0; i < list.length; i++){
						for( int j = 0; j < list.length; j++){
							list[i][j] = '#';
						} // for( int j = 0; j < list[0].length; j++)
					} // for(int i = 0; i < list.length; i++)
						for(int i = 1; i <= 8; i++){
							String Let = Read.next();
							for(int j = 1; j <= 8; j++){
								list[i][j] = Let.charAt(j-1);
							} // for( int j = 0; j < list[0].length; j++)
						} // for(int i = 0; i < list.length; i++)
					TestInput = false;
				} // TestInput - try
				catch(FileNotFoundException e){
					System.out.println("File was not Found");
					
				} // TestInput - catch FileNotFoundException
			} //while(TestInput)
			return list;
		} // if(option!=0)
		
		if(option==0){
			System.out.println("[Program Will Now Exit]");
		} // if(option==0)
		
		return list;
	} // // End of readFromFile method
	
	/**
	 * Display the imported 2D list grid
	 * @param list imported 2D list containing grid
	 * @param markedList
	 * @return markedList 2D list containing marked o's
	 */
	public static char[][] displayArray(char [][] list, char [] [] markedList){
		for(int i = 0; i < list.length; i++){
			System.out.println();
			for( int j = 0; j < list[i].length; j++){
				System.out.print(list[i][j]);
				markedList[i][j] = list[i][j];
			} // for( int j = 0; j < list[0].length; j++)
		} // for(int i = 0; i < list.length; i++)
		System.out.println();
		return (markedList);
	} // End of displayArray method

} // End of GridAreaCounter2 Class
