/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2019, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.wildfly.extension.microprofile.openapi;

import java.util.function.Consumer;

import org.jboss.as.clustering.controller.ResourceServiceHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.server.DeploymentProcessorTarget;
import org.jboss.as.server.deployment.Phase;
import org.jboss.dmr.ModelNode;
import org.wildfly.extension.microprofile.openapi.deployment.OpenAPIDependencyProcessor;
import org.wildfly.extension.microprofile.openapi.deployment.OpenAPIDocumentProcessor;
import org.wildfly.extension.microprofile.openapi.logging.MicroProfileOpenAPILogger;

/**
 * @author Paul Ferraro
 */
public class MicroProfileOpenAPIServiceHandler implements ResourceServiceHandler, Consumer<DeploymentProcessorTarget> {

    @Override
    public void installServices(OperationContext context, ModelNode model) throws OperationFailedException {
        MicroProfileOpenAPILogger.LOGGER.activatingSubsystem();
    }

    @Override
    public void removeServices(OperationContext context, ModelNode model) throws OperationFailedException {
    }

    @Override
    public void accept(DeploymentProcessorTarget target) {
        target.addDeploymentProcessor(MicroProfileOpenAPIExtension.SUBSYSTEM_NAME, Phase.DEPENDENCIES, Phase.DEPENDENCIES_MICROPROFILE_OPENAPI, new OpenAPIDependencyProcessor());
        target.addDeploymentProcessor(MicroProfileOpenAPIExtension.SUBSYSTEM_NAME, Phase.INSTALL, Phase.POST_MODULE_MICROPROFILE_OPENAPI, new OpenAPIDocumentProcessor());
    }
}
