package se.bthstudent.sis.psangbok.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ViewSongActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ViewSongFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A ViewSong fragment containing a simple view.
     */
    public static class ViewSongFragment extends Fragment {
		public static ViewSongFragment newInstance(long songId) {
            ViewSongFragment fragment = new ViewSongFragment();
            Bundle args = new Bundle();
            args.putLong("song", songId);
            fragment.setArguments(args);
            return fragment;
        }
        public ViewSongFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_view_song, container, false);
			TextView title = (TextView)rootView.findViewById(R.id.song_title);
			TextView info = (TextView)rootView.findViewById(R.id.song_info);
			TextView text = (TextView)rootView.findViewById(R.id.song_text);

		long songId = getArguments().getLong("song");
		DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
		Song s = dbHelper.getSong(songId);
		String stringTitle;
			stringTitle = s.getTitle();
			title.setText(stringTitle);

		StringBuilder sb = new StringBuilder();
		if (!s.getCredits().trim().equals(""))
			sb.append(s.getCredits());
		if (!s.getCredits().trim().equals("") &&
				!s.getMelody().trim().equals(""))
			sb.append(", ");
		if (!s.getMelody().trim().equals(""))
			sb.append(s.getMelody());
		info.setText(sb.toString());

		text.setText(s.getText());
			rootView.postInvalidate();
            return rootView;
        }
    }
}
