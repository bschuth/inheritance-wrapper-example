package com.schuth.inheritanceexplain;

import java.util.logging.Logger;

public class ImplementationWrapperMain {
    public static final Logger log = Logger.getLogger("ImplementationWrapperMain");

    public static void main(String[] args) {
        TestableProductionClass actual = new TestableProductionClass();
        log.info("This is main - let's run the unoverridden method on the real class");
        actual.runUnoverriddenUnnamedMethod();
        log.info("This is main - now the overridden same name method on the real class");
        actual.runOverriddenSameNameMethod();
        log.info("look above - see, the super class didn't get called because the overridden child class method was called instead");
        log.info("This is main - lets call the method that delegates to the renamed overridden method");
        actual.runOverriddenWrapNameMethod();
        log.info("Isn't that better?");
        log.info("Finally, just for completeness, we call the method that can't be overridden, through its renamed wrap");
        actual.runCantOverrideMethod();
    }
}
