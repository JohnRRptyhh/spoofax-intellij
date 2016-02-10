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

package org.metaborg.intellij.idea.filetypes;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.*;

/**
 * Consumer for file types implementing the {@link IMetaborgFileType} interface.
 */
public final class MetaborgFileTypeConsumer {

    private final FileTypeConsumer consumer;

    /**
     * Initializes a new instance of the {@link MetaborgFileTypeConsumer} class.
     *
     * @param consumer The file type consumer to wrap.
     */
    public MetaborgFileTypeConsumer(final FileTypeConsumer consumer) {
        Preconditions.checkNotNull(consumer);

        this.consumer = consumer;
    }

    /**
     * Consumes the file type with its default extensions.
     *
     * @param fileType The file type.
     */
    public void consume(final IMetaborgFileType fileType) {
        Preconditions.checkNotNull(fileType);

        consume(fileType, Iterables.toArray(fileType.getExtensions(), String.class));
    }

    /**
     * Consumes the file type with only the specified extensions.
     *
     * @param fileType   The file type.
     * @param extensions The file extensions, without a leading '.'.
     */
    public void consume(final FileType fileType, @NonNls final String... extensions) {
        Preconditions.checkNotNull(fileType);
        Preconditions.checkNotNull(extensions);

        final FileNameMatcher[] matchers = new FileNameMatcher[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            matchers[i] = new ExtensionFileNameMatcher(extensions[i]);
        }
        consume(fileType, matchers);
    }

    /**
     * Consumes the file type with only the specified file name matchers.
     *
     * @param fileType The file type.
     * @param matchers The file name matchers.
     */
    public void consume(final FileType fileType, final FileNameMatcher... matchers) {
        Preconditions.checkNotNull(fileType);
        Preconditions.checkNotNull(matchers);

        this.consumer.consume(fileType, matchers);
    }

}
