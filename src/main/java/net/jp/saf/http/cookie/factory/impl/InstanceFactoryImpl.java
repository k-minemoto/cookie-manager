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
package net.jp.saf.http.cookie.factory.impl;

import net.jp.saf.http.cookie.factory.InstanceFactory;
import net.jp.saf.http.cookie.syntax.CookieAttribute;
import net.jp.saf.http.cookie.syntax.CookieFormatter;
import net.jp.saf.http.cookie.syntax.impl.CommentAttribute;
import net.jp.saf.http.cookie.syntax.impl.CookieFormatterImpl;
import net.jp.saf.http.cookie.syntax.impl.DomainAttribute;
import net.jp.saf.http.cookie.syntax.impl.ExpiresAttribute;
import net.jp.saf.http.cookie.syntax.impl.HttpOnlyAttribute;
import net.jp.saf.http.cookie.syntax.impl.NameAttribute;
import net.jp.saf.http.cookie.syntax.impl.PathAttribute;
import net.jp.saf.http.cookie.syntax.impl.SecureAttribute;
import net.jp.saf.http.cookie.syntax.impl.VersionAttribute;

/**
 * Implementation of the {@link InstanceFactory} interface.
 * <p>
 * InstanceFactoryの実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class InstanceFactoryImpl implements InstanceFactory {

	private CookieAttribute version0;

	private CookieAttribute version1;

	private boolean initialized;

	/**
	 * {@inheritDoc}
	 */
	public void initialize() {
		if (this.initialized) {
			return;
		}
		CookieAttribute attr0 = this.buildVersion0CookieAttribute();
		CookieAttribute attr1 = this.buildVersion1CookieAttribute();
		this.version0 = attr0;
		this.version1 = attr1;
		this.initialized = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieFormatter createCookieFormatter(int cookieVersion) {
		return new CookieFormatterImpl();
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute buildCookieAttribute(int cookieVersion) {
		if (cookieVersion == 0) {
			return this.version0;
		} else {
			return this.version1;
		}
	}

	protected CookieAttribute buildVersion0CookieAttribute() {
		return createNameAttribute(0,
			createExpiresAttribute(0, createPathAttribute(0, createDomainAttribute(0, createSecureAttribute(0, createHttpOnlyAttribute(0, null))))));
	}

	protected CookieAttribute buildVersion1CookieAttribute() {
		return createNameAttribute(
			1,
			createExpiresAttribute(
				1,
				createDomainAttribute(1,
					createPathAttribute(1, createSecureAttribute(1, createHttpOnlyAttribute(1, createCommentAttribute(1, null)))))));
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createNameAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new NameAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createExpiresAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new ExpiresAttribute(nextAttr, cookieVersion != 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createDomainAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new DomainAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createPathAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new PathAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createSecureAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new SecureAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createHttpOnlyAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new HttpOnlyAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createVersionAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new VersionAttribute(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	public CookieAttribute createCommentAttribute(int cookieVersion, CookieAttribute nextAttr) {
		return new CommentAttribute(nextAttr);
	}

}
