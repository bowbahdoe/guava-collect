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
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@code Multimap} that cannot hold duplicate key-value pairs. Adding a key-value pair that's
 * already in the multimap has no effect. See the {@code Multimap} documentation for information
 * common to all multimaps.
 *
 * <p>The {@code #get}, {@code #removeAll}, and {@code #replaceValues} methods each return a {@code
 * Set} of values, while {@code #entries} returns a {@code Set} of map entries. Though the method
 * signature doesn't say so explicitly, the map returned by {@code #asMap} has {@code Set} values.
 *
 * <p>If the values corresponding to a single key should be ordered according to a {@code
 * java.util.Comparator} (or the natural order), see the {@code SortedSetMultimap} subinterface.
 *
 * <p>Since the value collections are sets, the behavior of a {@code SetMultimap} is not specified
 * if key <em>or value</em> objects already present in the multimap change in a manner that affects
 * {@code equals} comparisons. Use caution if mutable objects are used as keys or values in a {@code
 * SetMultimap}.
 *
 * <p><b>Warning:</b> Do not modify either a key <i>or a value</i> of a {@code SetMultimap} in a way
 * that affects its {@code Object#equals} behavior. Undefined behavior and bugs will result.
 *
 * <p>See the Guava User Guide article on <a href=
 * "https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap">{@code Multimap}</a>.
 *
 * @author Jared Levy
 * @since 2.0
 */
@ElementTypesAreNonnullByDefault
public interface SetMultimap<K extends @Nullable Object, V extends @Nullable Object>
    extends Multimap<K, V> {
  /**
   * {@inheritDoc}
   *
   * <p>Because a {@code SetMultimap} has unique values for a given key, this method returns a
   * {@code Set}, instead of the {@code java.util.Collection} specified in the {@code Multimap}
   * interface.
   */
  @Override
  Set<V> get(@ParametricNullness K key);

  /**
   * {@inheritDoc}
   *
   * <p>Because a {@code SetMultimap} has unique values for a given key, this method returns a
   * {@code Set}, instead of the {@code java.util.Collection} specified in the {@code Multimap}
   * interface.
   */
  @CanIgnoreReturnValue
  @Override
  Set<V> removeAll(@CheckForNull Object key);

  /**
   * {@inheritDoc}
   *
   * <p>Because a {@code SetMultimap} has unique values for a given key, this method returns a
   * {@code Set}, instead of the {@code java.util.Collection} specified in the {@code Multimap}
   * interface.
   *
   * <p>Any duplicates in {@code values} will be stored in the multimap once.
   */
  @CanIgnoreReturnValue
  @Override
  Set<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);

  /**
   * {@inheritDoc}
   *
   * <p>Because a {@code SetMultimap} has unique values for a given key, this method returns a
   * {@code Set}, instead of the {@code java.util.Collection} specified in the {@code Multimap}
   * interface.
   */
  @Override
  Set<Entry<K, V>> entries();

  /**
   * {@inheritDoc}
   *
   * <p><b>Note:</b> The returned map's values are guaranteed to be of type {@code Set}. To obtain
   * this map with the more specific generic type {@code Map<K, Set<V>>}, call {@code
   * Multimaps#asMap(SetMultimap)} instead.
   */
  @Override
  Map<K, Collection<V>> asMap();

  /**
   * Compares the specified object to this multimap for equality.
   *
   * <p>Two {@code SetMultimap} instances are equal if, for each key, they contain the same values.
   * Equality does not depend on the ordering of keys or values.
   *
   * <p>An empty {@code SetMultimap} is equal to any other empty {@code Multimap}, including an
   * empty {@code ListMultimap}.
   */
  @Override
  boolean equals(@CheckForNull Object obj);
}
