package com.qualityplus.fasttry.core;

import com.qualityplus.fasttry.core.supplier.TrySupplier;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

/**
 * Fast Try interface
 *
 * @param <T> Generic FastTry return type
 */
public interface FastTry<T> {
    /**
     * Creates a FastTry given a {@link TrySupplier}
     *
     * @param supplier {@link TrySupplier} supplier to try
     * @return new FastTry instance
     * @param <H> Generic Supplier type
     */
    public static <H> FastTry<H> of(final TrySupplier<H> supplier) {
        H value = null;
        Throwable exception = null;

        try {
            value = supplier.get();
        } catch (final Throwable caught) {
            exception = caught;
        }

        return new FastTryImpl<>(value, exception);
    }

    /**
     * Action to execute if FastTry is on success
     *
     * @param function {@link Function} function to execute
     * @return Function result
     * @param <I> Generic Function type
     */
    public <I> I onSuccess(final Function<T, I> function);

    /**
     * Action to execute if FastTry fails
     *
     * @param function {@link Function} function to execute
     * @return Function result
     * @param <I> Generic Function type
     */
    public <I> I onFailure(final Function<T, I> function);

    /**
     * Retrieves an optional with an
     * exception if there's
     *
     * @return Optional of {@link Throwable}
     */
    public Optional<Throwable> getException();

    /**
     * Retrieves FastTry value if it's present else
     * retrieves alternative value
     *
     * @param alternative alternative value
     * @return FastTry value or alternative value
     */
    public T getOrElse(final T alternative);

    /**
     * Retrieves an optional with FastTry
     * value if there's
     *
     * @return Optional of FastTry value
     */
    public Optional<T> getValue();

    /**
     * Retrieves true if there's no
     * exception running FastTry
     *
     * @return true if there's no exception
     */
    public boolean isSuccess();

    /**
     * Retrieves true if there's
     * exception running FastTry
     *
     * @return true if there's exception
     */
    public boolean isFailure();


    /**
     * FastTry basic implementation
     *
     * @param <T> Generic FastTry return type
     */
    public class FastTryImpl<T> implements FastTry<T> {
        @Nullable
        private final Throwable exception;
        @Nullable
        private final T value;

        /**
         * All args constructor
         *
         * @param value     FastTry result
         * @param exception FastTry exception result
         */
        private FastTryImpl(final @Nullable T value, final @Nullable Throwable exception) {
            this.value = value;
            this.exception = exception;
        }

        @Override
        public Optional<Throwable> getException() {
            return Optional.ofNullable(this.exception);
        }

        @Override
        public Optional<T> getValue() {
            return Optional.ofNullable(this.value);
        }

        @Override
        public T getOrElse(final T alternative) {
            return getValue().orElse(alternative);
        }

        @Override
        public <I> I onSuccess(final Function<T, I> function) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <I> I onFailure(final Function<T, I> function) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isSuccess() {
            return getValue().isPresent();
        }

        @Override
        public boolean isFailure() {
            return getException().isPresent();
        }
    }
}
