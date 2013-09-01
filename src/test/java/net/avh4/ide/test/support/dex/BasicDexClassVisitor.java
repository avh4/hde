package net.avh4.ide.test.support.dex;

import com.googlecode.dex2jar.Field;
import com.googlecode.dex2jar.Method;
import com.googlecode.dex2jar.visitors.*;

public class BasicDexClassVisitor implements DexClassVisitor {

    private DexMethodVisitor methodVisitor;

    public BasicDexClassVisitor(DexCodeVisitor codeVisitor) {
        this(new BasicDexMethodVisitor(codeVisitor));
    }

    public BasicDexClassVisitor(BasicDexMethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }

    @Override
    public void visitSource(String s) {
    }

    @Override
    public DexFieldVisitor visitField(int i, Field field, Object o) {
        return null;
    }

    @Override
    public DexMethodVisitor visitMethod(int i, Method method) {
        return methodVisitor;
    }

    @Override
    public void visitEnd() {
    }

    @Override
    public DexAnnotationVisitor visitAnnotation(String s, boolean b) {
        return null;
    }
}
