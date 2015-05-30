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

package org.wso2.carbon.identity.workflow.mgt;

import org.wso2.carbon.identity.workflow.mgt.bean.TemplateParameterDef;

public class SimpleApprovalTemplate extends WorkflowTemplate {

    private static final TemplateParameterDef[] PARAMETER_DEFINITIONS;


    static {
        TemplateParameterDef roleDef = new TemplateParameterDef();
        roleDef.setParamName(WorkFlowConstants.TemplateConstants.SIMPLE_APPROVAL_ROLE_NAME);
        roleDef.setParamType(WorkflowTemplateParamType.USER_ROLE);
        PARAMETER_DEFINITIONS = new TemplateParameterDef[]{roleDef};
    }

    @Override
    public TemplateParameterDef[] getParamDefinitions() {
        return PARAMETER_DEFINITIONS;
    }

    @Override
    public String getTemplateId() {
        return WorkFlowConstants.TemplateConstants.APPROVAL_TEMPLATE_NAME;
    }

    @Override
    public String getFriendlyName() {
        return WorkFlowConstants.TemplateConstants.APPROVAL_TEMPLATE_NAME;
    }
}
