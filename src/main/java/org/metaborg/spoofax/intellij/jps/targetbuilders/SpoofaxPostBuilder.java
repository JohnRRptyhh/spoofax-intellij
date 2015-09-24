package org.metaborg.spoofax.intellij.jps.targetbuilders;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.model.module.JpsModule;
import org.metaborg.spoofax.intellij.SpoofaxSourceRootDescriptor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Singleton
public final class SpoofaxPostBuilder extends TargetBuilder<SpoofaxSourceRootDescriptor, SpoofaxPostTarget> {

    @SuppressWarnings("unchecked")
    @Inject
    public SpoofaxPostBuilder(SpoofaxPostTargetType targetType){
        super(Collections.singletonList(targetType));
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Spoofax post-Java builder";
    }

    @Override
    public void buildStarted(CompileContext context) {
        List<JpsModule> modules = context.getProjectDescriptor().getProject().getModules();
        System.out.println(modules);
    }

    @Override
    public void build(@NotNull SpoofaxPostTarget target,
                      @NotNull DirtyFilesHolder<SpoofaxSourceRootDescriptor, SpoofaxPostTarget> holder,
                      @NotNull BuildOutputConsumer consumer,
                      @NotNull CompileContext context) throws ProjectBuildException, IOException {

        //JpsProjectService projectService = new JpsProjectService(target.getModule());


        context.processMessage(new ProgressMessage("POST Compiling Spoofax sources"));
        //buildSpoofax(target.getModule());
        context.checkCanceled();

    }


}