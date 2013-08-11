package net.avh4.ide.struct;

import net.avh4.ide.struct.example.Watch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class StructRef<T> extends Ref<T> {
    private final Class<T> structClass;
    private final HashMap<String, Ref<String>> refs = new HashMap<String, Ref<String>>();
    private final Watch<Object> update = new Watch<Object>() {
        @Override
        public void update(Object value) {
            notifyWatchers();
        }
    };

    public StructRef(Class<T> structClass) {
        this.structClass = structClass;
        this.value = newValueProxy(structClass);
    }

    public T watchAttribute(final Watch<?> watch) {
        return newWatchProxy(watch);
    }

    private Ref<String> getRef(String name) {
        if (!refs.containsKey(name)) refs.put(name, new StringRef(update));
        return refs.get(name);
    }

    public T setAttribute(final String value) {
        return newSetProxy(value);
    }

    private T newValueProxy(Class<T> structClass) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(structClass.getClassLoader(), new Class[]{structClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String attribute = method.getName();
                final Ref<String> ref = getRef(attribute);
                return ref.get();
            }
        });
    }

    private T newWatchProxy(final Watch<?> watch) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(structClass.getClassLoader(), new Class[]{structClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String attribute = method.getName();
                final Ref<String> ref = getRef(attribute);
                ref.watch((Watch<? super String>) watch);
                return null;
            }
        });
    }

    private T newSetProxy(final String value) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(structClass.getClassLoader(), new Class[]{structClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String attribute = method.getName();
                final Ref<String> ref = getRef(attribute);
                ref.set(value);
                return null;
            }
        });
    }

}
