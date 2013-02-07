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
package net.jp.saf.http.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface represents a builder that creates {@link javax.servlet.http.Cookie}.
 *
 * @author k-minemoto
 *
 */
public interface CookieBuilder {

	/**
	 * Cookie's comment.
	 *
	 * @param purpose comment to display to the user.
	 * @return this.
	 */
	CookieBuilder comment(String purpose);

	/**
	 * Cookie's domain.
	 *
	 * @param pattern the domain name within which this cookie is visible.
	 * @return this.
	 */
	CookieBuilder domain(String pattern);

	/**
	 * Marks or unmarks this Cookie as HttpOnly.
	 *
	 * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise.
	 * @return this.
	 */
	CookieBuilder httpOnly(boolean isHttpOnly);

	/**
	 * Marks this Cookie as HttpOnly.
	 *
	 * @return this.
	 */
	CookieBuilder httpOnly();

	/**
	 * Unmarks this Cookie as HttpOnly.
	 *
	 * @return this.
	 */
	CookieBuilder notHttpOnly();

	/**
	 * Cookie's maximum age in seconds.
	 *
	 * @param expiry maximum age of the cookie in seconds; if negative, means the cookie is not stored; if zero, deletes the
	 *            cookie.
	 * @return this.
	 */
	CookieBuilder maxAge(int expiry);

	/**
	 * Cookie's path.
	 *
	 * @param uri a path
	 * @return this.
	 */
	CookieBuilder path(String uri);

	/**
	 * Marks or unmarks this cookie should only be sent using a secure protocol, such as HTTPS or SSL.
	 *
	 * @param isSecure if true, sends the cookie from the browser to the server only when using a secure protocol; if false, sent on any protocol
	 * @return this.
	 */
	CookieBuilder secure(boolean isSecure);

	/**
	 * Marks this cookie should only be sent using a secure protocol.
	 *
	 * @return this.
	 */
	CookieBuilder secure();

	/**
	 * Unmarks this cookie should only be sent using a secure protocol.
	 *
	 * @return this.
	 */
	CookieBuilder notSecure();

	/**
	 * Cookie's value.
	 *
	 * @param value the new value of the cookie
	 * @return this.
	 */
	CookieBuilder value(String value);

	/**
	 * Cookie's version.
	 *
	 * @param version version no.
	 * @return this.
	 */
	CookieBuilder version(int version);

	/**
	 * Write this cookie to specified response.
	 *
	 * @param response {@link HttpServletResponse} instance to write cookie;
	 */
	void write(HttpServletResponse response);

	/**
	 * Create a new Cookie with the builded value.
	 *
	 * @return new {@link Cookie} instance
	 */
	Cookie create();

	/**
	 * Return the cookie-header value which can be used for response header.
	 *
	 * @return cookie-header value
	 */
	String toHeaderString();
}
