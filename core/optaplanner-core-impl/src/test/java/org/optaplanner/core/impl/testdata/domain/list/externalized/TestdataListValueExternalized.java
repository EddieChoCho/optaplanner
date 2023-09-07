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

package org.optaplanner.core.impl.testdata.domain.list.externalized;

import org.optaplanner.core.impl.testdata.domain.TestdataObject;

public class TestdataListValueExternalized extends TestdataObject {

    public TestdataListValueExternalized() {
    }

    public TestdataListValueExternalized(String code) {
        super(code);
    }

    @Override
    public boolean equals(Object obj) {
        // Pretend a bad equals() design that makes all values equal. This proves that external supplies must use
        // the IdentityHasMap to eliminate dependency on user domain implementation of equals().
        return obj != null && obj.getClass().equals(this.getClass());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
