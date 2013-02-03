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
package net.jp.saf.http.cookie.syntax;

import javax.servlet.http.Cookie;

/**
 * The CookieAttribute interface models a Cookie-Header attribute.
 * 
 * <p>
 * Cookieの属性を扱うインタフェースです。
 * </p>
 * 
 * @author k-minemoto
 */
public interface CookieAttribute {

	String EMPTY = "";

	/**
	 * Build the attribute value which can be used for Cookie-Header.
	 * <p>
	 * Cookieヘッダーに使用する属性値を構築します。
	 * </p>
	 * 
	 * @param seed cookie
	 * @param formatter formatter
	 * @return The attribute value which can be used for Cookie-Header.
	 */
	String write(final Cookie seed, final CookieFormatter formatter);
}
