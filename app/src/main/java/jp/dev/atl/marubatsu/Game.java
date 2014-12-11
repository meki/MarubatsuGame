package jp.dev.atl.marubatsu;

import java.util.ArrayList;

/**
 * Created by Sashida on 2014/11/27.
 */
public class Game {
	private static Game ourInstance = new Game();
	private Panel panel = new Panel();

	public static Game getInstance () {
		return ourInstance;
	}

	private Game () {
	}

	public Panel getPanel () {
		return panel;
	}

}
