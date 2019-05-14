/*
 * Copyright (c) 2019 envimate GmbH - https://envimate.com/.
 *
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

package com.envimate.mapmate.deserialization.specs.givenwhenthen;

import com.envimate.mapmate.deserialization.Deserializer;
import com.envimate.mapmate.domain.valid.AComplexType;
import com.envimate.mapmate.domain.valid.ANumber;
import com.envimate.mapmate.domain.valid.AString;
import com.envimate.mapmate.domain.valid.AnException;
import com.envimate.mapmate.deserialization.validation.ValidationError;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static com.envimate.mapmate.deserialization.Deserializer.aDeserializer;
import static com.envimate.mapmate.deserialization.specs.givenwhenthen.Unmarshallers.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Given {
    private final Deserializer deserializer;

    public static Given givenTheExampleMapMateDeserializer() {
        final Deserializer deserializer = aDeserializer()
                .withJsonUnmarshaller(jsonUnmarshaller())
                .withXmlUnmarshaller(xmlUnmarshaller())
                .withYamlUnmarshaller(yamlUnmarshaller())
                .withDataTransferObject(AComplexType.class)
                .deserializedUsingTheSingleFactoryMethod()
                .withCustomPrimitive(AString.class)
                .deserializedUsingTheMethodNamed("fromString")
                .withCustomPrimitive(ANumber.class)
                .deserializedUsingTheMethodNamed("fromString")
                .mappingExceptionUsing(AnException.class, ValidationError::fromExceptionMessageAndPropertyPath)
                .build();
        return new Given(deserializer);
    }

    public When when() {
        return new When(this.deserializer);
    }
}
