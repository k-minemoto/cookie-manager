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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.servlet.http.Cookie;
import net.jp.saf.http.cookie.syntax.CookieAttribute;
import net.jp.saf.http.cookie.syntax.CookieFormatter;

/**
 * Implementation of the {@link AbstractCookieAttributeImpl} for 'Expires' and 'Max-Age' attribute.
 * <p>
 * ExpiresとMax-Age属性用の実装です。
 * </p>
 *
 * @author k-minemoto
 *
 */
public class ExpiresAttribute extends AbstractCookieAttributeImpl {

	/**
	 * Date Formatter.
	 */
	protected final ThreadLocal<DateFormat> expiresFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			return format;
		}
	};

	protected static final String OLD_EXPIRES = "Thu, 01-Jan-1970 00:00:00 GMT";

	protected final boolean needMaxAgeAttr;

	/**
	 * The base date which calculates a expires.
	 * <p>
	 * Expireを計算する基準日
	 * </p>
	 */
	private Date baseDate = null;

	public ExpiresAttribute(CookieAttribute nextAttr) {
		this(nextAttr, false);
	}

	public ExpiresAttribute(CookieAttribute nextAttr, boolean isNeedMaxAgeAttr) {
		super(nextAttr);
		this.needMaxAgeAttr = isNeedMaxAgeAttr;
	}

	public void setBaseDate(Date date) {
		this.baseDate = date;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String writeAttribute(Cookie seed, CookieFormatter formatter) {
		final int maxAge = seed.getMaxAge();
		if (maxAge > -1) {
			final String expires = "; Expires=" + createExpiresValue(maxAge);
			if (needMaxAgeAttr) {
				return expires + "; Max-Age=" + maxAge;
			}
			return expires;
		}
		return EMPTY;
	}

	protected String createExpiresValue(int maxAge) {
		if (maxAge == 0) {
			return OLD_EXPIRES;
		}
		final long currentTime = (this.baseDate == null) ? System.currentTimeMillis() : this.baseDate.getTime();
		return expiresFormat.get().format(new Date(currentTime + maxAge * 1000L));
	}

}
