/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.optaplanner.core.impl.phase.event;

import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.impl.phase.scope.AbstractPhaseScope;
import org.optaplanner.core.impl.phase.scope.AbstractStepScope;
import org.optaplanner.core.impl.solver.event.SolverLifecycleListenerAdapter;

/**
 * An adapter for {@link PhaseLifecycleListener}.
 *
 * @param <Solution_> the solution type, the class with the {@link PlanningSolution} annotation
 */
public abstract class PhaseLifecycleListenerAdapter<Solution_> extends SolverLifecycleListenerAdapter<Solution_>
        implements PhaseLifecycleListener<Solution_> {

    @Override
    public void phaseStarted(AbstractPhaseScope<Solution_> phaseScope) {
        // Hook method
    }

    @Override
    public void stepStarted(AbstractStepScope<Solution_> stepScope) {
        // Hook method
    }

    @Override
    public void stepEnded(AbstractStepScope<Solution_> stepScope) {
        // Hook method
    }

    @Override
    public void phaseEnded(AbstractPhaseScope<Solution_> phaseScope) {
        // Hook method
    }

}
