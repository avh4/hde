package net.avh4.ide.test.support.dex;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.googlecode.dex2jar.visitors.DexCodeVisitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public abstract class DebuggingDexCodeVisitor {
    public static DexCodeVisitor create() {

        return (DexCodeVisitor) Proxy.newProxyInstance(DexCodeVisitor.class.getClassLoader(),
                new Class[]{DexCodeVisitor.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, java.lang.reflect.Method method,
                                 Object[] args) throws Throwable {
                final Iterable<Object> safeArgs = Iterables.transform(
                        args == null ? new ArrayList<Object>() : Arrays.asList(args),
                        new com.google.common.base.Function<Object, Object>() {
                            @Override
                            public Object apply(Object input) {
                                if (input == null) return "(null)";
                                return input.toString();
                            }
                        });
                System.out.println(method.getName() + ": " + Joiner.on(", ").join(safeArgs));
                return null;
            }
        });
    }
}
