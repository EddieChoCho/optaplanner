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

package org.optaplanner.examples.nurserostering.app;

import java.util.stream.Stream;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.ConstraintStreamImplType;
import org.optaplanner.examples.common.app.SolverSmokeTest;
import org.optaplanner.examples.nurserostering.domain.NurseRoster;

class NurseRosteringSmokeTest extends SolverSmokeTest<NurseRoster, HardSoftScore> {

    private static final String UNSOLVED_DATA_FILE = "data/nurserostering/unsolved/medium_late01_initialized.json";

    @Override
    protected NurseRosteringApp createCommonApp() {
        return new NurseRosteringApp();
    }

    @Override
    protected Stream<TestData<HardSoftScore>> testData() {
        return Stream.of(
                TestData.of(ConstraintStreamImplType.DROOLS, UNSOLVED_DATA_FILE,
                        HardSoftScore.ofSoft(-598),
                        HardSoftScore.ofSoft(-609)),
                TestData.of(ConstraintStreamImplType.BAVET, UNSOLVED_DATA_FILE,
                        HardSoftScore.ofSoft(-508),
                        HardSoftScore.ofSoft(-534)));
    }
}
