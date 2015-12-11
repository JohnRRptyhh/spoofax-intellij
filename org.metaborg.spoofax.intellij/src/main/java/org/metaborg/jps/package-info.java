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

/**
 * JPS plugin with Metaborg Core functionality.
 * <p>
 * The JetBrains Project System (JPS) build system is used to build
 * an IntelliJ project. When an IntelliJ project is being build (e.g. by
 * clicking <em>Make Project</em> from the <em>Build</em> menu), a new
 * process is created in which the JPS plugin is loaded.
 * <p>
 * The JPS plugin is registered with the IDEA plugin in the
 * <a href="META-INF.plugin.xml">plugin.xml</a> file under the
 * &lt;compileServer.plugin&gt; tag. All the JPS plugin's dependencies
 * are listed there as well. You can get a list of JPS plugin dependencies
 * by executing the <code>gradle printJpsDependencies</code> task in the
 * project root.
 * <p>
 * The JPS plugin exposes services, which are
 * loaded by the build system through the {@link java.util.ServiceLoader} class.
 */
@NonNullByDefault
package org.metaborg.jps;

import org.metaborg.core.NonNullByDefault;