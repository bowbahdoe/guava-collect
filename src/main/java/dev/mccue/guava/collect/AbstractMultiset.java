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

import static dev.mccue.guava.collect.Multisets.setCountImpl;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * This class provides a skeletal implementation of the {@code Multiset} interface. A new multiset
 * implementation can be created easily by extending this class and implementing the {@code
 * Multiset#entrySet()} method, plus optionally overriding {@code #add(Object, int)} and {@code
 * #remove(Object, int)} to enable modifications to the multiset.
 *
 * <p>The {@code #count} and {@code #size} implementations all iterate across the set returned by
 * {@code Multiset#entrySet()}, as do many methods acting on the set returned by {@code
 * #elementSet()}. Override those methods for better performance.
 *
 * @author Kevin Bourrillion
 * @author Louis Wasserman
 */
@ElementTypesAreNonnullByDefault
abstract class AbstractMultiset<E extends @Nullable Object> extends AbstractCollection<E>
    implements Multiset<E> {
  // Query Operations

  @Override
  public boolean isEmpty() {
    return entrySet().isEmpty();
  }

  @Override
  public boolean contains(@CheckForNull Object element) {
    return count(element) > 0;
  }

  // Modification Operations
  @CanIgnoreReturnValue
  @Override
  public final boolean add(@ParametricNullness E element) {
    add(element, 1);
    return true;
  }

  @CanIgnoreReturnValue
  @Override
  public int add(@ParametricNullness E element, int occurrences) {
    throw new UnsupportedOperationException();
  }

  @CanIgnoreReturnValue
  @Override
  public final boolean remove(@CheckForNull Object element) {
    return remove(element, 1) > 0;
  }

  @CanIgnoreReturnValue
  @Override
  public int remove(@CheckForNull Object element, int occurrences) {
    throw new UnsupportedOperationException();
  }

  @CanIgnoreReturnValue
  @Override
  public int setCount(@ParametricNullness E element, int count) {
    return setCountImpl(this, element, count);
  }

  @CanIgnoreReturnValue
  @Override
  public boolean setCount(@ParametricNullness E element, int oldCount, int newCount) {
    return setCountImpl(this, element, oldCount, newCount);
  }

  // Bulk Operations

  /**
   * {@inheritDoc}
   *
   * <p>This implementation is highly efficient when {@code elementsToAdd} is itself a {@code
   * Multiset}.
   */
  @CanIgnoreReturnValue
  @Override
  public final boolean addAll(Collection<? extends E> elementsToAdd) {
    return Multisets.addAllImpl(this, elementsToAdd);
  }

  @CanIgnoreReturnValue
  @Override
  public final boolean removeAll(Collection<?> elementsToRemove) {
    return Multisets.removeAllImpl(this, elementsToRemove);
  }

  @CanIgnoreReturnValue
  @Override
  public final boolean retainAll(Collection<?> elementsToRetain) {
    return Multisets.retainAllImpl(this, elementsToRetain);
  }

  @Override
  public abstract void clear();

  // Views

  @LazyInit @CheckForNull private transient Set<E> elementSet;

  @Override
  public Set<E> elementSet() {
    Set<E> result = elementSet;
    if (result == null) {
      elementSet = result = createElementSet();
    }
    return result;
  }

  /**
   * Creates a new instance of this multiset's element set, which will be returned by {@code
   * #elementSet()}.
   */
  Set<E> createElementSet() {
    return new ElementSet();
  }

  class ElementSet extends Multisets.ElementSet<E> {
    @Override
    Multiset<E> multiset() {
      return AbstractMultiset.this;
    }

    @Override
    public Iterator<E> iterator() {
      return elementIterator();
    }
  }

  abstract Iterator<E> elementIterator();

  @LazyInit @CheckForNull private transient Set<Entry<E>> entrySet;

  @Override
  public Set<Entry<E>> entrySet() {
    Set<Entry<E>> result = entrySet;
    if (result == null) {
      entrySet = result = createEntrySet();
    }
    return result;
  }

  class EntrySet extends Multisets.EntrySet<E> {
    @Override
    Multiset<E> multiset() {
      return AbstractMultiset.this;
    }

    @Override
    public Iterator<Entry<E>> iterator() {
      return entryIterator();
    }

    @Override
    public int size() {
      return distinctElements();
    }
  }

  Set<Entry<E>> createEntrySet() {
    return new EntrySet();
  }

  abstract Iterator<Entry<E>> entryIterator();

  abstract int distinctElements();

  // Object methods

  /**
   * {@inheritDoc}
   *
   * <p>This implementation returns {@code true} if {@code object} is a multiset of the same size
   * and if, for each element, the two multisets have the same count.
   */
  @Override
  public final boolean equals(@CheckForNull Object object) {
    return Multisets.equalsImpl(this, object);
  }

  /**
   * {@inheritDoc}
   *
   * <p>This implementation returns the hash code of {@code Multiset#entrySet()}.
   */
  @Override
  public final int hashCode() {
    return entrySet().hashCode();
  }

  /**
   * {@inheritDoc}
   *
   * <p>This implementation returns the result of invoking {@code toString} on {@code
   * Multiset#entrySet()}.
   */
  @Override
  public final String toString() {
    return entrySet().toString();
  }
}
