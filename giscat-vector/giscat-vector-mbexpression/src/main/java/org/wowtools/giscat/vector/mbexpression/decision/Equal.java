/*****************************************************************
 *  Copyright (c) 2022- "giscat by 刘雨 (https://github.com/codingmiao/giscat)"
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.wowtools.giscat.vector.mbexpression.decision;

import org.wowtools.giscat.vector.mbexpression.Expression;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * ==
 * <p>
 * 参见 https://docs.mapbox.com/mapbox-gl-js/style-spec/expressions/#==
 * <p>
 * Syntax
 * ["==", value, value]: boolean
 * ["==", value, value, collator]: boolean
 *
 * @author liuyu
 * @date 2022/7/15
 */
public class Equal extends Expression<Boolean> {
    protected Equal(ArrayList expressionArray) {
        super(expressionArray);
        if (expressionArray.size() == 4) {
            throw new UnsupportedOperationException("collator参数暂未实现");
        }
    }

    @Override
    public String getExpressionName() {
        return "==";
    }

    @Override
    public Boolean getValue(Map<String, Object> featureProperties) {
        Object o1 = expressionArray.get(1);
        if (o1 instanceof Expression) {
            Expression expression = (Expression) o1;
            o1 = expression.getValue(featureProperties);
        }
        Object o2 = expressionArray.get(2);
        if (o2 instanceof Expression) {
            Expression expression = (Expression) o2;
            o2 = expression.getValue(featureProperties);
        }
        return Objects.equals(o1, o2);
    }

}