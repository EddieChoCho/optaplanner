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

package org.optaplanner.core.impl.domain.variable.nextprev;

import java.util.List;

import org.optaplanner.core.api.domain.variable.ListVariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.domain.variable.descriptor.ListVariableDescriptor;
import org.optaplanner.core.impl.score.director.InnerScoreDirector;

public class PreviousElementVariableListener<Solution_> implements ListVariableListener<Solution_, Object, Object> {

    protected final PreviousElementShadowVariableDescriptor<Solution_> shadowVariableDescriptor;
    protected final ListVariableDescriptor<Solution_> sourceVariableDescriptor;

    public PreviousElementVariableListener(
            PreviousElementShadowVariableDescriptor<Solution_> shadowVariableDescriptor,
            ListVariableDescriptor<Solution_> sourceVariableDescriptor) {
        this.shadowVariableDescriptor = shadowVariableDescriptor;
        this.sourceVariableDescriptor = sourceVariableDescriptor;
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<Solution_> scoreDirector, Object entity) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(ScoreDirector<Solution_> scoreDirector, Object entity) {
        InnerScoreDirector<Solution_, ?> innerScoreDirector = (InnerScoreDirector<Solution_, ?>) scoreDirector;
        List<Object> listVariable = sourceVariableDescriptor.getListVariable(entity);
        for (int i = 1; i < listVariable.size(); i++) {
            Object element = listVariable.get(i);
            Object previous = listVariable.get(i - 1);
            innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
            shadowVariableDescriptor.setValue(element, previous);
            innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
        }
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<Solution_> scoreDirector, Object entity) {
        // Do nothing
    }

    @Override
    public void afterEntityRemoved(ScoreDirector<Solution_> scoreDirector, Object entity) {
        InnerScoreDirector<Solution_, ?> innerScoreDirector = (InnerScoreDirector<Solution_, ?>) scoreDirector;
        List<Object> listVariable = sourceVariableDescriptor.getListVariable(entity);
        for (int i = 1; i < listVariable.size(); i++) {
            Object element = listVariable.get(i);
            innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
            shadowVariableDescriptor.setValue(element, null);
            innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
        }
    }

    @Override
    public void afterListVariableElementUnassigned(ScoreDirector<Solution_> scoreDirector, Object element) {
        InnerScoreDirector<Solution_, ?> innerScoreDirector = (InnerScoreDirector<Solution_, ?>) scoreDirector;
        if (shadowVariableDescriptor.getValue(element) != null) {
            innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
            shadowVariableDescriptor.setValue(element, null);
            innerScoreDirector.afterVariableChanged(shadowVariableDescriptor, element);
        }
    }

    @Override
    public void beforeListVariableChanged(ScoreDirector<Solution_> scoreDirector, Object entity, int fromIndex, int toIndex) {
        // Do nothing
    }

    @Override
    public void afterListVariableChanged(ScoreDirector<Solution_> scoreDirector, Object entity, int fromIndex, int toIndex) {
        InnerScoreDirector<Solution_, ?> innerScoreDirector = (InnerScoreDirector<Solution_, ?>) scoreDirector;
        List<Object> listVariable = sourceVariableDescriptor.getListVariable(entity);
        Object previous = fromIndex > 0 ? listVariable.get(fromIndex - 1) : null;
        for (int i = fromIndex; i <= toIndex && i < listVariable.size(); i++) {
            Object element = listVariable.get(i);
            if (previous != shadowVariableDescriptor.getValue(element)) {
                innerScoreDirector.beforeVariableChanged(shadowVariableDescriptor, element);
                shadowVariableDescriptor.setValue(element, previous);
                innerScoreDirector.afterVariableChanged(shadowVariableDescriptor, element);
            }
            previous = element;
        }
    }
}
