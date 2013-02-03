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

import net.jp.saf.http.cookie.CookieBuilder;
import net.jp.saf.http.cookie.CookieManager;
import net.jp.saf.http.cookie.factory.InstanceFactory;
import net.jp.saf.http.cookie.factory.impl.InstanceFactoryImpl;

/**
 * Implementation of the {@link CookieManager} interface.
 * <p>
 * CookieManagerの実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class CookieManagerImpl implements CookieManager {

	private InstanceFactory factory;

	private String domain = "";

	private String path = null;

	private int maxAge = -1;

	private boolean secure = false;

	private boolean httpOnly = false;

	private int version = 0;

	private String comment = null;

	public CookieManagerImpl() {
		this.factory = new InstanceFactoryImpl();
		this.factory.initialize();
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieBuilder cookie(final String name) {
		return new CookieBuilderImpl(name, factory).path(path).maxAge(maxAge).domain(domain).secure(secure).httpOnly(httpOnly).version(version)
			.comment(comment);
	}

	/**
	 * Set the {@link InstanceFactory} instance which {@link CookieBuilder} uses.
	 * <p>
	 * CookieBuilderが使用するInstanceFactoryを設定します。
	 * </p>
	 * 
	 * @param instanceFactory {@link InstanceFactory} instance.
	 */
	public void setInstanceFactory(InstanceFactory instanceFactory) {
		this.factory = instanceFactory;
	}

	/**
	 * Set the default value.
	 * 
	 * @param domain default domain name.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Set the default value.
	 * 
	 * @param path default path.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Set the default value.
	 * 
	 * @param maxAge default maximum age(sec).
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * Set the default value.
	 * 
	 * @param secure default marks using a secure protocol.
	 */
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	/**
	 * Set the default value.
	 * 
	 * @param httpOnly default marks as HttpOnly.
	 */
	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	/**
	 * Set the default value.
	 * 
	 * @param version default cookie version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Set the default value.
	 * 
	 * @param comment default cookie comment.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
