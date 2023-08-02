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
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@code SetMultimap} whose set of values for a given key are kept sorted; that is, they comprise
 * a {@code SortedSet}. It cannot hold duplicate key-value pairs; adding a key-value pair that's
 * already in the multimap has no effect. This interface does not specify the ordering of the
 * multimap's keys. See the {@code Multimap} documentation for information common to all multimaps.
 *
 * <p>The {@code #get}, {@code #removeAll}, and {@code #replaceValues} methods each return a {@code
 * SortedSet} of values, while {@code Multimap#entries()} returns a {@code Set} of map entries.
 * Though the method signature doesn't say so explicitly, the map returned by {@code #asMap} has
 * {@code SortedSet} values.
 *
 * <p><b>Warning:</b> As in all {@code SetMultimap}s, do not modify either a key <i>or a value</i>
 * of a {@code SortedSetMultimap} in a way that affects its {@code Object#equals} behavior (or its
 * position in the order of the values). Undefined behavior and bugs will result.
 *
 * <p>See the Guava User Guide article on <a href=
 * "https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap">{@code Multimap}</a>.
 *
 * @author Jared Levy
 * @since 2.0
 */
@ElementTypesAreNonnullByDefault
public interface SortedSetMultimap<K extends @Nullable Object, V extends @Nullable Object>
    extends SetMultimap<K, V> {
  // Following Javadoc copied from Multimap.

  /**
   * Returns a collection view of all values associated with a key. If no mappings in the multimap
   * have the provided key, an empty collection is returned.
   *
   * <p>Changes to the returned collection will update the underlying multimap, and vice versa.
   *
   * <p>Because a {@code SortedSetMultimap} has unique sorted values for a given key, this method
   * returns a {@code SortedSet}, instead of the {@code java.util.Collection} specified in the
   * {@code Multimap} interface.
   */
  @Override
  SortedSet<V> get(@ParametricNullness K key);

  /**
   * Removes all values associated with a given key.
   *
   * <p>Because a {@code SortedSetMultimap} has unique sorted values for a given key, this method
   * returns a {@code SortedSet}, instead of the {@code java.util.Collection} specified in the
   * {@code Multimap} interface.
   */
  @CanIgnoreReturnValue
  @Override
  SortedSet<V> removeAll(@CheckForNull Object key);

  /**
   * Stores a collection of values with the same key, replacing any existing values for that key.
   *
   * <p>Because a {@code SortedSetMultimap} has unique sorted values for a given key, this method
   * returns a {@code SortedSet}, instead of the {@code java.util.Collection} specified in the
   * {@code Multimap} interface.
   *
   * <p>Any duplicates in {@code values} will be stored in the multimap once.
   */
  @CanIgnoreReturnValue
  @Override
  SortedSet<V> replaceValues(@ParametricNullness K key, Iterable<? extends V> values);

  /**
   * Returns a map view that associates each key with the corresponding values in the multimap.
   * Changes to the returned map, such as element removal, will update the underlying multimap. The
   * map does not support {@code setValue()} on its entries, {@code put}, or {@code putAll}.
   *
   * <p>When passed a key that is present in the map, {@code asMap().get(Object)} has the same
   * behavior as {@code #get}, returning a live collection. When passed a key that is not present,
   * however, {@code asMap().get(Object)} returns {@code null} instead of an empty collection.
   *
   * <p><b>Note:</b> The returned map's values are guaranteed to be of type {@code SortedSet}. To
   * obtain this map with the more specific generic type {@code Map<K, SortedSet<V>>}, call {@code
   * Multimaps#asMap(SortedSetMultimap)} instead. <b>However</b>, the returned map <i>itself</i> is
   * not necessarily a {@code SortedMap}: A {@code SortedSetMultimap} must expose the <i>values</i>
   * for a given key in sorted order, but it need not expose the <i>keys</i> in sorted order.
   * Individual {@code SortedSetMultimap} implementations, like those built with {@code
   * MultimapBuilder#treeKeys()}, may make additional guarantees.
   */
  @Override
  Map<K, Collection<V>> asMap();

  /**
   * Returns the comparator that orders the multimap values, with {@code null} indicating that
   * natural ordering is used.
   */
  @CheckForNull
  Comparator<? super V> valueComparator();
}
