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

package org.metaborg.intellij.jps.builders;

import com.google.inject.*;
import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.incremental.*;
import org.metaborg.intellij.jps.projects.*;
import org.metaborg.intellij.logging.*;
import org.metaborg.spoofax.meta.core.build.*;
import org.metaborg.spoofax.meta.core.project.*;
import org.metaborg.util.log.*;

/**
 * Builder executed before Java compilation.
 */
@Singleton
public final class SpoofaxPreBuilder extends MetaborgMetaBuilder2<SpoofaxPreTarget> {

    @InjectLogger
    private ILogger logger;

    /**
     * Initializes a new instance of the {@link SpoofaxPreBuilder} class.
     */
    @Inject
    public SpoofaxPreBuilder(
            final SpoofaxPreTargetType targetType,
            final IJpsProjectService projectService,
            final ISpoofaxLanguageSpecService languageSpecService,
            final JpsSpoofaxMetaBuilder jpsSpoofaxMetaBuilder) {
        super(targetType, jpsSpoofaxMetaBuilder, projectService, languageSpecService);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getPresentableName() {
        return "Spoofax pre-Java builder";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void doBuild(
            final LanguageSpecBuildInput metaInput,
            final SpoofaxPreTarget target,
            final DirtyFilesHolder<SpoofaxSourceRootDescriptor, SpoofaxPreTarget> holder,
            final BuildOutputConsumer consumer,
            final CompileContext context) throws Exception {

        this.jpsSpoofaxMetaBuilder.beforeBuild(metaInput, context);
        this.jpsSpoofaxMetaBuilder.clean(metaInput, context);
        this.jpsSpoofaxMetaBuilder.initialize(metaInput, context);
        this.jpsSpoofaxMetaBuilder.generateSources(metaInput, context);
        this.jpsSpoofaxMetaBuilder.regularBuild(metaInput, context);
        this.jpsSpoofaxMetaBuilder.compilePreJava(metaInput, context);
    }

}