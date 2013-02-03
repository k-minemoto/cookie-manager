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

import javax.servlet.http.Cookie;
import net.jp.saf.http.cookie.syntax.CookieAttribute;
import net.jp.saf.http.cookie.syntax.CookieFormatter;

/**
 * Abstract implementation of the {@link CookieAttribute} interface.
 *
 * @author k-minemoto
 *
 */
public abstract class AbstractCookieAttributeImpl implements CookieAttribute {

	/**
	 * Next Atribute.
	 */
	protected final CookieAttribute nextAttr;

	public AbstractCookieAttributeImpl(CookieAttribute nextAttribute) {
		this.nextAttr = nextAttribute;
	}

	/**
	 * {@inheritDoc}
	 */
	public String write(Cookie seed, CookieFormatter formatter) {
		if (nextAttr == null) {
			return writeAttribute(seed, formatter);
		}
		String next = nextAttr.write(seed, formatter);
		if (EMPTY.equals(next)) {
			return writeAttribute(seed, formatter);
		} else {
			return writeAttribute(seed, formatter) + next;
		}
	}

	/**
	 * Write the attribute value.
	 * <p>
	 * 属性値を出力します。
	 * </p>
	 *
	 * @param seed
	 * @param formatter
	 * @return The attribute value which can be used for Cookie-Header.
	 */
	protected abstract String writeAttribute(Cookie seed, CookieFormatter formatter);

}
