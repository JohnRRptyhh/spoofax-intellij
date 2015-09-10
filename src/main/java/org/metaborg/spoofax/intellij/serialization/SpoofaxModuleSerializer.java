package org.metaborg.spoofax.intellij.serialization;

import org.jdom.Element;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.module.JpsModuleType;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;
import org.metaborg.spoofax.intellij.JpsSpoofaxModuleType;

public final class SpoofaxModuleSerializer extends JpsModulePropertiesSerializer<JpsDummyElement> {


    public SpoofaxModuleSerializer() {
        super(JpsSpoofaxModuleType.INSTANCE, "SPOOFAX_MODULE", null);
    }

    @Override
    public JpsDummyElement loadProperties(Element element) {
        return JpsElementFactory.getInstance().createDummyElement();
    }

    @Override
    public void saveProperties(JpsDummyElement jpsDummyElement, Element element) {
        throw new UnsupportedOperationException("The `saveExtension()` method is not supported.");
    }
}
