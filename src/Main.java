
public class Main {
	public static void main(String[] args) {
		Model m;
		View v;
		Controller c;

		m = new Model();
		v = new View(m);
		c = new Controller(m, v);

		m.setView(v);
		v.setController(c);

		c.showView();
	}
}
