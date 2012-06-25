// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.api.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloud.api.ApiConstants;
import com.cloud.api.BaseListCmd;
import com.cloud.api.IdentityMapper;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.response.ListResponse;
import com.cloud.api.response.PrivateGatewayResponse;
import com.cloud.network.vpc.PrivateGateway;

/**
 * @author Alena Prokharchyk
 */
@Implementation(description="List private gateways", responseObject=PrivateGatewayResponse.class)
public class ListPrivateGatewaysCmd extends BaseListCmd{
    public static final Logger s_logger = Logger.getLogger(ListPrivateGatewaysCmd.class.getName());

    private static final String s_name = "listprivategatewaysresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////
    
    @Parameter(name=ApiConstants.IP_ADDRESS, type=CommandType.STRING, description="list gateways by ip address")
    private String ipAddress;
    
    @Parameter(name=ApiConstants.VLAN, type=CommandType.STRING, description="list gateways by vlan")
    private String vlan;
    
    @IdentityMapper(entityTableName="vpc")
    @Parameter(name=ApiConstants.VPC_ID, type=CommandType.LONG, description="list gateways by vpc")
    private Long vpcId;
    
    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////


    public String getVlan() {
        return vlan;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    
    public Long getVpcId() {
        return vpcId;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////
    @Override
    public String getCommandName() {
        return s_name;
    }
    
    @Override
    public void execute() {
        List<PrivateGateway> gateways = _vpcService.listPrivateGateway(this);
        ListResponse<PrivateGatewayResponse> response = new ListResponse<PrivateGatewayResponse>();
        List<PrivateGatewayResponse> projectResponses = new ArrayList<PrivateGatewayResponse>();
        for (PrivateGateway gateway : gateways) {
            PrivateGatewayResponse gatewayResponse = _responseGenerator.createPrivateGatewayResponseResponse(gateway);
            projectResponses.add(gatewayResponse);
        }
        response.setResponses(projectResponses);
        response.setResponseName(getCommandName());
        
        this.setResponseObject(response);
    }
}
