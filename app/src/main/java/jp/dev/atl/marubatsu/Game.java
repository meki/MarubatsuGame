package jp.dev.atl.marubatsu;

/**
 * Created by Sashida on 2014/11/27.
 */
public class Game implements Panel.StateChangeLister {
	private static Game ourInstance = new Game();
	private Panel panel = new Panel();
	private PlayerType currentPlayer = PlayerType.Player1;

	PlayerType getCurrentPlayer() { return currentPlayer; }

	public static Game getInstance () {
		return ourInstance;
	}

	private Game () {
	}

	public Panel getPanel () {
		return panel;
	}

	@Override
	public void onStateChanged (PanelState type, int x, int y) {

		currentPlayer = (currentPlayer == PlayerType.Player1) ? PlayerType.Player2 : PlayerType.Player1;

	}

	@Override
	public void onGameEnd (PlayerType type) {

	}
}
