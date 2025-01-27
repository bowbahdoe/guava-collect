/*
 * Copyright (C) 2012 The Guava Authors
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

import static dev.mccue.guava.base.Preconditions.checkArgument;
import static dev.mccue.guava.collect.Iterables.getOnlyElement;

import dev.mccue.guava.collect.ImmutableMap.IteratorBasedImmutableMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import dev.mccue.jsr305.CheckForNull;

/**
 * Implementation of {@code ImmutableMap} backed by a non-empty {@code java.util.EnumMap}.
 *
 * @author Louis Wasserman
 */

@SuppressWarnings("serial") // we're overriding default serialization
@ElementTypesAreNonnullByDefault
final class ImmutableEnumMap<K extends Enum<K>, V> extends IteratorBasedImmutableMap<K, V> {
  static <K extends Enum<K>, V> ImmutableMap<K, V> asImmutable(EnumMap<K, V> map) {
    switch (map.size()) {
      case 0:
        return ImmutableMap.of();
      case 1:
        Entry<K, V> entry = getOnlyElement(map.entrySet());
        return ImmutableMap.of(entry.getKey(), entry.getValue());
      default:
        return new ImmutableEnumMap<>(map);
    }
  }

  private final transient EnumMap<K, V> delegate;

  private ImmutableEnumMap(EnumMap<K, V> delegate) {
    this.delegate = delegate;
    checkArgument(!delegate.isEmpty());
  }

  @Override
  UnmodifiableIterator<K> keyIterator() {
    return Iterators.unmodifiableIterator(delegate.keySet().iterator());
  }

  @Override
  Spliterator<K> keySpliterator() {
    return delegate.keySet().spliterator();
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean containsKey(@CheckForNull Object key) {
    return delegate.containsKey(key);
  }

  @Override
  @CheckForNull
  public V get(@CheckForNull Object key) {
    return delegate.get(key);
  }

  @Override
  public boolean equals(@CheckForNull Object object) {
    if (object == this) {
      return true;
    }
    if (object instanceof ImmutableEnumMap) {
      object = ((ImmutableEnumMap<?, ?>) object).delegate;
    }
    return delegate.equals(object);
  }

  @Override
  UnmodifiableIterator<Entry<K, V>> entryIterator() {
    return Maps.unmodifiableEntryIterator(delegate.entrySet().iterator());
  }

  @Override
  Spliterator<Entry<K, V>> entrySpliterator() {
    return CollectSpliterators.map(delegate.entrySet().spliterator(), Maps::unmodifiableEntry);
  }

  @Override
  public void forEach(BiConsumer<? super K, ? super V> action) {
    delegate.forEach(action);
  }

  @Override
  boolean isPartialView() {
    return false;
  }

  // All callers of the constructor are restricted to <K extends Enum<K>>.
  @Override
  // serialization
  Object writeReplace() {
    return new EnumSerializedForm<>(delegate);
  }

  // serialization
  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Use EnumSerializedForm");
  }

  /*
   * This class is used to serialize ImmutableEnumMap instances.
   */
  // serialization
  private static class EnumSerializedForm<K extends Enum<K>, V> implements Serializable {
    final EnumMap<K, V> delegate;

    EnumSerializedForm(EnumMap<K, V> delegate) {
      this.delegate = delegate;
    }

    Object readResolve() {
      return new ImmutableEnumMap<>(delegate);
    }

    private static final long serialVersionUID = 0;
  }
}
