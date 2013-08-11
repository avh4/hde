package net.avh4.ide.platforms;

import com.google.common.collect.ImmutableSet;
import fj.F;
import fj.Ord;
import fj.data.Set;

public class CodePackage {
    public static final Ord<CodePackage> nameOrdering =
            Ord.stringOrd.comap(new F<CodePackage, String>() {
                @Override
                public String f(CodePackage codePackage) {
                    return codePackage.name;
                }
            });

    private String name;
    private Set<CodeClass> classes;

    public CodePackage(String name) {
        this.name = name;
        this.classes = Set.empty(CodeClass.nameOrdering);
    }

    public String getName() {
        return name;
    }

    public ImmutableSet<CodeClass> getClasses() {
        return ImmutableSet.copyOf(classes);
    }

    public void addClass(CodeClass codeClass) {
        classes = classes.insert(codeClass);
    }
}
