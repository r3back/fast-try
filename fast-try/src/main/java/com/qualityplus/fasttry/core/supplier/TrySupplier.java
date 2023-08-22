package com.qualityplus.fasttry.core.supplier;

@FunctionalInterface
public interface TrySupplier<T> {
    public T get() throws Throwable;
}
