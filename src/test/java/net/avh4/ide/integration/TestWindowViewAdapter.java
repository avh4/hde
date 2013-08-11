package net.avh4.ide.integration;

import net.avh4.ide.WindowView;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.PicoCompositionException;
import org.picocontainer.PicoContainer;
import org.picocontainer.PicoVisitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * PicoContainer adapter that provides instances of a given WindowView subinterface.  The provided instance
 * will be able to save and return it's current Actions.
 *
 * @see net.avh4.ide.WindowView#actions()
 * @see WindowView#setActions(Object)
 */
public class TestWindowViewAdapter<T extends WindowView<?>> implements ComponentAdapter<T> {
    private final Class<T> viewClass;

    /**
     * Convenience method to create instances with less generics verbosity.
     */
    public static <T extends WindowView<?>> TestWindowViewAdapter<T> create(Class<T> viewClass) {
        return new TestWindowViewAdapter<T>(viewClass);
    }

    public TestWindowViewAdapter(Class<T> viewClass) {
        this.viewClass = viewClass;
    }

    @Override
    public Object getComponentKey() {
        return viewClass;
    }

    @Override
    public Class<? extends T> getComponentImplementation() {
        return viewClass;
    }

    @Override
    public T getComponentInstance(PicoContainer container) throws PicoCompositionException {
        return getComponentInstance(container, NOTHING.class);
    }

    @Override
    public T getComponentInstance(PicoContainer container, Type into) throws PicoCompositionException {
        return provide();
    }

    @Override
    public void verify(PicoContainer container) throws PicoCompositionException {
    }

    @Override
    public void accept(PicoVisitor visitor) {
    }

    @Override
    public ComponentAdapter<T> getDelegate() {
        return null;
    }

    @Override
    public <U extends ComponentAdapter> U findAdapterOfType(Class<U> adapterType) {
        return null;
    }

    @Override
    public String getDescriptor() {
        return getClass().getSimpleName();
    }

    public T provide() {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
                viewClass.getClassLoader(),
                new Class[]{viewClass},
                new InvocationHandler() {
                    private Object actions;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("setActions")) {
                            actions = args[0];
                            return null;
                        } else if (method.getName().equals("actions")) {
                            return actions;
                        } else {
                            System.out.println("Unexpected method call '" + method.getName() + "' for test double " + viewClass.getSimpleName());
                            return null;
                        }
                    }
                });
    }
}
