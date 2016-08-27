package minesweeper;

import java.util.ArrayList;

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper implements IMinesweeperModel,IFieldObserver{

	private Field mineField;
	private IGameController controler;
	private ArrayList<IFieldObserver> fos;
	
	
	public Minesweeper() {
		fos = new ArrayList<>();
		
	}
	
	public void setControler(IGameController controler){
		this.controler = controler;
	}

	@Override
	public void newGame(int x, int y, int numMines) {
		mineField = instanciateNewField(x,y,numMines);
	}

	@Override
	public void clickCell(Location c) {

		if (mineField.clickCell(c)) {
			gameLose();
		}
		if (fieldIsClear()){
			gameWin();
		}
	}

	private Field instanciateNewField(int x, int y, int numMines) {
		Field rtn = new Field(x, y, numMines);
		rtn.regFieldObserver(this);
		return rtn;
	}

	private boolean fieldIsClear() {
		return mineField.isClear();
	}

	private void gameWin() {
		mineField.revealAll();
		controler.gameWin();
		
	}

	private void gameLose() {
		
		mineField.revealAll();
		controler.gameLose();
	}

	@Override
	public void flagCell(Location c) {

		mineField.flagCell(c);
		
	}

	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}

	@Override
	public Field getMinefield() {
		
		return mineField;
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {
		fos.add(fo);
	}

	@Override
	public void remFieldObserver(IFieldObserver fo) {
		fos.remove(fo);
	}

	@Override
	public void update() {
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}
}
