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
package net.jp.saf.http.cookie.factory;

import net.jp.saf.http.cookie.syntax.CookieAttribute;
import net.jp.saf.http.cookie.syntax.CookieFormatter;

/**
 * This interface represents a builder that creates {@link CookieAttribute} and {@link CookieFormatter}.
 * <p>
 * {@link CookieAttribute}と{@link CookieFormatter}を構築するインタフェースです。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public interface InstanceFactory {

	/**
	 * Initializes this instance.
	 * <p>
	 * このインスタンスを初期化します。 初期化処理は任意です。
	 * </p>
	 */
	void initialize();

	/**
	 * Create a {@link CookieFormatter} instance.
	 * <p>
	 * {@link CookieFormatter}のインスタンスを新規作成します。
	 * </p>
	 * 
	 * @param cookieVersion
	 * @return new instance.
	 */
	CookieFormatter createCookieFormatter(int cookieVersion);

	/**
	 * Build a instance of {@link CookieAttribute} which builds a Cookie attribute.
	 * <p>
	 * Cookie属性を構築する{@link CookieAttribute}のインスタンスを構築します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute buildCookieAttribute(int cookieVersion);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Name attribute.
	 * <p>
	 * Cookieのname属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createNameAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Expires attribute.
	 * <p>
	 * CookieのExpires属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createExpiresAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Domain attribute.
	 * <p>
	 * CookieのDomain属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createDomainAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Path attribute.
	 * <p>
	 * CookieのPath属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createPathAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Secure attribute.
	 * <p>
	 * CookieのSecure属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createSecureAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-HttpOnly attribute.
	 * <p>
	 * CookieのHttpOnly属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createHttpOnlyAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Version attribute.
	 * <p>
	 * CookieのVersion属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createVersionAttribute(int cookieVersion, CookieAttribute nextAttr);

	/**
	 * Create a instance of {@link CookieAttribute} which builds a Cookie-Comment attribute.
	 * <p>
	 * CookieのComment属性を構築する{@link CookieAttribute}のインスタンスを作成します。
	 * </p>
	 * 
	 * @param cookieVersion cookie version (0 or 1).
	 * @param nextAttr next {@link CookieAttribute} instance.
	 * @return {@link CookieAttribute} instance.
	 */
	CookieAttribute createCommentAttribute(int cookieVersion, CookieAttribute nextAttr);
}
