package com.schuth.inheritanceexplain;

import java.util.logging.Logger;

/**
 * This is the actual class we use in our product. It derives from the superclass wrapper implementation class.
 * The whole trick of this approach is that means that THIS class already has all of the wrapper methods it
 * needs because it's parent has them. However, that leads to one possible gotcha...
 */
public class TestableProductionClass extends DependentSuperclassWrapperImpl {
    public static final Logger logger = Logger.getLogger("ActualClassWeWant");

    /**
     * We use this instance variable to call our superclass methods with unwanted dependencies. You
     * use this in conjunction with second constructor; in test code, you can provide a mock of
     * IDependentSuperclassWrapper and use that to stub and verify. However, in production code we use
     * the parameterless constructor - and we just assign ourselves to wrapper!
     */
    IDependentSuperclassWrapper wrapper;

    /**
     * This is the magic. By assigning "this" to wrapper, all calls on the wrapper object will result
     * in calls to our superclass wrapper methods in our parent class DependentSuperclassWrapperImpl.
     */
    public TestableProductionClass() {
        wrapper = this;
    }

    /**
     * Test constructor. Put your mock here.
     * @param testWrapper
     */
    public TestableProductionClass(IDependentSuperclassWrapper testWrapper) {
        wrapper = testWrapper;
    }

    /**
     * This method calls our unoverriddenMethod. The wrapper interface method has the same name as the SuperclassWithDependencies
     * method we want; that's not a problem here because we don't override it ourselves, at and runtime the
     * class in DependentSuperclassWrapperImpl.unoverriddenMethod() is called as we expect.
     */
    public void runUnoverriddenUnnamedMethod() {
        logger.info("Running the unoverridden method through wrapper. I expect a call to the superclass");
        wrapper.unoverriddenMethod();
    }

    /**
     * This method calls overriddenMethod through the interface method that has the SAME NAME as our own override
     * and the superclass name. This will work just fine in testing, because wrapper will be assigned to a totally
     * independent mock object. But at runtime, since wrapper = this, calling wrapper.overrideMethod means OUR OWN
     * OVERRIDE gets called instead of DependentSuperclassWrapperImpl.overriddenMethod, which means that
     * SuperclassWithDependencies.overriddenMethod is NOT called. But when we make a call on the wrapper, we specifically
     * want to end up with a call in SuperclassWithDependencies. Doh!
     */
    public void runOverriddenSameNameMethod() {
        logger.info("Running the overridden method *through wrapper* - I expect a call to the superclass!");
        wrapper.overriddenMethod();
    }

    /**
     * This method does it the right way. Because we use a differently named (i.e. with "Wrap" appended) method, now
     * we can ONLY call the method in DependentSuperclassWrapperImpl, and so it will delegate to SuperclassWithDependencies
     * just like we want.
     */
    public void runOverriddenWrapNameMethod() {
        logger.info("running the super method by using the RENAMED 'wrap' method which will ensure the superclass is called");
        wrapper.overriddenMethodWrap();
    }

    /**
     * Nothing terribly interesting here - since cantOverride is final in the superclass, we can't override it even
     * in our wrapper class, so we will call the Wrap method.
     */
    public void runCantOverrideMethod() {
        logger.info("running the super final method using a renamed wrap method");
        wrapper.cantOverrideMethodWrap();
    }

    @Override
    public void overriddenMethod() {
        logger.info("This is the overriddenMethodWithSameName in the child class");
    }
}
