package net.avh4.ide.platforms;

import fj.data.List;

public class CodeProject {

    private final List<CodeModule> modules;

    public CodeProject() {
        modules = List.nil();
    }

    public CodeProject(List<CodeModule> modules) {
        this.modules = modules;
    }

    public CodeProject addModule(CodeModule module) {
        return new CodeProject(modules.cons(module));
    }

    public List<CodeModule> modules() {
        return modules;
    }
}
