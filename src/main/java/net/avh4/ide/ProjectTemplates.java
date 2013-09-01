package net.avh4.ide;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.Transaction;
import net.avh4.data.per2.TransactionContext;
import net.avh4.ide.platforms.CodeProject;

public class ProjectTemplates {

    private static final String HELLO_WORLD_ACTIVITY_JAVA = "package com.example.Hello_World;\n" +
            "\n" +
            "import android.app.Activity;\n" +
            "import android.os.Bundle;\n" +
            "import android.widget.LinearLayout;\n" +
            "import android.widget.TextView;\n" +
            "\n" +
            "public class HelloWorldActivity extends Activity {\n" +
            "    @Override\n" +
            "    protected void onCreate(Bundle savedInstanceState) {\n" +
            "        super.onCreate(savedInstanceState);\n" +
            "        LinearLayout content = new LinearLayout(this);\n" +
            "        TextView text =new TextView(this);\n" +
            "        text.setText(\"Hello World\");\n" +
            "        content.addView(text);\n" +
            "        setContentView(content);\n" +
            "    }\n" +
            "}\n";

    public CodeProject createAndroidHelloWorldProject(Database db) {
        String project = db.transact(new Transaction<String>() {
            @Override
            public String run(TransactionContext t) {
                final String project = t.create();
                t.add(project, "modules", createAndroidHelloWorldModule(t));
                return project;
            }
        });
        return db.get(CodeProject.class, project);
    }

    private String createAndroidHelloWorldModule(TransactionContext t) {
        String cl = t.create();
        t.set(cl, "packageName", "com.example.Hello_World");
        t.set(cl, "name", "HelloWorldActivity");
        t.set(cl, "source", HELLO_WORLD_ACTIVITY_JAVA);

        String module = t.create();
        t.add(module, "classes", cl);
        return module;
    }
}
