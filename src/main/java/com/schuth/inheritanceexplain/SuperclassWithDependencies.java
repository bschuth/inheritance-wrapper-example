package com.schuth.inheritanceexplain;

import java.util.logging.Logger;

/**
 * This class models a superclass that has four methods, all of which have dependencies we don't running
 * in unit tests. The rest of this project defines a wrapper strategy using inheritance that enables us
 * to substitute mocks. There are no tests here, however, as the point of this example is to demonstrate
 * naming issues in the wrapper interface and implementation.
 *
 * The wrapper interface is called IDependentSuperclassWrapper. The implementation of that interface is a
 * class that extends this one, called DependentSuperclassWrapperImpl.
 */
public class SuperclassWithDependencies {
    Logger log;
    public SuperclassWithDependencies() {
        log = Logger.getLogger("AnnoyingSuperclass");
    }

    /**
     * This is a final method - it cannot be overridden at all, but we still want to wrap it in the wrapper
     * interface. We will be forced to give the method in the wrapper interface a different name, because
     * our DependentSupercallWrapperImpl class can't use this method name at all.
     */
    public final void cantOverrideMethod() {
        log.info("this is the cantOverrideMethod in the super class and I'm going to call a dependency!");
    }
    /**
     * For demonstration purposes, this method exists to show that so long as you don't override a superclass
     * method in the child class, you can get away without adding "Wrap" or anything to the method name. The problem
     * only arises if there is an override, or a final like above. I happen to think you should just rename all of
     * the interface methods to *Wrap for consistency's sake.
     */
    public void unoverriddenMethod() {
        log.info("This is unoverriddenMethod  in the super class and I'm going to call a dependency!");
    }

    /**
     *  This method is will be overridden in the child class, and we'll use exactly this name in the wrapper as
     *  well. You'll see at runtime this means that you can have situation where your CODE suggests you are running
     *  the super wrapper - but the child method gets run instead!
     */
    public void overriddenMethod() {
        log.info("This is overriddenMethodWithSameName in the super class and I'm calling a dependency!");
    }
}
