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

package org.metaborg.core.project.settings;

import org.jetbrains.annotations.NotNull;
import org.metaborg.core.language.LanguageIdentifier;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Settings stub.
 */
public final class SettingsStub extends Settings {

    /* package private */ static final SettingKey<String> NAME_KEY = new SettingKey<>("name", String.class);
    /* package private */ static final SettingKey<LanguageIdentifier> ID_KEY = new SettingKey<>("id", LanguageIdentifier.class);

    public SettingsStub(
            @NotNull final Map<SettingKey<?>, Object> settings, @Nullable final Settings parent) {
        super(settings, parent);
    }

    public String name() {
        return getSetting(NAME_KEY);
    }

    public LanguageIdentifier id() { return getSetting(ID_KEY); }

}

