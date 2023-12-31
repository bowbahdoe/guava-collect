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

import static dev.mccue.guava.base.Preconditions.checkNotNull;

import dev.mccue.guava.base.Function;
import dev.mccue.guava.base.Objects;
import java.io.Serializable;
import dev.mccue.jsr305.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An ordering that orders elements by applying an order to the result of a function on those
 * elements.
 */

@ElementTypesAreNonnullByDefault
final class ByFunctionOrdering<F extends @Nullable Object, T extends @Nullable Object>
    extends Ordering<F> implements Serializable {
  final Function<F, ? extends T> function;
  final Ordering<T> ordering;

  ByFunctionOrdering(Function<F, ? extends T> function, Ordering<T> ordering) {
    this.function = checkNotNull(function);
    this.ordering = checkNotNull(ordering);
  }

  @Override
  public int compare(@ParametricNullness F left, @ParametricNullness F right) {
    return ordering.compare(function.apply(left), function.apply(right));
  }

  @Override
  public boolean equals(@CheckForNull Object object) {
    if (object == this) {
      return true;
    }
    if (object instanceof ByFunctionOrdering) {
      ByFunctionOrdering<?, ?> that = (ByFunctionOrdering<?, ?>) object;
      return this.function.equals(that.function) && this.ordering.equals(that.ordering);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(function, ordering);
  }

  @Override
  public String toString() {
    return ordering + ".onResultOf(" + function + ")";
  }

  private static final long serialVersionUID = 0;
}
