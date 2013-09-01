package net.avh4.tasks.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import net.avh4.ide.example.net.avh4.tasks.android.R;
import net.avh4.tasks.Task;
import net.avh4.tasks.TimeUtil;

import static net.avh4.tasks.android.Logger.log;

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context) {
        super(context, R.layout.list_item_task, android.R.id.text1);
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = View.inflate(getContext(), R.layout.list_item_task, null);
        }

        final Task object = getItem(position);

        TextView description = (TextView) view.findViewById(android.R.id.text1);
        description.setText(object.getDescription());

        TextView estimate = (TextView) view.findViewById(R.id.estimate);
        estimate.setText(TimeUtil.toString(object.getEstimate()));

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.completed);
        checkBox.setChecked(object.isCompleted());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    log("ui_event", "complete_task", "checkbox");
                    completeTask(object);
                } else {
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    private void completeTask(final Task task) {
        task.setCompleted();
        remove(task);
        notifyDataSetChanged();
    }

    public void addTask(String text) {
        final Task task = new Task(text);
        add(task);
        notifyDataSetChanged();
    }
}
