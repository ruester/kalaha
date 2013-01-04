public class Model implements ModelInterface {
	private ViewInterface view;
	int activePlayer;
	int winner;
	int stoneCount;
	int p1Kalaha;
	int p2Kalaha;
	int []p1Bin;
	int []p2Bin;

	private void changeActivePlayer() {
		assert(activePlayer == ModelInterface.PLAYER1
				|| activePlayer == ModelInterface.PLAYER2);

		if (activePlayer == ModelInterface.PLAYER1)
			activePlayer = ModelInterface.PLAYER2;
		else
			activePlayer = ModelInterface.PLAYER1;
	}

	private int getRandomPlayer() {
		if (Math.random() >= 0.5)
			return ModelInterface.PLAYER1;

		return ModelInterface.PLAYER2;
	}

	private void init() {
		p1Kalaha     = p2Kalaha = 0;
		activePlayer = getRandomPlayer();
		winner       = ModelInterface.ERROR;

		for (int i = 0; i < 6; i++)
			p1Bin[i] = p2Bin[i] = stoneCount;
	}

	public Model() {
		// default values and allocation
		stoneCount = 4;
		p1Bin      = new int[6];
		p2Bin      = new int[6];

		init();
	}

	public void setView(ViewInterface v) {
		view = v;
	}

	public int p1KalahaCount() {
		return p1Kalaha;
	}

	public int p2KalahaCount() {
		return p2Kalaha;
	}

	public int p1BinCount(int which) {
		assert(which >= 0 && which <= 5);
		return p1Bin[which];
	}

	public int p2BinCount(int which) {
		assert(which >= 0 && which <= 5);
		return p2Bin[which];
	}

	private void p2StealsAll() {
		for (int i = 0; i < 6; i++) {
			p2Kalaha += p2Bin[i];
			p2Bin[i] = 0;
		}
	}

	private void p1StealsAll() {
		for (int i = 0; i < 6; i++) {
			p1Kalaha += p1Bin[i];
			p1Bin[i] = 0;
		}
	}

	private void determineWinner() {
		if (p1Kalaha > p2Kalaha)
			winner = ModelInterface.PLAYER1;
		else
			winner = ModelInterface.PLAYER2;

		if (p1Kalaha == p2Kalaha)
			winner = ModelInterface.TIE;
	}

	public int p1Move(int which) {
		int hand;
		int currentBin;
		int opponentBin;

		assert(which >= 0 && which <= 5);

		if (winner != ModelInterface.ERROR
				|| activePlayer == ModelInterface.END_OF_GAME
				|| activePlayer == ModelInterface.PLAYER2) {
			view.showError("Not your turn, Player 1 (" + activePlayer + ")");
			view.refresh();
			return ModelInterface.ERROR;
		}

		hand              = p1Bin[which];
		currentBin        = which;
		p1Bin[currentBin] = 0;

		if (hand == 0) {
			view.showError("No stones selected");
			view.refresh();
			return ModelInterface.ERROR;
		}

		while (hand > 0) {
			// go around
			currentBin++;
			hand--;

			if (currentBin == 13)
				currentBin = 0;

			if (currentBin == 6) {
				p1Kalaha++;
				continue;
			}

			if (currentBin > 6) {
				p2Bin[currentBin - 7]++;
				continue;
			}

			p1Bin[currentBin]++;
		}

		if (currentBin == 6) {
			if (p1BinsEmpty()) {
				p2StealsAll();
				determineWinner();

				activePlayer = ModelInterface.END_OF_GAME;

				view.finish();
				return ModelInterface.END_OF_GAME;
			}

			view.refresh();
			return ModelInterface.REPEAT;
		}

		// steal
		if (currentBin < 6 && p1Bin[currentBin] == 1) {
			opponentBin = 5 - currentBin;
			p1Bin[currentBin] = 0;
			p1Kalaha++;
			p1Kalaha += p2Bin[opponentBin];
			p2Bin[opponentBin] = 0;

			if (p2BinsEmpty()) {
				p1StealsAll();
				determineWinner();

				activePlayer = ModelInterface.END_OF_GAME;

				view.finish();
				return ModelInterface.END_OF_GAME;
			}
		}

		changeActivePlayer();

		if (p1BinsEmpty()) {
			p2StealsAll();
			determineWinner();

			activePlayer = ModelInterface.END_OF_GAME;

			view.finish();
			return ModelInterface.END_OF_GAME;
		}

		view.refresh();
		return ModelInterface.SUCCESS;
	}

	public int p2Move(int which) {
		int hand;
		int currentBin;
		int opponentBin;

		assert(which >= 0 && which <= 5);

		if (winner != ModelInterface.ERROR
				|| activePlayer == ModelInterface.END_OF_GAME
				|| activePlayer == ModelInterface.PLAYER1) {
			view.showError("Not your turn, Player 2 (" + activePlayer + ")");
			view.refresh();
			return ModelInterface.ERROR;
		}

		hand              = p2Bin[which];
		currentBin        = which;
		p2Bin[currentBin] = 0;

		if (hand == 0) {
			view.showError("No stones selected");
			view.refresh();
			return ModelInterface.ERROR;
		}

		while (hand > 0) {
			// go around
			currentBin++;
			hand--;

			if (currentBin == 13)
				currentBin = 0;

			if (currentBin == 6) {
				p2Kalaha++;
				continue;
			}

			if (currentBin > 6) {
				p1Bin[currentBin - 7]++;
				continue;
			}

			p2Bin[currentBin]++;
		}

		if (currentBin == 6) {
			if (p2BinsEmpty()) {
				p1StealsAll();
				determineWinner();

				activePlayer = ModelInterface.END_OF_GAME;

				view.finish();
				return ModelInterface.END_OF_GAME;
			}

			view.refresh();
			return ModelInterface.REPEAT;
		}

		// steal
		if (currentBin < 6 && p2Bin[currentBin] == 1) {
			opponentBin = 5 - currentBin;
			p2Bin[currentBin] = 0;
			p2Kalaha++;
			p2Kalaha += p1Bin[opponentBin];
			p1Bin[opponentBin] = 0;

			if (p1BinsEmpty()) {
				p2StealsAll();
				determineWinner();

				activePlayer = ModelInterface.END_OF_GAME;

				view.finish();
				return ModelInterface.END_OF_GAME;
			}
		}

		changeActivePlayer();

		if (p2BinsEmpty()) {
			p1StealsAll();
			determineWinner();

			activePlayer = ModelInterface.END_OF_GAME;

			view.finish();
			return ModelInterface.END_OF_GAME;
		}

		view.refresh();
		return ModelInterface.SUCCESS;
	}

	public boolean p1BinsEmpty() {
		for (int i = 0; i < 6; i++)
			if (p1Bin[i] > 0)
				return false;

		return true;
	}

	public boolean p2BinsEmpty() {
		for (int i = 0; i < 6; i++)
			if (p2Bin[i] > 0)
				return false;

		return true;
	}

	public void reset() {
		init();
	}

	public void setStoneCount(int count) {
		assert(count > 0 && count <= 100);
		stoneCount = count;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public int getWinner() {
		if (activePlayer == ModelInterface.END_OF_GAME)
			return winner;

		view.showError("There is no winner yet");
		return ModelInterface.ERROR;
	}
}
