package net.avh4.ide;

import com.google.common.collect.ImmutableSet;

public class ProjectPicker implements Window<ProjectPicker.Actions> {

    public interface Actions {
        void choose(String path);
    }

    @Override
    public ImmutableSet<Function> functions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WindowView<Actions> view() {
        throw new RuntimeException("Not implemented");  // TODO
    }
}
