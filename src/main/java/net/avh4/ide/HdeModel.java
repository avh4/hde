package net.avh4.ide;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.Transaction;
import net.avh4.data.per2.TransactionContext;
import net.avh4.ide.platforms.CodeClass;
import net.avh4.ide.platforms.CodeProject;

public class HdeModel {
    private final Database db;
    private final ProjectTemplates projectTemplates;
    private final ProjectCommand executeProjectCommand;
    private final ProjectReader projectReader;
    private CodeProject project;

    public HdeModel(Database db, ProjectTemplates projectTemplates,
                    ProjectCommand executeProjectCommand, ProjectReader projectReader) {
        this.db = db;
        this.projectTemplates = projectTemplates;
        this.executeProjectCommand = executeProjectCommand;
        this.projectReader = projectReader;
    }

    public void createNewProject(String projectName) {
        project = projectTemplates.createAndroidHelloWorldProject(db);
    }

    public CodeProject project() {
        return project;
    }

    public void executeProject() {
        executeProjectCommand.execute(project);
    }

    public void replaceClassSource(String sourceFileName, final String newSource) {
        final CodeClass codeClass = project.modules()[0].classes()[0];
        db.transact(new Transaction<Void>() {
            @Override
            public Void run(TransactionContext t) {
                return t.set(codeClass._id(), "source", newSource);
            }
        });
    }

    public void openProject(String path) {
        project = projectReader.read(path);
    }
}
