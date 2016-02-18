/*
 * Copyright © 2015-2016
 *
 * This file is part of Spoofax for IntelliJ.
 *
 * Spoofax for IntelliJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoofax for IntelliJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Spoofax for IntelliJ.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.metaborg.intellij.injections;

import com.google.inject.*;
import com.intellij.openapi.application.*;
import org.metaborg.intellij.logging.*;
import org.metaborg.intellij.logging.LoggerUtils;
import org.metaborg.util.log.*;

import javax.annotation.*;

/**
 * Provides an instance from the IntelliJ component manager.
 */
/* package private */ final class IntelliJComponentProvider<T> implements Provider<T> {

    @InjectLogger
    private ILogger logger;
    private final Class<T> service;

    /**
     * Initializes a new instance of the {@link IntelliJComponentProvider} class.
     *
     * @param service The class of the component to load.
     */
    public IntelliJComponentProvider(final Class<T> service) {
        this.service = service;
    }

    @Override
    public T get() {
        @Nullable final T component = ApplicationManager.getApplication().getComponent(this.service);
        if (component == null) {
            throw LoggerUtils.exception(this.logger, ProvisionException.class,
                    "No implementations are registered for the class {}.",
                    this.service
            );
        }
        return component;
    }
}
