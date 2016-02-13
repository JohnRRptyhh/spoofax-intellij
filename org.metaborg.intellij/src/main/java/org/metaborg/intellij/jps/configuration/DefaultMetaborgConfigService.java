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

package org.metaborg.intellij.jps.configuration;

import org.jetbrains.jps.model.*;
import org.jetbrains.jps.model.module.*;

/**
 * Default implementation of the {@link IMetaborgConfigService} interface.
 */
public final class DefaultMetaborgConfigService implements IMetaborgConfigService {

    /**
     * {@inheritDoc}
     */
    @Override
    public final JpsMetaborgApplicationConfig getConfiguration(final JpsGlobal global) {
        return global.getContainer().getChild(JpsMetaborgApplicationConfig.ROLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JpsMetaborgProjectConfig getConfiguration(final JpsProject project) {
        return project.getContainer().getChild(JpsMetaborgProjectConfig.ROLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JpsMetaborgModuleConfig getConfiguration(final JpsModule module) {
        final JpsElement result = module.getProperties();
        return result instanceof JpsMetaborgModuleConfig ? (JpsMetaborgModuleConfig)result : null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public final void setConfiguration(final JpsGlobal global, final JpsMetaborgApplicationConfig config) {
        global.getContainer().setChild(JpsMetaborgApplicationConfig.ROLE, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setConfiguration(final JpsProject project, final JpsMetaborgProjectConfig config) {
        project.getContainer().setChild(JpsMetaborgProjectConfig.ROLE, config);
    }

}
