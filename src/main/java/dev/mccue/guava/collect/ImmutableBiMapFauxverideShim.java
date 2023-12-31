/*
 * Copyright (C) 2015 The Guava Authors
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
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * "Overrides" the {@code ImmutableMap} static methods that lack {@code ImmutableBiMap} equivalents
 * with deprecated, exception-throwing versions. See {@code ImmutableSortedSetFauxverideShim} for
 * details.
 *
 * @author Louis Wasserman
 */
@ElementTypesAreNonnullByDefault
abstract class ImmutableBiMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
  /**
   * Not supported. Use {@code ImmutableBiMap#toImmutableBiMap} instead. This method exists only to
   * hide {@code ImmutableMap#toImmutableMap(Function, Function)} from consumers of {@code
   * ImmutableBiMap}.
   *
   * @throws UnsupportedOperationException always
   * @deprecated Use {@code ImmutableBiMap#toImmutableBiMap}.
   */
  @Deprecated
  @DoNotCall("Use toImmutableBiMap")
  public static <T extends @Nullable Object, K, V>
      Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
          Function<? super T, ? extends K> keyFunction,
          Function<? super T, ? extends V> valueFunction) {
    throw new UnsupportedOperationException();
  }

  /**
   * Not supported. This method does not make sense for {@code BiMap}. This method exists only to
   * hide {@code ImmutableMap#toImmutableMap(Function, Function, BinaryOperator)} from consumers of
   * {@code ImmutableBiMap}.
   *
   * @throws UnsupportedOperationException always
   * @deprecated
   */
  @Deprecated
  @DoNotCall("Use toImmutableBiMap")
  public static <T extends @Nullable Object, K, V>
      Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
          Function<? super T, ? extends K> keyFunction,
          Function<? super T, ? extends V> valueFunction,
          BinaryOperator<V> mergeFunction) {
    throw new UnsupportedOperationException();
  }
}
