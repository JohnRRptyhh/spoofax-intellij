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

import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.incremental.*;
import org.jetbrains.jps.model.module.*;
import org.metaborg.core.project.*;
import org.metaborg.intellij.jps.projects.*;
import org.metaborg.intellij.logging.*;
import org.metaborg.intellij.logging.LoggerUtils;
import org.metaborg.spoofax.core.project.*;
import org.metaborg.spoofax.core.project.configuration.*;
import org.metaborg.spoofax.meta.core.*;
import org.metaborg.util.log.*;

import javax.annotation.*;
import java.io.*;
import java.util.*;

/**
 * Metaborg meta-builder.
 */
public abstract class MetaborgMetaBuilder2<T extends SpoofaxTarget> extends TargetBuilder<SpoofaxSourceRootDescriptor, T> {

    protected final IJpsProjectService projectService;
    protected final ILanguageSpecService languageSpecService;
    protected final ISpoofaxLanguageSpecPathsService pathsService;
    protected final ISpoofaxLanguageSpecConfigService spoofaxLanguageSpecConfigService;
    @InjectLogger
    private ILogger logger;

    /**
     * Gets the presentable name of the builder.
     *
     * @return The name.
     */
    @Override
    public abstract String getPresentableName();

    /**
     * Initializes a new instance of the {@link MetaborgMetaBuilder2} class.
     *
     * @param targetType The target type.
     */
    protected MetaborgMetaBuilder2(
            final BuildTargetType<T> targetType,
            final IJpsProjectService projectService,
            final ILanguageSpecService languageSpecService,
            final ISpoofaxLanguageSpecPathsService pathsService,
            final ISpoofaxLanguageSpecConfigService spoofaxLanguageSpecConfigService) {
        super(Collections.singletonList(targetType));
        this.projectService = projectService;
        this.languageSpecService = languageSpecService;
        this.pathsService = pathsService;
        this.spoofaxLanguageSpecConfigService = spoofaxLanguageSpecConfigService;
    }

    /**
     * Builds the build target.
     *
     * @param target   The build target.
     * @param holder   The dirty files holder.
     * @param consumer The build output consumer.
     * @param context  The compile context.
     * @throws ProjectBuildException
     * @throws IOException
     */
    @Override
    public final void build(
            final T target,
            final DirtyFilesHolder<SpoofaxSourceRootDescriptor, T> holder,
            final BuildOutputConsumer consumer,
            final CompileContext context) throws ProjectBuildException, IOException {

        try {
            final LanguageSpecBuildInput metaInput = getLanguageSpecBuildInput(target.getModule());

            doBuild(metaInput, target, holder, consumer, context);

        } catch (final ProjectBuildException e) {
            this.logger.error("An unexpected project build exception occurred.", e);
            throw e;
        } catch (final ProjectException e) {
            this.logger.error("An unexpected project exception occurred.", e);
            throw new ProjectBuildException(e);
        } catch (final Exception e) {
            this.logger.error("An unexpected exception occurred.", e);
            throw new ProjectBuildException(e);
        }
    }

    /**
     * Executes the build.
     *
     * @throws ProjectBuildException
     * @throws IOException
     */
    public abstract void doBuild(
            final LanguageSpecBuildInput metaInput,
            final T target,
            final DirtyFilesHolder<SpoofaxSourceRootDescriptor, T> holder,
            final BuildOutputConsumer consumer,
            final CompileContext context) throws Exception;


    /**
     * Gets the build input.
     *
     * @param module The JPS module.
     * @return The build input.
     * @throws ProjectBuildException
     * @throws IOException
     */
    protected LanguageSpecBuildInput getLanguageSpecBuildInput(final JpsModule module)
            throws ProjectBuildException, IOException {

        @Nullable final MetaborgJpsProject project = this.projectService.get(module);
        if (project == null) {
            throw LoggerUtils.exception(this.logger, ProjectBuildException.class,
                    "Could not get a project for the module {}", module);
        }

        @Nullable final ILanguageSpec languageSpec = this.languageSpecService.get(project);
        if (languageSpec == null) {
            throw LoggerUtils.exception(this.logger, ProjectBuildException.class,
                    "Could not get a language specification for the project {}", project);
        }

        @Nullable final ISpoofaxLanguageSpecConfig config = this.spoofaxLanguageSpecConfigService.get(languageSpec);
        if (config == null) {
            throw LoggerUtils.exception(this.logger, ProjectBuildException.class,
                    "Could not get a configuration for language specification {}", languageSpec);
        }

        final ISpoofaxLanguageSpecPaths paths = this.pathsService.get(languageSpec);
        return new LanguageSpecBuildInput(languageSpec, config, paths);
    }

}