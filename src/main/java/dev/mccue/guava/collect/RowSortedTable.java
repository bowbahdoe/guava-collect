/*
 * Copyright (C) 2010 The Guava Authors
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

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Interface that extends {@code Table} and whose rows are sorted.
 *
 * <p>The {@code #rowKeySet} method returns a {@code SortedSet} and the {@code #rowMap} method
 * returns a {@code SortedMap}, instead of the {@code Set} and {@code Map} specified by the {@code
 * Table} interface.
 *
 * @author Warren Dukes
 * @since 8.0
 */
@ElementTypesAreNonnullByDefault
public interface RowSortedTable<
        R extends @Nullable Object, C extends @Nullable Object, V extends @Nullable Object>
    extends Table<R, C, V> {
  /**
   * {@inheritDoc}
   *
   * <p>This method returns a {@code SortedSet}, instead of the {@code Set} specified in the {@code
   * Table} interface.
   */
  @Override
  SortedSet<R> rowKeySet();

  /**
   * {@inheritDoc}
   *
   * <p>This method returns a {@code SortedMap}, instead of the {@code Map} specified in the {@code
   * Table} interface.
   */
  @Override
  SortedMap<R, Map<C, V>> rowMap();
}
