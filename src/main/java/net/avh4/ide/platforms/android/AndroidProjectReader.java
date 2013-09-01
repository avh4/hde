package net.avh4.ide.platforms.android;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.Transaction;
import net.avh4.data.per2.TransactionContext;
import net.avh4.ide.ProjectReader;
import net.avh4.ide.platforms.CodeProject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class AndroidProjectReader implements ProjectReader{
    private Database db;

    public AndroidProjectReader(Database db) {
        this.db = db;
    }

    @Override
    public CodeProject read(final String path) {
        String project = db.transact(new Transaction<String>() {
            @Override
            public String run(TransactionContext t) {
                String project = t.create();

                String mainModule = t.create();
                t.set(mainModule, "name", "Tasks-main");
                t.add(project, "modules", mainModule);

                String testModule = t.create();
                t.set(testModule, "name", "Tasks-test");
                t.add(project, "modules", testModule);

                try {
                    Files.walkFileTree(Paths.get(path, "src", "main", "java"), new JavaFileVisitor(t, mainModule));
                    Files.walkFileTree(Paths.get(path, "src", "test", "java"), new JavaFileVisitor(t, testModule));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return project;
            }
        });
        return db.get(CodeProject.class, project);
    }

    private static class JavaFileVisitor implements FileVisitor<Path> {
        private final TransactionContext t;
        private final String module;

        public JavaFileVisitor(TransactionContext t, String module) {
            this.t = t;
            this.module = module;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String cl = t.create();
            t.add(module, "classes", cl);
            t.set(cl, "name", file.getFileName().toString().split("\\.", 2)[0]);
            t.set(cl, "source", IOUtils.toString(file.toUri()));
            t.set(cl, "packageName", file.getParent().toString().replaceFirst(".*/src/main/java/", "").replace('/', '.'));
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }
}
