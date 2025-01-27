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

import static dev.mccue.guava.collect.Iterators.singletonIterator;

import dev.mccue.guava.base.Preconditions;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Implementation of {@code ImmutableSet} with exactly one element.
 *
 * @author Kevin Bourrillion
 * @author Nick Kralevich
 */

@SuppressWarnings("serial") // uses writeReplace(), not default serialization
@ElementTypesAreNonnullByDefault
final class SingletonImmutableSet<E> extends ImmutableSet<E> {
  // We deliberately avoid caching the asList and hashCode here, to ensure that with
  // compressed oops, a SingletonImmutableSet packs all the way down to the optimal 16 bytes.

  final transient E element;

  SingletonImmutableSet(E element) {
    this.element = Preconditions.checkNotNull(element);
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean contains(@CheckForNull Object target) {
    return element.equals(target);
  }

  @Override
  public UnmodifiableIterator<E> iterator() {
    return singletonIterator(element);
  }

  @Override
  public ImmutableList<E> asList() {
    return ImmutableList.of(element);
  }

  @Override
  boolean isPartialView() {
    return false;
  }

  @Override
  int copyIntoArray(@Nullable Object[] dst, int offset) {
    dst[offset] = element;
    return offset + 1;
  }

  @Override
  public final int hashCode() {
    return element.hashCode();
  }

  @Override
  public String toString() {
    return '[' + element.toString() + ']';
  }

  // redeclare to help optimizers with b/310253115
  @SuppressWarnings("RedundantOverride")
  @Override
  // serialization
  // serialization
  Object writeReplace() {
    return super.writeReplace();
  }
}
