package net.avh4.ide.test.support;

import net.avh4.ide.WindowView;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.PicoCompositionException;
import org.picocontainer.PicoContainer;
import org.picocontainer.PicoVisitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * PicoContainer adapter that provides instances of a given WindowView subinterface.  The provided instance
 * will be able to save and return parameters passed to its methods.
 */
public class TestWindowViewAdapter<T extends WindowView<?>> implements ComponentAdapter<T> {
    private final Class<T> viewClass;

    public interface TestWindowView {
        Object _get(String methodName, int parameterIndex);
    }

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
                new Class[]{viewClass, TestWindowView.class},
                new ParamStoringInvocationHandler());
    }

    private static class ParamStoringInvocationHandler implements InvocationHandler {
        private HashMap<String, Object> savedParams = new HashMap<String, Object>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("_get")) {
                String methodName = (String) args[0];
                Integer index = (Integer) args[1];
                String key = methodName + "-" + index;
                return savedParams.get(key);
            } else {
                String methodName = method.getName();
                for (int i = 0; i < args.length; i++) {
                    String key = methodName + "-" + i;
                    savedParams.put(key, args[i]);
                }
                return null;
            }
        }
    }
}
