package HousieTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;


public class HousieTicket {

	 private static final Logger logger = Logger.getLogger(HousieTicket.class.getName());


	    private static int[][] generateHousieTicket(int rows, int columns, int maxColumn, int maxRow) {
	        if (rows <= 0 || columns <= 0 || maxColumn <= 0 || maxRow <= 0) {
	            throw new IllegalArgumentException("Input parameters must be greater than zero.");
	        }

	        int[][] ticket = new int[rows][columns];
	        int[] cellsInRow = new int[rows];
	        int[] cellsInColumn = new int[columns];

	        for (int j = 0; j < columns; j++) {
	            ArrayList<Integer> columnValues = generateColumnValues(j + 1, maxColumn);
	            Collections.shuffle(columnValues);

	            for (int i = 0; i < Math.min(maxColumn, rows); i++) {
	            	int row = getRandomEmptyRow(cellsInRow, rows,maxRow);

	            	 if (row != -1 && cellsInColumn[j] < maxColumn) {
	                     ticket[row][j] = columnValues.get(i);
	                     cellsInRow[row]++;
	                     cellsInColumn[j]++;
	                 }
	            }
	        }

	        return ticket;
	    }
	    private static int getRandomEmptyRow(int[] cellsInRow, int rows,int maxRow) {
	        List<Integer> emptyRows = new ArrayList<>();
	        Random random = new Random();
	        for (int i = 0; i < rows; i++) {
	            if (cellsInRow[i] < maxRow) {
	                emptyRows.add(i);
	            }
	        }
	        if (!emptyRows.isEmpty()) {
	            return emptyRows.get(random.nextInt(emptyRows.size()));
	        }
	        return -1;
	    }
	    private static ArrayList<Integer> generateColumnValues(int column, int maxColumn) {
	        if (maxColumn < 0) {
	            throw new IllegalArgumentException("maxCellsPerColumn must be greater than zero.");
	        }
	        Random random = new Random();
	        ArrayList<Integer> values = new ArrayList<>();

	        
	        int startValue = (column - 1) * 10 + 1;
	        int endValue = column * 10;

	        
	        for (int i = 0; i < maxColumn; i++) {
	            int randomValue;
	            do {
	                randomValue = random.nextInt(endValue - startValue + 1) + startValue;
	            } while (values.contains(randomValue));
	            values.add(randomValue);
	        }

	        return values;
	    }

	    private static void displayHousieTicket(int[][] ticket) {
	        for (int i = 0; i < ticket.length; i++) {
	            for (int j = 0; j < ticket[0].length; j++) {
	                System.out.printf("%2d ", ticket[i][j]);
	            }
	            System.out.println();
	        }
	    }
	    public static void main(String[] args) {
	        try {
	           
	            Scanner sc = new Scanner(System.in);
	            int rows = sc.nextInt();
	            int columns= sc.nextInt();
	            int maxColumn = 2;
	            int maxRow = 5;

	            int[][] ticket = generateHousieTicket(rows, columns, maxColumn, maxRow);

	            displayHousieTicket(ticket);

	        } catch(InputMismatchException e) {
	            logger.info("Invalid input parameters: " + e.getMessage());
	        }
	    }
}
