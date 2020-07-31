package com.schuth.inheritanceexplain;

import java.util.logging.Logger;

/**
 * This wrapper class is mostly totally boring. All it exists to do is to delegate up to the
 * SuperclassWithDependencies class which is its parent (and which is also the grandparent of the TestableProductionClass,
 * of which we are the parent).
 *
 * One interesting wrinkle - I realized while writing this that ALL of these should be marked FINAL. It would make
 * no sense for TestableProductionClass to override these methods, and it could make things confusing.
 */
public class DependentSuperclassWrapperImpl extends SuperclassWithDependencies
implements IDependentSuperclassWrapper
{
    static final Logger log = Logger.getLogger("DependentSuperclassWrapperImpl");

    /**
     * We couldn't call this cantOverrideMethod because it is final in SuperclassWithDependencies, so we can't
     * override it here.
     */
    @Override
    public final void cantOverrideMethodWrap() {
        log.info("cantOverrideMethodWrap delegating to cantOverrideMethod");
        super.cantOverrideMethod();
    }

    /**
     * This exists as an example to show that, yes, if TestableProductionClass doesn't ever override this method,
     * you could get away with not using the same name. Note that I did NOT make this 'final' though - that would
     * be a defect if someone later overrode it in TestableProductionClass and didn't realize it would break the
     * testing design. That's why I think this is a BAD idea. And you may not want to put 'final' here because for all
     * you know, it SHOULD be overridden later. So we shouldn't use the same name in the wrapper interface, is my point.
     */
    @Override
    public void unoverriddenMethod() {
        log.info("unoverriddenMethod delegating to super unoverriddenMethod");
        super.unoverriddenMethod();
    }

    /**
     * Defect - this won't get called at all in our code because the override in TestableProductionClass will get
     * called instead.
     */
    @Override
    public void overriddenMethod() {
        log.info("overriddenMethod delegating to super overriddenMethod");
        super.overriddenMethod();
    }

    /**
     * ...and this is how you fix that. Rename it so it has a distinct name, and put final on it so its
     * behavior can't change.
     */
    @Override
    public final void overriddenMethodWrap() {
        log.info("overriddenMethodWrap delegating to super overriddenMethod");
        super.overriddenMethod();
    }
}
