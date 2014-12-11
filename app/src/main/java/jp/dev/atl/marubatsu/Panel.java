package jp.dev.atl.marubatsu;

import java.util.ArrayList;

enum	PanelState
{
	Blank,
	Check_Player1,
	Check_Player2,
}

enum PlayerType
{
	Player1,
	Player2,
}

/**
 * Created by Sashida on 2014/11/27.
 */
class Panel extends ArrayList<PanelState>
{
	static final int WIDTH = 3;
	static final int HEIGHT = 3;

	Panel() {
		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			add(PanelState.Blank);
		}
	}

	/** (x, y)位置のパネルの状態を取得する. */
	PanelState get(int x, int y)
	{
		return get(x + y * WIDTH);
	}

	/** ユーザ入力. */
	public boolean changeState(PlayerType type, int x, int y)
	{
		PanelState s = PanelState.Blank;
		switch (type)
		{
			case Player1:
				s = PanelState.Check_Player1;
				break;

			case Player2:
				s = PanelState.Check_Player2;
				break;
		}

		int index = x + y * WIDTH;
		if (get(index) == PanelState.Blank)
		{
			set(index, s);
			notifyStateChanged(s, x, y);
			return true;
		}
		else
		{
			// already checked.
			return false;
		}
	}

	public interface StateChangeLister
	{
		void onStateChanged (PanelState type, int x, int y);
	}

	ArrayList<StateChangeLister> listeners = new ArrayList<StateChangeLister>();

	void addListener(StateChangeLister lis) { listeners.add(lis); }

	void notifyStateChanged(PanelState type, int x, int y)
	{
		for (StateChangeLister lis : listeners)
		{
			lis.onStateChanged(type, x, y);
		}
	}
}
