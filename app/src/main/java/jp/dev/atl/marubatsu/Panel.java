package jp.dev.atl.marubatsu;

import android.widget.Toast;

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
    DEFAULT,
}

/**
 * Created by Sashida on 2014/11/27.
 */
class Panel extends ArrayList<PanelState> {
    static final int WIDTH = 3;
    static final int HEIGHT = 3;

    Panel() {
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            add(PanelState.Blank);
        }
    }

    /**
     * (x, y)位置のパネルの状態を取得する.
     */
    PanelState get(int x, int y) {
        return get(x + y * WIDTH);
    }

    /**
     * ユーザ入力.
     */
    public boolean changeState(PlayerType type, int x, int y) {
        PanelState s = PanelState.Blank;
        switch (type) {
            case Player1:
                s = PanelState.Check_Player1;
                break;

            case Player2:
                s = PanelState.Check_Player2;
                break;
        }

        int index = x + y * WIDTH;
        if (get(index) == PanelState.Blank) {
            set(index, s);
            notifyStateChanged(s, x, y);
            return true;
        } else {
            // already checked.
            return false;
        }
    }

    public interface StateChangeLister {
        void onStateChanged(PanelState type, int x, int y);

        void onGameEnd(PlayerType type);
    }

    ArrayList<StateChangeLister> listeners = new ArrayList<StateChangeLister>();

    void addListener(StateChangeLister lis) {
        listeners.add(lis);
    }

    void notifyStateChanged(PanelState type, int x, int y) {
        for (StateChangeLister lis : listeners) {
            lis.onStateChanged(type, x, y);
        }
    }


    void notifyGameEnd(PlayerType type) {
        for (StateChangeLister lis : listeners) {
            lis.onGameEnd(type);
        }
    }

    void checkGameEnd() {
        Panel panel = Game.getInstance().getPanel();
        if (isPlayerWon(PlayerType.Player1))
        {
            notifyGameEnd(PlayerType.Player1);
        }
        else if (isPlayerWon(PlayerType.Player2))
        {
            notifyGameEnd(PlayerType.Player2);
        }


    }

    private boolean isPlayerWon(PlayerType player) {
        Panel panel = Game.getInstance().getPanel();

        if (panel.get(0, 0)==panel.get(0, 1)&&panel.get(0, 0)==panel.get(0, 2)) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(1, 0) == PanelState.Check_Player1 && panel.get(1, 1) == PanelState.Check_Player1 && panel.get(1, 2) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(2, 0) == PanelState.Check_Player1 && panel.get(2, 1) == PanelState.Check_Player1 && panel.get(2, 2) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(0, 0) == PanelState.Check_Player1 && panel.get(1, 0) == PanelState.Check_Player1 && panel.get(2, 0) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(0, 1) == PanelState.Check_Player1 && panel.get(1, 1) == PanelState.Check_Player1 && panel.get(2, 1) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(0, 2) == PanelState.Check_Player1 && panel.get(1, 2) == PanelState.Check_Player1 && panel.get(2, 2) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(0, 0) == PanelState.Check_Player1 && panel.get(1, 1) == PanelState.Check_Player1 && panel.get(2, 2) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }
        if (panel.get(0, 2) == PanelState.Check_Player1 && panel.get(1, 1) == PanelState.Check_Player1 && panel.get(2, 0) == PanelState.Check_Player1) {
            if (panel.get(0, 0)==PanelState.Check_Player1&&player==PlayerType.Player1) {
                return true;
            }
            if (panel.get(0, 0)==PanelState.Check_Player2&&player==PlayerType.Player2) {
                return true;
            }
        }

        return false;

    }

}
