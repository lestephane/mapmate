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

package com.envimate.mapmate.builder.models.annotated;

import com.envimate.mapmate.builder.conventional.customprimitives.methodannotation.MapMatePrimitiveDeserializer;
import com.envimate.mapmate.builder.conventional.customprimitives.methodannotation.MapMatePrimitiveSerializer;
import com.envimate.mapmate.builder.validation.EmailAddressValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailAddress {
    private final String value;

    @MapMatePrimitiveDeserializer
    public static EmailAddress emailAddress(final String value) {
        final String validated = EmailAddressValidator.ensureEmailAddress(value, "emailAddress");
        return new EmailAddress(validated);
    }

    @MapMatePrimitiveSerializer
    public String value() {
        return this.value;
    }
}
