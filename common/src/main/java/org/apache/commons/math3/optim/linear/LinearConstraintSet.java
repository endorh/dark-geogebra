/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.optim.linear;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.math3.optim.OptimizationData;

/**
 * Class that represents a set of {@link LinearConstraint linear constraints}.
 *
 * @since 3.1
 */
public class LinearConstraintSet implements OptimizationData {
    /** Set of constraints. */
    private final Set<LinearConstraint> linearConstraints = new LinkedHashSet<>();

    /**
     * Creates a set containing the given constraints.
     *
     * @param constraints Constraints.
     */
    public LinearConstraintSet(LinearConstraint... constraints) {
	    linearConstraints.addAll(Arrays.asList(constraints));
    }

    /**
     * Creates a set containing the given constraints.
     *
     * @param constraints Constraints.
     */
    public LinearConstraintSet(Collection<LinearConstraint> constraints) {
        linearConstraints.addAll(constraints);
    }

    /**
     * Gets the set of linear constraints.
     *
     * @return the constraints.
     */
    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableSet(linearConstraints);
    }
}
