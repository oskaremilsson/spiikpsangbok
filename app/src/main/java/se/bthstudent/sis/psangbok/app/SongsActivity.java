package se.bthstudent.sis.psangbok.app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SongsFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.songs, menu);
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
     * A Songs fragment containing a simple view.
     */
    public static class SongsFragment extends Fragment {
		 private static final String ARG_SECTION_NUMBER = "section_number";
		ListView listView;
        DatabaseHelper dbHelper;
		private ArrayList<Song> songs;
		/**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SongsFragment newInstance(int sectionNumber) {
            SongsFragment fragment = new SongsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
	    public SongsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_songs, container, false);
			dbHelper = new DatabaseHelper(getActivity());
			songs = dbHelper.getSongs();
			listView = (ListView) rootView.findViewById(R.id.listView);
			listView.setAdapter(new SongArrayAdapter(getActivity(), songs));
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
					FragmentManager fragmentManager = getFragmentManager();
					Fragment fragment = new ViewSongActivity.ViewSongFragment().newInstance(songs.get(position).getId());
					 fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
				}
			});
			return rootView;
        }
	}
}
