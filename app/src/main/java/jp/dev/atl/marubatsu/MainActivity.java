package jp.dev.atl.marubatsu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener,
		Panel.StateChangeLister
{

	Button bt00;
	Button bt10;
	Button bt20;
	Button bt01;
	Button bt11;
	Button bt21;
	Button bt02;
	Button bt12;
	Button bt22;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt00 = (Button)findViewById(R.id.button00);
		bt10 = (Button)findViewById(R.id.button10);
		bt20 = (Button)findViewById(R.id.button20);
		bt01 = (Button)findViewById(R.id.button01);
		bt11 = (Button)findViewById(R.id.button11);
		bt21 = (Button)findViewById(R.id.button21);
		bt02 = (Button)findViewById(R.id.button02);
		bt12 = (Button)findViewById(R.id.button12);
		bt22 = (Button)findViewById(R.id.button22);

		bt00.setOnClickListener(this);
		bt10.setOnClickListener(this);
		bt20.setOnClickListener(this);
		bt01.setOnClickListener(this);
		bt11.setOnClickListener(this);
		bt21.setOnClickListener(this);
		bt02.setOnClickListener(this);
		bt12.setOnClickListener(this);
		bt22.setOnClickListener(this);

		Panel p = Game.getInstance().getPanel();
		p.addListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		Point p = new Point();

		switch (v.getId())
		{
			case R.id.button00:
				p.set(0, 0);
				break;

			case R.id.button10:
				p.set(1, 0);
				break;

			case R.id.button20:
				p.set(2, 0);
				break;

			case R.id.button01:
				p.set(0, 1);
				break;

			case R.id.button11:
				p.set(1, 1);
				break;

			case R.id.button21:
				p.set(2, 1);
				break;

			case R.id.button02:
				p.set(0, 2);
				break;

			case R.id.button12:
				p.set(1, 2);
				break;

			case R.id.button22:
				p.set(2, 2);
				break;
		}  // switch

		Panel panel = Game.getInstance().getPanel();
		boolean isChanged = panel.changeState(Game.getInstance().getCurrentPlayer(), p.x, p.y);

		if (!isChanged)
		{
			Toast.makeText(this, "ボタン (" + p.x + ", " + p.y + ") は押せないよ", Toast.LENGTH_SHORT).show();
		}

        panel.checkGameEnd();

    }



	@Override
	public void onStateChanged (PanelState type, int x, int y) {

		Button button = getButton(x, y);

		switch (type)
		{
			case Blank:
				button.setText("");
				break;

			case Check_Player1:
				button.setText("○");
				button.setTextColor(Color.BLUE);
				break;

			case Check_Player2:
				button.setText("×");
				button.setTextColor(Color.RED);
		}
	}

    @Override
    public void onGameEnd(PlayerType type) {
        Directage dir = new Directage();
        dir.showWinAnimation(this,type);
    }

    Button getButton(int x, int y)
	{
		switch (x)
		{
			case 0:
				switch (y)
				{
					case 0:
						return bt00;
					case 1:
						return bt01;
					case 2:
						return bt02;
				}
				break;

			case 1:
				switch (y)
				{
					case 0:
						return bt10;
					case 1:
						return bt11;
					case 2:
						return bt12;
				}
				break;

			case 2:
				switch (y)
				{
					case 0:
						return bt20;
					case 1:
						return bt21;
					case 2:
						return bt22;
				}
				break;
		}

		// ここには来ないはず！
		return null;
	}
}
