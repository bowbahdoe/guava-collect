/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.mccue.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import java.util.Queue;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A queue which forwards all its method calls to another queue. Subclasses should override one or
 * more methods to modify the behavior of the backing queue as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * <p><b>Warning:</b> The methods of {@code ForwardingQueue} forward <b>indiscriminately</b> to the
 * methods of the delegate. For example, overriding {@code #add} alone <b>will not</b> change the
 * behavior of {@code #offer} which can lead to unexpected behavior. In this case, you should
 * override {@code offer} as well, either providing your own implementation, or delegating to the
 * provided {@code standardOffer} method.
 *
 * <p><b>{@code default} method warning:</b> This class does <i>not</i> forward calls to {@code
 * default} methods. Instead, it inherits their default implementations. When those implementations
 * invoke methods, they invoke methods on the {@code ForwardingQueue}.
 *
 * <p>The {@code standard} methods are not guaranteed to be thread-safe, even when all of the
 * methods that they depend on are thread-safe.
 *
 * @author Mike Bostock
 * @author Louis Wasserman
 * @since 2.0
 */
@ElementTypesAreNonnullByDefault
public abstract class ForwardingQueue<E extends @Nullable Object> extends ForwardingCollection<E>
    implements Queue<E> {

  /** Constructor for use by subclasses. */
  protected ForwardingQueue() {}

  @Override
  protected abstract Queue<E> delegate();

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  public boolean offer(@ParametricNullness E o) {
    return delegate().offer(o);
  }

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  @CheckForNull
  public E poll() {
    return delegate().poll();
  }

  @CanIgnoreReturnValue
  @Override
  @ParametricNullness
  public E remove() {
    return delegate().remove();
  }

  @Override
  @CheckForNull
  public E peek() {
    return delegate().peek();
  }

  @Override
  @ParametricNullness
  public E element() {
    return delegate().element();
  }

  /**
   * A sensible definition of {@code #offer} in terms of {@code #add}. If you override {@code #add},
   * you may wish to override {@code #offer} to forward to this implementation.
   *
   * @since 7.0
   */
  protected boolean standardOffer(@ParametricNullness E e) {
    try {
      return add(e);
    } catch (IllegalStateException caught) {
      return false;
    }
  }

  /**
   * A sensible definition of {@code #peek} in terms of {@code #element}. If you override {@code
   * #element}, you may wish to override {@code #peek} to forward to this implementation.
   *
   * @since 7.0
   */
  @CheckForNull
  protected E standardPeek() {
    try {
      return element();
    } catch (NoSuchElementException caught) {
      return null;
    }
  }

  /**
   * A sensible definition of {@code #poll} in terms of {@code #remove}. If you override {@code
   * #remove}, you may wish to override {@code #poll} to forward to this implementation.
   *
   * @since 7.0
   */
  @CheckForNull
  protected E standardPoll() {
    try {
      return remove();
    } catch (NoSuchElementException caught) {
      return null;
    }
  }
}
