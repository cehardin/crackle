/**
 * This file is part of crackle-api.
 *
 * crackle-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-api.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.crackle.base.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Chad
 */
public class InvokeMessage {

    private final MethodKey methodKey;
    private final List<?> arguments;

    public InvokeMessage(MethodKey methodKey, List<?> arguments) {
        this.methodKey = methodKey;
        this.arguments = arguments;
    }

    public MethodKey getMethodKey() {
        return methodKey;
    }

    public List<?> getArguments() {
        return arguments;
    }
    
    

}
