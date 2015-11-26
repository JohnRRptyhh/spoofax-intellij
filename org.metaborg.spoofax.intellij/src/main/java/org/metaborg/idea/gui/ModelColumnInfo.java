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

package org.metaborg.idea.gui;

import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Column info for nodes that implement the {@link IModelItem} interface.
 */
public class ModelColumnInfo<Item extends IModelItem<Item>> extends ColumnInfo<Item, Object> {

    /**
     * Initializes a new instance of the {@link ModelColumnInfo} class.
     *
     * @param name The name of the column.
     */
    public ModelColumnInfo(final String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Object valueOf(final Item item) {
        return item.getValueOfColumn(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(final Item item, @Nullable final Object value) {
        item.setValueOfColumn(this, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(final Item item) {
        return item.canEditValueOfColumn(this);
    }

}
