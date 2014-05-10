package se.bthstudent.sis.psangbok.tests;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.ArrayList;

import se.bthstudent.sis.psangbok.app.DatabaseHelper;
import se.bthstudent.sis.psangbok.app.Song;

/**
 * Created by sikevux on 5/10/14.
 */
public class DatabaseHelperTest extends AndroidTestCase {
	private DatabaseHelper mDatabaseHelper;

	protected void setUp() throws Exception {
		RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
		mDatabaseHelper = new DatabaseHelper(context);
	}

	public void testDatabaseHelper_getSongs() throws Exception {
		assertNotNull("Could not retrieve JSON from file", mDatabaseHelper.getSongs());
	}

	public void testDatabaseHelper_getSongsMatching() throws Exception {
		ArrayList<Song> mSongsMatching = mDatabaseHelper.getSongsMatching("fransyskor");
		assertNotNull("Could not get any songs when trying to find 'Feta fransyskor'", mSongsMatching);
		assertEquals("Feta fransyskor", mSongsMatching.get(0).getTitle());
		assertEquals("Matching sizes of ArrayList when searching", 1, mSongsMatching.size());
	}

	public void testDatabaseHelper_getSong() throws Exception {
		long testId = 6L;
		String testTitle = "Feta fransyskor";
		String testCredits = "K-sek @ LTH 1985";
		String testMelody = "Schuberts militärmarsch";
		String testText = "Feta fransyskor som svettas om fötterna,\nde trampar druvor\nsom sedan ska jäsas till vin.\nTranspirationen viktig é,\nty den ge'\nfin bouquet.\nVårtor och svampar följer me'\nmen vad gör väl de'?\n\nFör vi vill ha vin,\nvill ha vin,\nvill ha mera vin,\näven om följderna blir\natt vi må lida pin.\nFlickor: Flaskan och glaset gått i sin.\nPojkar: Hit med vin, mera vin!\nFlickor: Tror ni att vi är fyllesvin?\nPojkar: Ja! Fast större!";

		Song testSong = new Song(testTitle, testMelody, testCredits, testText);

		assertEquals("Song titles match", testSong.getTitle(), mDatabaseHelper.getSong(testId).getTitle());
		assertEquals("Song credits match", testSong.getCredits(), mDatabaseHelper.getSong(testId).getCredits());
		assertEquals("Song melody match", testSong.getMelody(), mDatabaseHelper.getSong(testId).getMelody());
		assertEquals("Song text match", testSong.getText(), mDatabaseHelper.getSong(testId).getText());
	}

	public void testDatabaseHelper_addToDatabaseFromUrl() throws Exception {
		String testUrl = "https://raw.githubusercontent.com/sikevux/psngbok/issue-7/app/testFiles/testSong.json";
		int mDatabaseSize = mDatabaseHelper.getSongs().size();
		mDatabaseHelper.addToDatabaseFromUrl(testUrl, mDatabaseHelper.getWritableDatabase());

		assertEquals("Database sizes matches", mDatabaseSize + 1, mDatabaseHelper.getSongs().size());
		assertEquals("testTitle", mDatabaseHelper.getSong(mDatabaseSize + 1L).getTitle());

	}

	public void testPreconditions() {
		assertNotNull("mDatabaseHelper is null", mDatabaseHelper);
	}
}

