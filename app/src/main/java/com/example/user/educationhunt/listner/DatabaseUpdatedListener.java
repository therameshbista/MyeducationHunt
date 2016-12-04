package com.example.user.educationhunt.listner;

import android.view.MenuItem;

/**
 * Created by Xerric on 11/30/2016.
 */

public interface DatabaseUpdatedListener {
    void setDatabaseSuccess(String schoolName, MenuItem item);
    void setDatabaseError(String failureMessage);
}
