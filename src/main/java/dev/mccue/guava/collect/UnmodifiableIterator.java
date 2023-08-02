/*
 * Copyright (C) 2008 The Guava Authors
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

import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An iterator that does not support {@code #remove}.
 *
 * <p>{@code UnmodifiableIterator} is used primarily in conjunction with implementations of {@code
 * ImmutableCollection}, such as {@code ImmutableList}. You can, however, convert an existing
 * iterator to an {@code UnmodifiableIterator} using {@code Iterators#unmodifiableIterator}.
 *
 * @author Jared Levy
 * @since 2.0
 */
@ElementTypesAreNonnullByDefault
public abstract class UnmodifiableIterator<E extends @Nullable Object> implements Iterator<E> {
  /** Constructor for use by subclasses. */
  protected UnmodifiableIterator() {}

  /**
   * Guaranteed to throw an exception and leave the underlying data unmodified.
   *
   * @throws UnsupportedOperationException always
   * @deprecated Unsupported operation.
   */
  @Deprecated
  @Override
  @DoNotCall("Always throws UnsupportedOperationException")
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}
