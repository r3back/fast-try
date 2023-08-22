package com.qualityplus.fasttry.core.mock;

/**
 * Mock Service
 */
public final class MockService {
    /**
     * Empty Constructor
     */
    public MockService() {
    }

    /**
     * Retrieves username
     *
     * @return username
     * @throws MockException Exception
     */
    public String getUser() throws Exception {
        throw new Exception();
    }
}
