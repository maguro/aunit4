/**
 *
 * Copyright 2010-2011 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toolazydogs.aunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation specifying to which method names the configuration applies. The value of configuration is an array of
 * regular expressions that are matched against test method names (those methods marked with {@link org.junit.Test}).
 * A configuration method can match multiple test methods, case when the options will be merged.
 *
 * @author Alin Dreghiciu (adreghiciu@gmail.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AppliesTo
{
    /**
     * Array of regular expressions that are matched against test methods names.
     * By default (value not specified) matches all test methods (".*").
     *
     * @return array of regular expressions
     */
    public abstract String[] value() default {".*"};
}
