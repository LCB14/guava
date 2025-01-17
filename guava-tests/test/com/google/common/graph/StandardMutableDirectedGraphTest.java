/*
 * Copyright (C) 2014 The Guava Authors
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

package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** Tests for a directed {@link ConfigurableMutableGraph}. */
@AndroidIncompatible
@RunWith(Parameterized.class)
public final class StandardMutableDirectedGraphTest extends AbstractStandardDirectedGraphTest {

  @Parameters(name = "allowsSelfLoops={0}, incidentEdgeOrder={1}")
  public static Collection<Object[]> parameters() {
    return Arrays.asList(
        new Object[][] {
          {false, ElementOrder.unordered()},
          {true, ElementOrder.unordered()},
          {false, ElementOrder.stable()},
          {true, ElementOrder.stable()},
        });
  }

  private final boolean allowsSelfLoops;
  private final ElementOrder<Integer> incidentEdgeOrder;

  public StandardMutableDirectedGraphTest(
      boolean allowsSelfLoops, ElementOrder<Integer> incidentEdgeOrder) {
    this.allowsSelfLoops = allowsSelfLoops;
    this.incidentEdgeOrder = incidentEdgeOrder;
  }

  @Override
  boolean allowsSelfLoops() {
    return allowsSelfLoops;
  }

  @Override
  ElementOrder<Integer> incidentEdgeOrder() {
    return incidentEdgeOrder;
  }

  @Override
  public MutableGraph<Integer> createGraph() {
    return GraphBuilder.directed()
        .allowsSelfLoops(allowsSelfLoops())
        .incidentEdgeOrder(incidentEdgeOrder)
        .build();
  }

  @CanIgnoreReturnValue
  @Override
  final boolean addNode(Integer n) {
    return graphAsMutableGraph.addNode(n);
  }

  @CanIgnoreReturnValue
  @Override
  final boolean putEdge(Integer n1, Integer n2) {
    return graphAsMutableGraph.putEdge(n1, n2);
  }
}
