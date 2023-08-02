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

import java.io.Serializable;
import java.util.List;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An ordering that treats all references as equals, even nulls.
 *
 * @author Emily Soldal
 */

@ElementTypesAreNonnullByDefault
final class AllEqualOrdering extends Ordering<@Nullable Object> implements Serializable {
  static final AllEqualOrdering INSTANCE = new AllEqualOrdering();

  @Override
  public int compare(@CheckForNull Object left, @CheckForNull Object right) {
    return 0;
  }

  @Override
  public <E extends @Nullable Object> List<E> sortedCopy(Iterable<E> iterable) {
    return Lists.newArrayList(iterable);
  }

  @Override
  public <E> ImmutableList<E> immutableSortedCopy(Iterable<E> iterable) {
    return ImmutableList.copyOf(iterable);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <S extends @Nullable Object> Ordering<S> reverse() {
    return (Ordering<S>) this;
  }

  private Object readResolve() {
    return INSTANCE;
  }

  @Override
  public String toString() {
    return "Ordering.allEqual()";
  }

  private static final long serialVersionUID = 0;
}
