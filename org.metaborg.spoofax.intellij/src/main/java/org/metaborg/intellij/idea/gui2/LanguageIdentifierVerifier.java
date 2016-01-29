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

package org.metaborg.intellij.idea.gui2;

import org.metaborg.core.language.LanguageIdentifier;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Verifies that a component's input is a valid language identifier.
 */
public final class LanguageIdentifierVerifier extends InputVerifier {

    /**
     * Initializes a new instance of the {@link LanguageIdentifierVerifier} class.
     */
    public LanguageIdentifierVerifier() {
        super();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(final JComponent input) {
        final String text = ((JTextComponent)input).getText();
        return LanguageIdentifier.validId(text);
    }
}
