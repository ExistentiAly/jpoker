package dev.ambs.util.function;

@FunctionalInterface
public interface Procedure {

    void execute();

    default void doNothing() {
    }
}
