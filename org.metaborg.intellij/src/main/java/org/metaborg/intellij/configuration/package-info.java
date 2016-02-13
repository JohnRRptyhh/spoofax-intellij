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

/**
 * Configuration classes that are shared between IntelliJ IDEA and JPS.
 *
 * There is an application-wide configuration, multiple project configurations, and multiple module
 * configurations.
 *
 * The application-wide configuration is stored by IntelliJ IDEA in the
 * <code>%idea.config.path%/options/metaborg.xml</code> file.
 *
 * The project-specific configuration is stored by IntelliJ IDEA in the
 * <code>$PROJECT_ROOT$/.idea/metaborg.xml</code> file.
 *
 * The module-specific configuration is stored by IntelliJ IDEA in the module's <code>.iml</code> file.
 *
 */
@NonNullByDefault
package org.metaborg.intellij.configuration;

import org.metaborg.intellij.*;