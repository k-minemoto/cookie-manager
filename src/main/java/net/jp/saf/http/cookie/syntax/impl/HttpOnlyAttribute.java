/**
 * Copyright (C) 2013- k-minemoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.jp.saf.http.cookie.syntax.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.Cookie;
import net.jp.saf.http.cookie.impl.CookieImpl;
import net.jp.saf.http.cookie.syntax.CookieAttribute;
import net.jp.saf.http.cookie.syntax.CookieFormatter;

/**
 * Implementation of the {@link AbstractCookieAttributeImpl} for 'HttpOnly' attribute.
 * <p>
 * HttpOnly属性用の実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class HttpOnlyAttribute extends AbstractCookieAttributeImpl {

	public HttpOnlyAttribute(CookieAttribute nextAttr) {
		super(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String writeAttribute(Cookie seed, CookieFormatter formatter) {
		if (seed instanceof CookieImpl) {
			return ((CookieImpl) seed).isHttpOnly() ? "; HttpOnly" : EMPTY;
		}
		if (!CookieImpl.isHttpOnlySupported()) {
			return EMPTY;
		}
		try {
			final Method isHttpOnly = Cookie.class.getMethod("isHttpOnly");
			final Object httpOnly = isHttpOnly.invoke(seed);
			if (httpOnly instanceof Boolean) {
				return ((Boolean) httpOnly).booleanValue() ? "; HttpOnly" : EMPTY;
			}
		} catch (SecurityException e) {
			return EMPTY;
		} catch (IllegalArgumentException e) {
			return EMPTY;
		} catch (NoSuchMethodException e) {
			return EMPTY;
		} catch (IllegalAccessException e) {
			return EMPTY;
		} catch (InvocationTargetException e) {
			return EMPTY;
		}
		return EMPTY;
	}

}
