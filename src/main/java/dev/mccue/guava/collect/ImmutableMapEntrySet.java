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

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Spliterator;
import java.util.function.Consumer;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * {@code entrySet()} implementation for {@code ImmutableMap}.
 *
 * @author Jesse Wilson
 * @author Kevin Bourrillion
 */

@ElementTypesAreNonnullByDefault
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet.CachingAsList<Entry<K, V>> {
  static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
    private final transient ImmutableMap<K, V> map;
    private final transient ImmutableList<Entry<K, V>> entries;

    RegularEntrySet(ImmutableMap<K, V> map, Entry<K, V>[] entries) {
      this(map, ImmutableList.<Entry<K, V>>asImmutableList(entries));
    }

    RegularEntrySet(ImmutableMap<K, V> map, ImmutableList<Entry<K, V>> entries) {
      this.map = map;
      this.entries = entries;
    }

    @Override
    ImmutableMap<K, V> map() {
      return map;
    }

    @Override
    
    int copyIntoArray(@Nullable Object[] dst, int offset) {
      return entries.copyIntoArray(dst, offset);
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
      return entries.iterator();
    }

    @Override
    public Spliterator<Entry<K, V>> spliterator() {
      return entries.spliterator();
    }

    @Override
    public void forEach(Consumer<? super Entry<K, V>> action) {
      entries.forEach(action);
    }

    @Override
    ImmutableList<Entry<K, V>> createAsList() {
      return new RegularImmutableAsList<>(this, entries);
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

  ImmutableMapEntrySet() {}

  abstract ImmutableMap<K, V> map();

  @Override
  public int size() {
    return map().size();
  }

  @Override
  public boolean contains(@CheckForNull Object object) {
    if (object instanceof Entry) {
      Entry<?, ?> entry = (Entry<?, ?>) object;
      V value = map().get(entry.getKey());
      return value != null && value.equals(entry.getValue());
    }
    return false;
  }

  @Override
  boolean isPartialView() {
    return map().isPartialView();
  }

  @Override
  // not used in GWT
  boolean isHashCodeFast() {
    return map().isHashCodeFast();
  }

  @Override
  public int hashCode() {
    return map().hashCode();
  }

  // serialization
  @Override
  Object writeReplace() {
    return new EntrySetSerializedForm<>(map());
  }

  // serialization
  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Use EntrySetSerializedForm");
  }

  // serialization
  private static class EntrySetSerializedForm<K, V> implements Serializable {
    final ImmutableMap<K, V> map;

    EntrySetSerializedForm(ImmutableMap<K, V> map) {
      this.map = map;
    }

    Object readResolve() {
      return map.entrySet();
    }

    private static final long serialVersionUID = 0;
  }
}
