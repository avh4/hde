package net.avh4.ide;

import net.avh4.ide.platforms.CodeModule;
import net.avh4.ide.platforms.CodeProject;

public class ProjectTemplates {
    public CodeProject createAndroidHelloWorldProject() {
        return new CodeProject()
                .addModule(createAndroidHelloWorldModule());
    }

    private CodeModule createAndroidHelloWorldModule() {
        return new CodeModule()
                .addClass("com.example.Hello_World", "HelloWorldActivity", "<<SOURCE>>");
    }
}
