package net.avh4.tasks.android;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.avh4.ide.example.net.avh4.tasks.android.R;

/**
 * A list fragment representing a list of Tasks.
 */
public class TaskListFragment extends ListFragment {
    private TaskListAdapter adapter;
    private EditText quickAddEntry;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, null);

        adapter = new TaskListAdapter(getActivity());

        //noinspection ConstantConditions
        quickAddEntry = (EditText) view.findViewById(R.id.quick_add_entry);

        final Button quickAddButton = (Button) view.findViewById(R.id.quick_add_button);
        quickAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask("button");
            }
        });

        quickAddEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addTask("keyboard");
                return true;
            }
        });

        setListAdapter(adapter);
        return view;
    }

    private void addTask(String source) {
        Logger.log("ui_event", "quick_add_task", source);
        //noinspection ConstantConditions
        String text = quickAddEntry.getText().toString();
        adapter.addTask(text);
        quickAddEntry.setText("");
    }
}
