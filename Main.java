public class Main {

	public static void main(String[] args) {
		//GUI gui = new GUI();
		GUI gui = new GUI();
		Model model = new Model();
		Controller control = new Controller(model, gui);
		gui.setVis();
	}
}
