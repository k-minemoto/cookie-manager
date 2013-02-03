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
package net.jp.saf.http.cookie.impl;

import javax.servlet.http.Cookie;
import net.jp.saf.http.cookie.factory.InstanceFactory;
import net.jp.saf.http.cookie.factory.impl.InstanceFactoryImpl;

/**
 * HttpOnly Supported Cookie.
 * <p>
 * HttpOnlyを追加したCookie拡張です。
 * </p>
 *
 * @author k-minemoto
 *
 */
public class CookieImpl extends Cookie {

	/** Use toString(). */
	private static final InstanceFactory TO_STRING = new InstanceFactoryImpl();

	/** {@link Cookie} has setHttpOnly method? */
	private static final boolean COOKIE_HTTPONLY_SUPPORT;

	private boolean httpOnly;

	static {
		boolean hasMethod = true;
		try {
			Cookie.class.getMethod("setHttpOnly", boolean.class);
		} catch (Exception e) {
			hasMethod = false;
		} finally {
			COOKIE_HTTPONLY_SUPPORT = hasMethod;
		}
	}

	public CookieImpl(String name, String value) {
		super(name, value);
	}

	public static boolean isHttpOnlySupported() {
		return CookieImpl.COOKIE_HTTPONLY_SUPPORT;
	}

	public boolean isHttpOnly() {
		return this.httpOnly;
	}

	public void setHttpOnly(boolean isHttpOnly) {
		this.httpOnly = isHttpOnly;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() {
		CookieImpl clone = (CookieImpl) super.clone();
		clone.setHttpOnly(httpOnly);
		return clone;
	}

	/**
	 * @return Cookie Header
	 */
	@Override
	public String toString() {
		return TO_STRING.buildCookieAttribute(getVersion()).write(this, TO_STRING.createCookieFormatter(getVersion()));
	}

}
