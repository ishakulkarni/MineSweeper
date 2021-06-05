// Name:Isha Kulkarni
// USC NetID: ikulkarn
// CS 455 PA3
// Spring 2021

import java.util.Arrays;
import java.util.Random;
/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
   private boolean[][] mf;
   private int numMines = 0;
   private int rows = 0;
   private int cols = 0;
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      
      rows= mineData.length;
      cols= mineData[0].length;
      mf = new boolean[rows][cols];
      
      int count = 0;
      for( int i=0; i< rows; i++){
         for (int j=0; j<cols; j++){
            
            if( mineData[i][j] == true){
               mf[i][j] = true;
               count++;
            }
            else{
               mf[i][j] = false;
            }
         }
      }
      numMines = count;
       
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), 
      
      that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      
      mf = new boolean[numRows][numCols]; //all false by default i.e. no mines anywhere
      
      this.numMines = numMines;
      rows = numRows;
      cols = numCols;
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      resetEmpty();
      Random generator = new Random();
      int count = 0;
      
      while(count < numMines){
         int r = generator.nextInt(rows);
         int c = generator.nextInt(cols);
         if(( r==row && c == col) || (mf[r][c] == true)){
            count=count; //do nothing, mine exists at that location
         }
         else{
            mf[r][c] = true;   //introduce new mine
            count++;   
         }
      }

   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in 
         at the beginning of a game.
    */
   public void resetEmpty() {
      mf = new boolean[rows][cols];
      
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col);
      
      int count=0;
      int [] r=new int[]{row-1, row-1, row-1, row, row, row+1, row+1, row+1};
      int [] c=new int[]{col-1, col, col+1, col-1, col+1, col-1, col, col+1};
      
      for (int n=0; n<8; n++){

         if(( r[n] >=0   && c[n] >= 0) && (r[n] <rows && c[n] < cols)){  //check- index in bounds of minefield dimensions

            if(mf[r[n]][c[n]] == true)   //minefield found
               count++;
         }
            
      }
      return count;       
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      if(row>=0 && row < rows  && col>=0 && col<cols){
         return true;
      }
      else{
         return false;    
      }
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return rows;       
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return cols;      
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      assert inRange(row,col);
      
      if(mf[row][col] == true){
         return true;
      }
      else{
         return false;  
      }
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines;      
   }
    
   
}

