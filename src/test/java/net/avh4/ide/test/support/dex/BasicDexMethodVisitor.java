package net.avh4.ide.test.support.dex;

import com.googlecode.dex2jar.visitors.DexAnnotationAble;
import com.googlecode.dex2jar.visitors.DexAnnotationVisitor;
import com.googlecode.dex2jar.visitors.DexCodeVisitor;
import com.googlecode.dex2jar.visitors.DexMethodVisitor;

public class BasicDexMethodVisitor implements DexMethodVisitor {

    private DexCodeVisitor codeVisitor;

    public BasicDexMethodVisitor(DexCodeVisitor codeVisitor) {
        this.codeVisitor = codeVisitor;
    }

    @Override
    public DexCodeVisitor visitCode() {
        return codeVisitor;
    }

    @Override
    public void visitEnd() {
    }

    @Override
    public DexAnnotationAble visitParameterAnnotation(int i) {
        return null;
    }

    @Override
    public DexAnnotationVisitor visitAnnotation(String s, boolean b) {
        return null;
    }
}
