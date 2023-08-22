package com.qualityplus.fasttry.core;

import com.qualityplus.fasttry.core.mock.MockException;
import com.qualityplus.fasttry.core.mock.MockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class FastTryTest {
    private static final String DEFAULT_USERNAME = "test_user";
    @Mock
    private MockService service;

    /**
     * Test when exception is present in {@link FastTry}
     */
    @Test
    @DisplayName("Test when exception is present")
    public void testWhenExceptionIsPresent() throws Exception {
        Mockito.when(this.service.getUser()).thenThrow(new MockException());

        final FastTry<String> result = FastTry.of(this.service::getUser);

        final boolean isMockException = result.getException()
                .map(throwable -> throwable instanceof MockException)
                .orElse(false);

        Assertions.assertTrue(isMockException);

        Assertions.assertTrue(result.getException().isPresent());
        Assertions.assertFalse(result.getValue().isPresent());

        Assertions.assertTrue(result.isFailure());
        Assertions.assertFalse(result.isSuccess());
    }


    /**
     * Test when exception is not present in {@link FastTry}
     */
    @Test
    @DisplayName("Test when exception is not present")
    public void testWhenExceptionIsNotPresent() throws Exception {
        Mockito.when(this.service.getUser()).thenReturn(DEFAULT_USERNAME);

        final FastTry<String> result = FastTry.of(this.service::getUser);

        Assertions.assertFalse(result.getException().isPresent());
        Assertions.assertTrue(result.getValue().isPresent());

        Assertions.assertFalse(result.isFailure());
        Assertions.assertTrue(result.isSuccess());
    }
}
