package se.bthstudent.sis.psangbok.tests;

import se.bthstudent.sis.psangbok.app.DatabaseHelper;

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

	public void testPreconditions() {
		assertNotNull("mDatabaseHelper is null", mDatabaseHelper);
	}
}

