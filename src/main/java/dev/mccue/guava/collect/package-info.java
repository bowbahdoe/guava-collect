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

/**
 * Collection interfaces and implementations, and other utilities for collections. This package is a
 * part of the open-source <a href="https://github.com/google/guava">Guava</a> library.
 *
 * <p>The classes in this package include:
 *
 * <h2>Immutable collections</h2>
 *
 * These are collections whose contents will never change. They also offer a few additional
 * guarantees (see {@code ImmutableCollection} for details). Implementations are available for both
 * the JDK collection types and the Guava collection types (listed below).
 *
 * <h2>Collection types</h2>
 *
 * <dl>
 *   <dt>{@code Multimap}
 *   <dd>A new type, which is similar to {@code java.util.Map}, but may contain multiple entries
 *       with the same key. Some behaviors of {@code Multimap} are left unspecified and are provided
 *       only by the subtypes mentioned below.
 *   <dt>{@code ListMultimap}
 *   <dd>An extension of {@code Multimap} which permits duplicate entries, supports random access of
 *       values for a particular key, and has <i>partially order-dependent equality</i> as defined
 *       by {@code ListMultimap#equals(Object)}. {@code ListMultimap} takes its name from the fact
 *       that the {@code ListMultimap#get collection of values} associated with a given key
 *       fulfills the {@code java.util.List} contract.
 *   <dt>{@code SetMultimap}
 *   <dd>An extension of {@code Multimap} which has order-independent equality and does not allow
 *       duplicate entries; that is, while a key may appear twice in a {@code SetMultimap}, each
 *       must map to a different value. {@code SetMultimap} takes its name from the fact that the
 *       {@code SetMultimap#get collection of values} associated with a given key fulfills the
 *       {@code java.util.Set} contract.
 *   <dt>{@code SortedSetMultimap}
 *   <dd>An extension of {@code SetMultimap} for which the {@code SortedSetMultimap#get
 *       collection values} associated with a given key is a {@code java.util.SortedSet}.
 *   <dt>{@code BiMap}
 *   <dd>An extension of {@code java.util.Map} that guarantees the uniqueness of its values as well
 *       as that of its keys. This is sometimes called an "invertible map," since the restriction on
 *       values enables it to support an {@code BiMap#inverse inverse view} -- which is another
 *       instance of {@code BiMap}.
 *   <dt>{@code Table}
 *   <dd>A new type, which is similar to {@code java.util.Map}, but which indexes its values by an
 *       ordered pair of keys, a row key and column key.
 *   <dt>{@code Multiset}
 *   <dd>An extension of {@code java.util.Collection} that may contain duplicate values like a
 *       {@code java.util.List}, yet has order-independent equality like a {@code java.util.Set}.
 *       One typical use for a multiset is to represent a histogram.
 *   <dt>{@code ClassToInstanceMap}
 *   <dd>An extension of {@code java.util.Map} that associates a raw type with an instance of that
 *       type.
 * </dl>
 *
 * <h2>Ranges</h2>
 *
 * <ul>
 *   <li>{@code Range}
 *   <li>{@code RangeMap}
 *   <li>{@code RangeSet}
 *   <li>{@code DiscreteDomain}
 *   <li>{@code ContiguousSet}
 * </ul>
 *
 * <h2>Classes of static utility methods</h2>
 *
 * <ul>
 *   <li>{@code Collections2}
 *   <li>{@code Comparators}
 *   <li>{@code Iterables}
 *   <li>{@code Iterators}
 *   <li>{@code Lists}
 *   <li>{@code Maps}
 *   <li>{@code MoreCollectors}
 *   <li>{@code Multimaps}
 *   <li>{@code Multisets}
 *   <li>{@code ObjectArrays}
 *   <li>{@code Queues}
 *   <li>{@code Sets}
 *   <li>{@code Streams}
 *   <li>{@code Tables}
 * </ul>
 *
 * <h2>Abstract implementations</h2>
 *
 * <ul>
 *   <li>{@code AbstractIterator}
 *   <li>{@code AbstractSequentialIterator}
 *   <li>{@code UnmodifiableIterator}
 *   <li>{@code UnmodifiableListIterator}
 * </ul>
 *
 * <h2>Forwarding collections</h2>
 *
 * We provide implementations of collections that forward all method calls to a delegate collection
 * by default. Subclasses can override one or more methods to implement the decorator pattern. For
 * an example, see {@code ForwardingCollection}.
 *
 * <h2>Other</h2>
 *
 * <ul>
 *   <li>{@code EvictingQueue}
 *   <li>{@code Interner}, {@code Interners}
 *   <li>{@code MapMaker}
 *   <li>{@code MinMaxPriorityQueue}
 *   <li>{@code PeekingIterator}
 * </ul>
 */
@CheckReturnValue
@ParametersAreNonnullByDefault
package dev.mccue.guava.collect;

import com.google.errorprone.annotations.CheckReturnValue;
import dev.mccue.jsr305.ParametersAreNonnullByDefault;
