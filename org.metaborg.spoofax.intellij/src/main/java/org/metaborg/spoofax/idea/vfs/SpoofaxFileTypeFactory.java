/*
 * Copyright © 2015-2015
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

package org.metaborg.spoofax.idea.vfs;

import com.google.inject.Inject;
import com.intellij.ide.highlighter.ArchiveFileType;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.metaborg.idea.vfs.MetaborgFileTypeConsumer;
import org.metaborg.idea.vfs.MetaborgFileTypeFactory;
import org.metaborg.spoofax.intellij.idea.IdeaPlugin;

/**
 * Factory for language-agnostic file types.
 */
public final class SpoofaxFileTypeFactory extends MetaborgFileTypeFactory {

    private SpoofaxArtifactFileType artifactFileType;

    /**
     * This instance is created by IntelliJ's plugin system.
     * Do not call this method manually.
     */
    public SpoofaxFileTypeFactory() {
        IdeaPlugin.injector().injectMembers(this);
    }

    // TODO: Maybe multi-inject a Set and register them all?
    @Inject
    @SuppressWarnings("unused")
    private void inject(final SpoofaxArtifactFileType artifactFileType) {
        this.artifactFileType = artifactFileType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFileTypes(@NotNull final MetaborgFileTypeConsumer consumer) {
        consumer.consume(this.artifactFileType);
    }
}
