/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.workflow.mgt.internal;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.identity.workflow.mgt.WorkFlowExecutor;
import org.wso2.carbon.identity.workflow.mgt.WorkFlowExecutorManager;
import org.wso2.carbon.identity.workflow.mgt.WorkflowRequestHandler;
import org.wso2.carbon.identity.workflow.mgt.ws.WSWorkflowExecutor;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.ConfigurationContextService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @scr.component name="identity.workflow" immediate="true"
 * @scr.reference name="user.realmservice.default" interface="org.wso2.carbon.user.core.service.RealmService"
 * cardinality="1..1" policy="dynamic" bind="setRealmService"
 * unbind="unsetRealmService"
 * @scr.reference name="workflow.executor.service"
 * interface="org.wso2.carbon.identity.workflow.mgt.WorkFlowExecutor"
 * cardinality="0..n" policy="dynamic"
 * bind="setWorkflowExecutorService"
 * unbind="unsetWorkflowExecutorService"
 * @scr.reference name="workflow.request.handler.service"
 * interface="org.wso2.carbon.identity.workflow.mgt.WorkflowRequestHandler"
 * cardinality="0..n" policy="dynamic"
 * bind="setWorkflowRequestHandlerService"
 * unbind="unsetWorkflowRequestHandlerService"
 * @scr.reference name="config.context.service"
 * interface="org.wso2.carbon.utils.ConfigurationContextService"
 * cardinality="1..1" policy="dynamic"  bind="setConfigurationContextService"
 * unbind="unsetConfigurationContextService"
 */
public class WorkflowMgtServiceComponent {
    private static RealmService realmService;
    private static ConfigurationContextService configurationContextService;
    private static SortedSet<WorkFlowExecutor> workFlowExecutors;
    private static Map<String, WorkflowRequestHandler> workflowRequestHandlers;
    private static BundleContext bundleContext;

    public static RealmService getRealmService() {
        return realmService;
    }

    protected void setRealmService(RealmService realmService) {
        WorkflowMgtServiceComponent.realmService = realmService;
    }

    public static ConfigurationContextService getConfigurationContextService() {
        return configurationContextService;
    }

    protected void setConfigurationContextService(ConfigurationContextService contextService) {
        WorkflowMgtServiceComponent.configurationContextService = contextService;
    }

    protected void activate(ComponentContext context) {
        bundleContext = context.getBundleContext();
        WorkFlowExecutorManager workFlowExecutorManager = WorkFlowExecutorManager.getInstance();
        bundleContext.registerService(WorkFlowExecutorManager.class, workFlowExecutorManager, null);
    }

    protected void unsetRealmService(RealmService realmService) {
        WorkflowMgtServiceComponent.realmService = null;
    }

    protected void unsetConfigurationContextService(ConfigurationContextService contextService) {
        WorkflowMgtServiceComponent.configurationContextService = null;
    }

    protected void setWorkflowRequestHandlerService(WorkflowRequestHandler workflowRequestHandler) {
        WorkflowServiceDataHolder.getInstance().addWorkflowRequestHandler(workflowRequestHandler);
    }

    protected void unsetWorkflowRequestHandlerService(WorkflowRequestHandler workflowRequestHandler) {
        WorkflowServiceDataHolder.getInstance().removeWorkflowRequestHandler(workflowRequestHandler);
    }
}
