import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View implements ViewInterface {
	private ModelInterface model;
	private Controller controller;

	public View(ModelInterface m) {
		model = m;
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void activate() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int l;
		int sc;

		System.out.println("Kalaha\n======\n");
		System.out.print("Stones per bin: ");
		System.out.flush();

		sc = 4;

		try {
			while ((s = in.readLine()) != null) {
				l = s.length();

				if (l == 0) {
					showError("empty string");
					System.out.print("Stones per bin: ");
					System.out.flush();
					continue;
				}

				sc = Integer.valueOf(s);

				if (sc < 0 || sc > 100) {
					showError("invalid number");
					System.out.print("Stones per bin: ");
					System.out.flush();
					continue;
				}

				break;
			}

			if (s == null)
				System.exit(1);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		controller.newGame(sc);
	}

	public void print() {
		System.out.print("\n    ");

		if (model.getActivePlayer() == ModelInterface.PLAYER1) {
			for (int i = 5; i >= 0; i--)
				System.out.printf("% 4d ", model.p2BinCount(i));
			System.out.println();

			System.out.printf("% 4d                             % 4d\n    ", model.p2KalahaCount(), model.p1KalahaCount());

			for (int i = 0; i < 6; i++)
				System.out.printf("% 4d ", model.p1BinCount(i));
		} else {
			for (int i = 5; i >= 0; i--)
				System.out.printf("% 4d ", model.p1BinCount(i));
			System.out.println();

			System.out.printf("% 4d                             % 4d\n    ", model.p1KalahaCount(), model.p2KalahaCount());

			for (int i = 0; i < 6; i++)
				System.out.printf("% 4d ", model.p2BinCount(i));
		}

		System.out.println("");
	}

	public void refresh() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String player;
		String s;
		int bin;
		int l;

		bin = -1;

		if (model.getActivePlayer() == ModelInterface.PLAYER1)
			player = "Player 1";
		else
			player = "Player 2";

		print();

		System.out.print("\nMove of " + player + ": ");

		try {
			while ((s = in.readLine()) != null) {
				l = s.length();

				if (l == 0) {
					showError("empty string");
					System.out.print("Move of " + player + ": ");
					System.out.flush();
					continue;
				}

				bin = Integer.valueOf(s);

				if (bin < 1 || bin > 6) {
					showError("invalid number");
					System.out.print("Move of " + player + ": ");
					System.out.flush();
					continue;
				}

				break;
			}

			if (s == null)
				System.exit(1);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		if (bin == -1) {
			System.err.println("BUG");
			System.exit(1);
		}

		if (model.getActivePlayer() == ModelInterface.PLAYER1)
			controller.p1Move(bin - 1);
		else
			controller.p2Move(bin - 1);
	}

	public void finish() {
		String player;

		print();

		if (model.getWinner() == ModelInterface.PLAYER1)
			player = "Player 1";
		else
			player = "Player 2";

		System.out.println("The Winner is " + player);
	}

	public void showError(String message) {
		System.err.println(message);
		System.err.flush();
	}
}
