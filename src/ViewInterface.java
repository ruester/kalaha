public interface ViewInterface {
	// register controller
	public void setController(Controller c);

	// activate the view to be visible
	// or show some kind of introduction
	public void activate();

	// there was changes in the model
	// query model and show data
	public void refresh();

	// game has ended; show end result
	public void finish();

	// show an error message
	public void showError(String message);
}
