/**
 * This file is part of crackle-examples.
 *
 * crackle-examples is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-examples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-examples.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cracke.example.helloworld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.crackle.Address;
import org.crackle.base.StringMessage;
import org.crackle.base.replyable.BasicReplyController;
import org.crackle.impl.Engine;
import org.crackle.impl.LocalVmAddressFactory;
import org.crackle.impl.engine.executor.ExecutorEngine;

/**
 *
 * @author Chad
 */
public class HelloWorldDriver {

    public void main(String[] args) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Engine engine = new ExecutorEngine(executorService, new LocalVmAddressFactory());
        final Address receiver = engine.create(new HelloWorldReceiverBehavior());
        final Address sender = engine.create(new HelloWorldSenderBehavior(receiver, new BasicReplyController()));
        
        engine.send(sender, new StringMessage("Sigrid"));
        
        executorService.shutdown();
        
    }
}
