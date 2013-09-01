package net.avh4.tasks.android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import net.avh4.ide.example.net.avh4.tasks.android.R;

/**
 * An activity representing a list of Tasks.
 */
public class TaskListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }
}
