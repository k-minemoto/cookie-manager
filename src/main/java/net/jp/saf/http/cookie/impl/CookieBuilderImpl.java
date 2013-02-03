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
import javax.servlet.http.HttpServletResponse;
import net.jp.saf.http.cookie.CookieBuilder;
import net.jp.saf.http.cookie.factory.InstanceFactory;

/**
 * Implementation of the {@link CookieBuilder} interface.
 * <p>
 * CookieBuilderの実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class CookieBuilderImpl implements CookieBuilder {

	protected final CookieImpl seed;

	protected final InstanceFactory factory;

	public CookieBuilderImpl(String name, InstanceFactory instanceFactory) {
		this.seed = new CookieImpl(name, null);
		this.factory = instanceFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder comment(String purpose) {
		this.seed.setComment(purpose);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder domain(String pattern) {
		this.seed.setDomain(pattern);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder httpOnly(boolean isHttpOnly) {
		this.seed.setHttpOnly(isHttpOnly);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder httpOnly() {
		return httpOnly(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder notHttpOnly() {
		return httpOnly(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder maxAge(int expiry) {
		this.seed.setMaxAge(expiry);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder path(String uri) {
		this.seed.setPath(uri);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder secure(boolean isSecure) {
		this.seed.setSecure(isSecure);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder secure() {
		return secure(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder notSecure() {
		return secure(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder value(String value) {
		this.seed.setValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder version(int version) {
		this.seed.setVersion(version == 1 ? 1 : 0);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void write(HttpServletResponse response) {
		if (CookieImpl.isHttpOnlySupported()) {
			response.addCookie(create());
		} else {
			response.addHeader("Set-Cookie", toHeaderString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Cookie create() {
		return (Cookie) this.seed.clone();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toHeaderString() {
		return factory.buildCookieAttribute(this.seed.getVersion()).write(this.seed, factory.createCookieFormatter(this.seed.getVersion()));
	}

}
