package se.spiik.android.psangbok;

import android.content.Context;

/**
 * Created by Oskar on 2015-11-07.
 */
public class DatabaseDestroyer2000 {
    public DatabaseDestroyer2000(Context context, String name) {
        context.deleteDatabase(name);
    }
}
