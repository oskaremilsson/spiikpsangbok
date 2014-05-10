package se.bthstudent.sis.psangbok.tests;

import se.bthstudent.sis.psangbok.app.DatabaseHelper;
import se.bthstudent.sis.psangbok.app.Song;

import android.content.Context;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
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

	public void testPreconditions() {
		assertNotNull("mDatabaseHelper is null", mDatabaseHelper);
	}
}

