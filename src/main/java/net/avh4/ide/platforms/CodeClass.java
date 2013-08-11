package net.avh4.ide.platforms;

import fj.F;
import fj.Ord;

public class CodeClass {
    public static Ord<CodeClass> nameOrdering =
            Ord.stringOrd.comap(new F<CodeClass, String>() {
                @Override
                public String f(CodeClass codeClass) {
                    return codeClass.name;
                }
            });

    private final String name;
    private final String packageName;

    public CodeClass(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String getSource() {
        return "package com.example.Hello_World;\n" +
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
    }

    public String packageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return "CodeClass{" +
                packageName + "/" + name;
    }
}
