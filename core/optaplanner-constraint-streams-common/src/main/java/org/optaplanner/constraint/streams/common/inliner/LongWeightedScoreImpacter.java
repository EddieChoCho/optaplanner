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

package org.optaplanner.constraint.streams.common.inliner;

import java.math.BigDecimal;
import java.util.Objects;

import org.optaplanner.core.api.score.Score;

final class LongWeightedScoreImpacter<Score_ extends Score<Score_>, Context_ extends ScoreContext<Score_>>
        implements WeightedScoreImpacter<Score_, Context_> {

    private final LongImpactFunction<Score_, Context_> impactFunction;
    private final Context_ context;

    public LongWeightedScoreImpacter(LongImpactFunction<Score_, Context_> impactFunction, Context_ context) {
        this.impactFunction = Objects.requireNonNull(impactFunction);
        this.context = context;
    }

    @Override
    public UndoScoreImpacter impactScore(int matchWeight, JustificationsSupplier justificationsSupplier) {
        return impactFunction.impact(context, matchWeight, justificationsSupplier); // int can be cast to long
    }

    @Override
    public UndoScoreImpacter impactScore(long matchWeight, JustificationsSupplier justificationsSupplier) {
        return impactFunction.impact(context, matchWeight, justificationsSupplier);
    }

    @Override
    public UndoScoreImpacter impactScore(BigDecimal matchWeight, JustificationsSupplier justificationsSupplier) {
        throw new UnsupportedOperationException("Impossible state: passing BigDecimal into a long impacter.");
    }

    @Override
    public Context_ getContext() {
        return context;
    }

}
