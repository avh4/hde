package net.avh4.ide;

import com.google.common.collect.ImmutableSet;

public class ProjectPicker implements Window<ProjectPicker.Actions> {

    public interface Actions {
        void choose(String path);
    }

    @Override
    public ImmutableSet<Function> functions() {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public WindowView<Actions> view() {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public Actions actions() {
        throw new RuntimeException("Not implemented");  // TODO
    }
}
