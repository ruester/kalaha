public interface ModelInterface {
	public final int TIE         = -4;
	public final int SUCCESS     = -3;
	public final int REPEAT      = -2;
	public final int ERROR       = -1;
	public final int PLAYER1     = 0;
	public final int PLAYER2     = 1;
	public final int END_OF_GAME = 2;

	// register view
	public void setView(ViewInterface v);

	// player 1: p1* functions
	// player 2: p2* functions

	// number of stones in the kalaha
	public int p1KalahaCount();
	public int p2KalahaCount();

	// number of stones in bin 'which'
	// 'which' => [0,5]
	public int p1BinCount(int which);
	public int p2BinCount(int which);

	// select and move stones from bin 'which'
	// 'which' => [0,5]
	// return values:
	// SUCCESS: move was successful; opponents turn 
	// REPEAT:  player is allowed to do another move
	// ERROR:   invalid move
	public int p1Move(int which);
	public int p2Move(int which);

	// reset game
	// set active player randomly
	// fill all bins with 'stoneCount'
	// set value of kalahas to 0
	public void reset();

	// set number of stones in each bin
	// minimum value: 1
	// maximum value: 100
	public void setStoneCount(int count);

	// which players turn is it?
	// returns PLAYER1, PLAYER2 or END_OF_GAME
	public int getActivePlayer();

	// who has won?
	// return PLAYER1, PLAYER2, TIE or ERROR (game not finished yet)
	public int getWinner();
}
