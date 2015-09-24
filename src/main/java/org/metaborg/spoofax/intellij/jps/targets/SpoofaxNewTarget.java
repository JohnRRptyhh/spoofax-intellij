package org.metaborg.spoofax.intellij.jps.targets;

import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.java.JavaSourceRootProperties;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.java.JpsJavaExtensionService;
import org.jetbrains.jps.model.module.JpsTypedModuleSourceRoot;
import org.metaborg.spoofax.intellij.SpoofaxSourceRootDescriptor;
import org.metaborg.spoofax.intellij.jps.project.SpoofaxJpsProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base class for Spoofax build targets.
 */
public abstract class SpoofaxNewTarget extends ModuleBasedTarget<SpoofaxSourceRootDescriptor> {

    private final SpoofaxJpsProject project;
    /**
     * Gets the project that is being built.
     * @return The project.
     */
    public SpoofaxJpsProject project() {
        return this.project;
    }

    /**
     * Initializes a new instance of the {@link SpoofaxNewTarget} class.
     * @param project The project being built.
     * @param targetType The target type.
     */
    protected SpoofaxNewTarget(@NotNull SpoofaxJpsProject project, @NotNull ModuleBasedBuildTargetType<?> targetType) {
        super(targetType, project.module());
        this.project = project;
    }

    @Override
    public boolean isTests() {
        // Default implementation.
        return false;
    }

    private final SpoofaxNewTargetType<?> getSpoofaxTargetType() {
        // Default implementation.
        return (SpoofaxNewTargetType<?>)getTargetType();
    }

    @Override
    public final String getId() {
        // Default implementation.
        return super.myModule.getName();
    }

    @NotNull
    @Override
    public final List<SpoofaxSourceRootDescriptor> computeRootDescriptors(JpsModel jpsModel, ModuleExcludeIndex moduleExcludeIndex, IgnoredFileIndex ignoredFileIndex, BuildDataPaths buildDataPaths) {
        // Default implementation.
        List<SpoofaxSourceRootDescriptor> result = new ArrayList<>();
        JavaSourceRootType type = isTests() ? JavaSourceRootType.TEST_SOURCE : JavaSourceRootType.SOURCE;
        for (JpsTypedModuleSourceRoot<JavaSourceRootProperties> root : super.myModule.getSourceRoots(type)) {
            result.add(new SpoofaxSourceRootDescriptor(root.getFile(), this));
        }
        return result;
    }

    @Nullable
    @Override
    public final SpoofaxSourceRootDescriptor findRootDescriptor(String rootId, BuildRootIndex rootIndex) {
        // Default implementation.
        return ContainerUtil.getFirstItem(rootIndex.getRootDescriptors(new File(rootId), Collections.singletonList(getSpoofaxTargetType()), null));
    }

    @NotNull
    @Override
    public final Collection<File> getOutputRoots(CompileContext compileContext) {
        // Default implementation.
        return ContainerUtil.createMaybeSingletonList(JpsJavaExtensionService.getInstance().getOutputDirectory(super.myModule, isTests()));
    }
}
