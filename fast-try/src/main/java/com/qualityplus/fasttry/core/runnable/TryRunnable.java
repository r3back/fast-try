package com.qualityplus.fasttry.core.runnable;

/**
 * Handles throwable runnable
 */
@FunctionalInterface
public interface TryRunnable {
    /**
     * Runs runnable logic
     *
     * @throws Throwable Exception
     */
    public abstract void run() throws Throwable;
}
