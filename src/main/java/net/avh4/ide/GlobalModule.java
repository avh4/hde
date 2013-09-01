package net.avh4.ide;

import net.avh4.data.per2.DatabaseImpl;
import net.avh4.data.per2.MemoryDatumStore;
import net.avh4.ide.commands.ExecuteProjectCommand;
import net.avh4.ide.editing.EditorWindow;
import net.avh4.ide.editing.EditorWindowProvider;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.ide.platforms.android.AntAndroidPlatform;
import net.avh4.ide.platforms.util.FileSystem;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public abstract class GlobalModule {
    public static void configureContainer(MutablePicoContainer pico) {
        pico.addComponent(PicoContainer.class, pico);
        pico.addComponent(ApplicationProvider.class);
        pico.addComponent(ProjectWindowFactory.class);
        pico.addComponent(ProjectTemplates.class);
        pico.addComponent(EditorWindowProvider.class);
        pico.addComponent(EditorWindow.class);
        pico.addComponent(ExecuteProjectCommand.class);
        pico.addComponent(BuildResultsProvider.class);
        pico.addComponent(AntAndroidPlatform.class);
        pico.addComponent(FileSystem.class);
        pico.addComponent(AndroidSdk.class);
        pico.addComponent(DatabaseImpl.class);
        pico.addComponent(MemoryDatumStore.class);
        pico.addComponent(HdeModel.class);
    }
}
