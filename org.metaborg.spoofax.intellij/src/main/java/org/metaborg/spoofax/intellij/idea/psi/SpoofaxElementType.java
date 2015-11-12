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

package org.metaborg.spoofax.intellij.idea.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.metaborg.spoofax.intellij.idea.languages.SpoofaxIdeaLanguage;

/**
 * A Spoofax element type.
 */
public class SpoofaxElementType extends IElementType {

    /**
     * Initializes a new instance of the {@link SpoofaxElementType} class.
     *
     * @param language The language.
     */
    public SpoofaxElementType(
            @Nullable final SpoofaxIdeaLanguage language) {
        this(language, "SPOOFAX_ELEMENT_TYPE");
    }

    /**
     * Initializes a new instance of the {@link SpoofaxElementType} class.
     *
     * @param language The language.
     * @param debugName The debug name.
     */
    protected SpoofaxElementType(
            @Nullable final SpoofaxIdeaLanguage language,
            @NotNull @NonNls final String debugName) {
        super(debugName, language);
    }

    /**
     * Gets the {@link SpoofaxIdeaLanguage} of this element type.
     *
     * @return The {@link SpoofaxIdeaLanguage}.
     */
    @Nullable
    public SpoofaxIdeaLanguage getSpoofaxIdeaLanguage() { return (SpoofaxIdeaLanguage)getLanguage(); }

}
