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
        throw new MockException();
    }

    /**
     * Runs task
     */
    public void runTask() {

    }

    /**
     * Runs throwable task
     */
    public void runThrowableTask() throws Exception {
        System.out.println("E");

        throw new MockException();
    }
}
