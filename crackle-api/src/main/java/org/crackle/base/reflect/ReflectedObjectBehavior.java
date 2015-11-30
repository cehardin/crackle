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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.base.ObjectMessage;
import org.crackle.base.ThrowableMessage;
import org.crackle.base.replyable.AbstractReplyingBehavior;

/**
 *
 * @author Chad
 */
public class ReflectedObjectBehavior extends AbstractReplyingBehavior {

    private static final Logger logger = Logger.getLogger(ReflectedObjectBehavior.class.getName());
    private final Map<MethodKey, Method> cache = new HashMap<>();
    private final Object object;

    public ReflectedObjectBehavior(Object object) {
        this.object = object;
    }
    
    @Override
    protected Optional<Message> processThenReply(Context context) {
        final InvokeMessage message = context.getMessageAs();
        final Optional<Method> method = findMethod(message);
        final Optional<Message> reply;

        if (method.isPresent()) {
            try {
                final Object ret = method.get().invoke(object, message.getArguments().toArray());
                
                if (Void.class.equals(method.get().getReturnType())) {
                    reply = Optional.empty();
                } else {
                    reply = Optional.of(ObjectMessage.mutableObject(ret));
                }
            } catch (IllegalAccessException | IllegalArgumentException ex) {
                logger.log(Level.WARNING, "", ex);
                return Optional.empty();
            } catch (InvocationTargetException ex) {
                return Optional.of(new ThrowableMessage(ex.getTargetException()));
            }
        } else {
            reply = Optional.empty();
        }

        return reply;

    }

    private Optional<Method> findMethod(InvokeMessage message) {

        final MethodKey cacheKey = message.getMethodKey();
        
        if (cache.containsKey(cacheKey)) {
            return Optional.of(cache.get(cacheKey));
        }

        for (final Method method : object.getClass().getMethods()) {
            if(cacheKey.equals(new MethodKey(method))) {
                cache.put(cacheKey, method);
                return Optional.of(method);
            }
        }

        return Optional.empty();
    }
}
