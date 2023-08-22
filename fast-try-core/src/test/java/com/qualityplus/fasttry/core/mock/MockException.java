package com.qualityplus.fasttry.core.mock;

/**
 * Mock Exception
 */
public final class MockException extends Exception {
    /**
     * Empty constructor
     */
    public MockException() {
    }

    /**
     * Constructor with message
     *
     * @param message exception message
     */
    public MockException(final String message) {
        super(message);
    }
}
