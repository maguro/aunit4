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
package com.toolazydogs.aunit.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.model.Statement;

import com.toolazydogs.aunit.CompositeOption;
import com.toolazydogs.aunit.Option;


/**
 *
 */
public class DefaultCompositeOption implements CompositeOption
{
    /**
     * Composite options (cannot be null).
     */
    private final List<Option> options = new ArrayList<Option>();

    /**
     * Constructor.
     *
     * @param options composite options (can be null or no option specified)
     */
    public DefaultCompositeOption(final Option... options)
    {
        add(options);
    }

    /**
     * Constructor.
     */
    public DefaultCompositeOption()
    {
    }

    /**
     * {@inheritDoc}
     */
    public Option[] getOptions()
    {
        return OptionUtils.expand(options.toArray(new Option[options.size()]));
    }

    /**
     * Adds options.
     *
     * @param options composite options to be added (can be null or no options specified)
     * @return itself, for fluent api usage
     */
    public DefaultCompositeOption add(final Option... options)
    {
        if (options != null)
        {
            this.options.addAll(Arrays.asList(options));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("DefaultCompositeOption");
        sb.append("{options=").append(options);
        sb.append('}');
        return sb.toString();
    }

    public Statement generateSetupStatement()
    {
        return new Statement()
        {
            final List<Statement> statements = new ArrayList<Statement>();

            {
                for (Option option : options) statements.add(option.generateSetupStatement());
            }

            @Override
            public void evaluate() throws Throwable
            {
                for (Statement statement : statements) statement.evaluate();
            }
        };
    }

    public Statement generateTeardownStatement()
    {
        return new Statement()
        {
            final List<Statement> statements = new ArrayList<Statement>();

            {
                for (Option option : options) statements.add(option.generateTeardownStatement());
            }

            @Override
            public void evaluate() throws Throwable
            {
                for (Statement statement : statements) statement.evaluate();
            }
        };
    }
}
