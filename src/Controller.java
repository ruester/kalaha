public class Controller {
	private ModelInterface model;
	private ViewInterface view;

	public Controller(ModelInterface m, ViewInterface v) {
		model = m;
		view  = v;
	}

	public void showView() {
		view.activate();
	}

	public void newGame(int stoneCount) {
		model.setStoneCount(stoneCount);
		model.reset();
		view.refresh();
	}

	public void p1Move(int bin) {
		assert(bin >= 0 && bin <= 5);
		model.p1Move(bin);
	}

	public void p2Move(int bin) {
		assert(bin >= 0 && bin <= 5);
		model.p2Move(bin);
	}
}
