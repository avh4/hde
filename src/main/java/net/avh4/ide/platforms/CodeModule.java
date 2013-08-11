package net.avh4.ide.platforms;

import fj.data.List;

public class CodeModule {
    private final List<CodeClass> classes;

    public CodeModule() {
        this.classes = List.nil();
    }

    public CodeModule(List<CodeClass> classes) {
        this.classes = classes;
    }

    public CodeModule addClass(String packageName, String className, String source) {
        return new CodeModule(List.single(new CodeClass(packageName, className)));
    }

    public List<CodeClass> classes() {
        return classes;
    }

    public String name() {
        throw new RuntimeException("Not implemented");  // TODO
    }
}
