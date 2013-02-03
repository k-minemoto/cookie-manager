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
 * Implementation of the {@link AbstractCookieAttributeImpl} for 'Comment' attribute.
 * <p>
 * Comment属性用の実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class CommentAttribute extends AbstractCookieAttributeImpl {

	public CommentAttribute(CookieAttribute nextAttr) {
		super(nextAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String writeAttribute(final Cookie seed, final CookieFormatter formatter) {
		final String comment = seed.getComment();
		if (comment != null && comment.length() > 0) {
			return "; Comment=" + formatter.format(comment);
		}
		return EMPTY;
	}

}
