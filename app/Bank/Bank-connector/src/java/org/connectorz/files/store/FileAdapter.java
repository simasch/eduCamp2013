/*
Copyright 2012 Adam Bien, adam-bien.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.connectorz.files.store;

import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

/**
 *
 * @author adam bien, blog.adam-bien.com
 */
@Connector(reauthenticationSupport = false,
transactionSupport = TransactionSupport.TransactionSupportLevel.LocalTransaction)
public class FileAdapter implements ResourceAdapter {

    @Override
    public void start(BootstrapContext bc) {
    }

    @Override
    public void stop() {
    }

    @Override
    public void endpointActivation(MessageEndpointFactory mef, ActivationSpec as) {
    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory mef, ActivationSpec as) {
    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] ass) {
        return null;
    }
}
