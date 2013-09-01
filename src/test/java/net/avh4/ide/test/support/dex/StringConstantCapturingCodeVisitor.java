package net.avh4.ide.test.support.dex;

import com.googlecode.dex2jar.DexLabel;
import com.googlecode.dex2jar.Field;
import com.googlecode.dex2jar.Method;
import com.googlecode.dex2jar.visitors.DexCodeVisitor;

public class StringConstantCapturingCodeVisitor implements DexCodeVisitor {
    public String capturedValue;

    @Override
    public void visitArrayStmt(int i, int i2, int i3, int i4, int i5) {
    }

    @Override
    public void visitBinopLitXStmt(int i, int i2, int i3, int i4) {
    }

    @Override
    public void visitBinopStmt(int i, int i2, int i3, int i4, int i5) {
    }

    @Override
    public void visitClassStmt(int i, int i2, int i3, String s) {
    }

    @Override
    public void visitClassStmt(int i, int i2, String s) {
    }

    @Override
    public void visitCmpStmt(int i, int i2, int i3, int i4, int i5) {
    }

    @Override
    public void visitConstStmt(int i, int i2, Object o, int i3) {
        if (o instanceof String) this.capturedValue = (String) o;
    }

    @Override
    public void visitFieldStmt(int i, int i2, Field field, int i3) {
    }

    @Override
    public void visitFieldStmt(int i, int i2, int i3, Field field, int i4) {
    }

    @Override
    public void visitFillArrayStmt(int i, int i2, int i3, int i4, Object[] objects) {
    }

    @Override
    public void visitFilledNewArrayStmt(int i, int[] ints, String s) {
    }

    @Override
    public void visitJumpStmt(int i, int i2, int i3, DexLabel dexLabel) {
    }

    @Override
    public void visitJumpStmt(int i, int i2, DexLabel dexLabel) {
    }

    @Override
    public void visitJumpStmt(int i, DexLabel dexLabel) {
    }

    @Override
    public void visitLookupSwitchStmt(int i, int i2, DexLabel dexLabel, int[] ints,
                                      DexLabel[] dexLabels) {
    }

    @Override
    public void visitMethodStmt(int i, int[] ints, Method method) {
    }

    @Override
    public void visitMonitorStmt(int i, int i2) {
    }

    @Override
    public void visitMoveStmt(int i, int i2, int i3) {
    }

    @Override
    public void visitMoveStmt(int i, int i2, int i3, int i4) {
    }

    @Override
    public void visitReturnStmt(int i) {
    }

    @Override
    public void visitReturnStmt(int i, int i2, int i3) {
    }

    @Override
    public void visitTableSwitchStmt(int i, int i2, DexLabel dexLabel, int i3, int i4,
                                     DexLabel[] dexLabels) {
    }

    @Override
    public void visitUnopStmt(int i, int i2, int i3, int i4) {
    }

    @Override
    public void visitUnopStmt(int i, int i2, int i3, int i4, int i5) {
    }

    @Override
    public void visitTryCatch(DexLabel dexLabel, DexLabel dexLabel2,
                              DexLabel[] dexLabels, String[] strings) {
    }

    @Override
    public void visitArguments(int i, int[] ints) {
    }

    @Override
    public void visitEnd() {
    }

    @Override
    public void visitLabel(DexLabel dexLabel) {
    }

    @Override
    public void visitLineNumber(int i, DexLabel dexLabel) {
    }

    @Override
    public void visitLocalVariable(String s, String s2, String s3, DexLabel dexLabel,
                                   DexLabel dexLabel2, int i) {
    }
}
