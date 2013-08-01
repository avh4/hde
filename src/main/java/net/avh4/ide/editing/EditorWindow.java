package net.avh4.ide.editing;

import com.google.common.collect.ImmutableSet;
import net.avh4.ide.Function;
import net.avh4.ide.Window;

public class EditorWindow implements Window {
    @Override
    public ImmutableSet<Function> functions() {
        return ImmutableSet.of(Function.EDITING);
    }

    public String getContents() {
        return "public class HelloWorldActivity extends Activity {\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.layout.hello_world_activity);\n" +
                "    }\n" +
                "}\n";
    }
}
