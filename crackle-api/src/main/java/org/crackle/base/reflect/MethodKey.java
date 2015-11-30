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
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import java.util.Collections;
import static java.util.Collections.unmodifiableSet;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * Identifies an object's method.
 * @author Chad
 */
public class MethodKey {
    
    /**
     * Extracts the method's method keys
     * @param c The class
     * @return The method keys of the class
     */
    public static Set<MethodKey> of(Class<?> c) {
        return unmodifiableSet(stream(c.getMethods()).map(MethodKey::new).collect(toSet()));
    }
    
    /**
     * Creates a map unique names for each method.
     * @param methodKeys The method keys
     * @return A map of uniquely named method keys.
     */
    public static Map<String, MethodKey> of(Set<MethodKey> methodKeys) {
        return unmodifiableMap(methodKeys.stream().collect(toMap(MethodKey::toString, identity())));
    }
    
    private final String methodName;
    private final List<Class<?>> parametertTypes;
    private final Class<?> returnType;

    public MethodKey(Method method) {
        this(method.getName(), asList(method.getParameterTypes()), method.getReturnType());
    }

    public MethodKey(String methodName, List<Class<?>> parametertTypes, Class<?> returnType) {
        this.methodName = methodName;
        this.parametertTypes = parametertTypes;
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Class<?>> getParametertTypes() {
        return parametertTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(methodName, parametertTypes, returnType);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean equal;
        
        if(this == o) {
            equal = true;
        }
        else if(getClass().isInstance(o)) {
            final MethodKey other = getClass().cast(o);
            equal = methodName.equals(other.methodName) && 
                    parametertTypes.equals(other.parametertTypes) &&
                    returnType.equals(other.returnType);
        }
        else {
            equal = false;
        }
        
        return equal;
    }
    
    @Override
    public String toString() {
        return String.format("%s-%s-%s", methodName, returnType, parametertTypes);
    }

}
