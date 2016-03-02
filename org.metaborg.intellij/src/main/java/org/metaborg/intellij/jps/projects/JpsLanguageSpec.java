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

package org.metaborg.intellij.jps.projects;

import com.google.inject.*;
import com.google.inject.assistedinject.*;
import org.apache.commons.vfs2.*;
import org.jetbrains.jps.model.*;
import org.jetbrains.jps.model.module.*;
import org.metaborg.core.project.*;
import org.metaborg.meta.core.config.*;
import org.metaborg.meta.core.project.*;
import org.metaborg.spoofax.meta.core.config.*;
import org.metaborg.spoofax.meta.core.project.*;

import java.util.*;

/**
 * A Spoofax project used in JPS.
 */
public final class JpsLanguageSpec extends MetaborgJpsProject implements ISpoofaxLanguageSpec {

    private final ISpoofaxLanguageSpecConfig config;
    private final ISpoofaxLanguageSpecPaths paths;

    /**
     * Initializes a new instance of the {@link JpsLanguageSpec} class.
     *
     * @param location
     *            The location of the project root.
     */
    @Inject
    public JpsLanguageSpec(@Assisted final JpsModule module,
                           @Assisted final FileObject location,
                           @Assisted final ISpoofaxLanguageSpecConfig config,
                           @Assisted final ISpoofaxLanguageSpecPaths paths) {
        super(module, location, config);
        this.config = config;
        this.paths = paths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISpoofaxLanguageSpecConfig config() {
        return this.config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISpoofaxLanguageSpecPaths paths() {
        return this.paths;
    }
}
