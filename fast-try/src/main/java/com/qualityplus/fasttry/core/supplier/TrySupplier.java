package com.qualityplus.fasttry.core.supplier;

/**
 * Handles throwable suppliers
 *
 * @param <T> Supplier Type
 */
@FunctionalInterface
public interface TrySupplier<T> {
    /**
     * Retrieves Supplier result
     *
     * @return supplier result
     * @throws Throwable Exception
     */
    public T get() throws Throwable;
}
