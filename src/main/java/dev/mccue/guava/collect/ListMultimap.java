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
import java.util.List;
import java.util.Map;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@code Multimap} that can hold duplicate key-value pairs and that maintains the insertion
 * ordering of values for a given key. See the {@code Multimap} documentation for information common
 * to all multimaps.
 *
 * <p>The {@code #get}, {@code #removeAll}, and {@code #replaceValues} methods each return a {@code
 * List} of values. Though the method signature doesn't say so explicitly, the map returned by
 * {@code #asMap} has {@code List} values.
 *
 * <p>See the Guava User Guide article on <a href=
 * "https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap">{@code Multimap}</a>.
 *
 * @author Jared Levy
 * @since 2.0
 */
@ElementTypesAreNonnullByDefault
public interface ListMultimap<K extends @Nullable Object, V extends @Nullable Object>
    extends Multimap<K, V> {
  /**
   * {@inheritDoc}
   *
   * <p>Because the values for a given key may have duplicates and follow the insertion ordering,
   * this method returns a {@code List}, instead of the {@code java.util.Collection} specified in
   * the {@code Multimap} interface.
   */
  @Override
  List<V> get(@ParametricNullness K key);

  /**
   * {@inheritDoc}
   *
   * <p>Because the values for a given key may have duplicates and follow the insertion ordering,
   * this method returns a {@code List}, instead of the {@code java.util.Collection} specified in
   * the {@code Multimap} interface.
   */
  @CanIgnoreReturnValue
  @Override
  List<V> removeAll(@CheckForNull Object key);

  /**
   * {@inheritDoc}
   *
   * <p>Because the values for a given key may have duplicates and follow the insertion ordering,
   * this method returns a {@code List}, instead of the {@code java.util.Collection} specified in
   * the {@code Multimap} interface.
   */
  @CanIgnoreReturnValue
  @Override
  List<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);

  /**
   * {@inheritDoc}
   *
   * <p><b>Note:</b> The returned map's values are guaranteed to be of type {@code List}. To obtain
   * this map with the more specific generic type {@code Map<K, List<V>>}, call {@code
   * Multimaps#asMap(ListMultimap)} instead.
   */
  @Override
  Map<K, Collection<V>> asMap();

  /**
   * Compares the specified object to this multimap for equality.
   *
   * <p>Two {@code ListMultimap} instances are equal if, for each key, they contain the same values
   * in the same order. If the value orderings disagree, the multimaps will not be considered equal.
   *
   * <p>An empty {@code ListMultimap} is equal to any other empty {@code Multimap}, including an
   * empty {@code SetMultimap}.
   */
  @Override
  boolean equals(@CheckForNull Object obj);
}
