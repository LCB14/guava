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

/** Tests for an undirected {@link ConfigurableMutableGraph}. */
@AndroidIncompatible
@RunWith(Parameterized.class)
public class StandardMutableUndirectedGraphTest extends AbstractStandardUndirectedGraphTest {

  @Parameters(name = "allowsSelfLoops={0}")
  public static Collection<Object[]> parameters() {
    return Arrays.asList(
        new Object[][] {
          {false}, {true},
        });
  }

  private final boolean allowsSelfLoops;

  public StandardMutableUndirectedGraphTest(boolean allowsSelfLoops) {
    this.allowsSelfLoops = allowsSelfLoops;
  }

  @Override
  boolean allowsSelfLoops() {
    return allowsSelfLoops;
  }

  @Override
  public MutableGraph<Integer> createGraph() {
    return GraphBuilder.undirected().allowsSelfLoops(allowsSelfLoops()).build();
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
