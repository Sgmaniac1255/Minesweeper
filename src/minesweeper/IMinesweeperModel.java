package minesweeper;

public interface IMinesweeperModel extends IFieldObservable{

	public void newGame(int x, int y, int numMines);
	
	public Field getMinefield();
	
	public void clickCell(int x, int y);

	public void flagCell(int x, int y);

	/*
	 * this might not be needed in the future. its only here for convenance
	 * for the text based display
	 */
	public StringBuilder printGrid();
	
}
