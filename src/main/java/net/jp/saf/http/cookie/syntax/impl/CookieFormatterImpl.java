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

import net.jp.saf.http.cookie.syntax.CookieFormatter;
import net.jp.saf.http.cookie.syntax.enums.TokenSeparators;

/**
 * Implementation of the {@link CookieFormatter} interface.
 * <p>
 * CookieFormatterの実装です。
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public class CookieFormatterImpl implements CookieFormatter {

	protected final TokenSeparators separator;

	protected static final char SPACE = (char) 0x20;

	protected static final char DEL = (char) 0x7f;

	public CookieFormatterImpl() {
		this(TokenSeparators.RFC2616);
	}

	public CookieFormatterImpl(TokenSeparators tokenSeparators) {
		this.separator = tokenSeparators;
	}

	/**
	 * {@inheritDoc}
	 */
	public TokenSeparators getCurrentTokenSeparator() {
		return this.separator;
	}

	/**
	 * {@inheritDoc}
	 */
	public String format(String sourceText) {
		return this.format(sourceText, this.separator);
	}

	/**
	 * {@inheritDoc}
	 */
	public String format(String sourceText, TokenSeparators separators) {
		if (sourceText == null || sourceText.length() == 0) {
			return "";
		}
		final boolean quoted = (sourceText.charAt(0) == '"' && sourceText.charAt(sourceText.length() - 1) == '"');
		boolean needQuote = false;
		StringBuffer buf = new StringBuffer(sourceText.length() + 4);
		for (int i = quoted ? 1 : 0, len = quoted ? sourceText.length() - 1 : sourceText.length(); i < len; i++) {
			final char checkChar = sourceText.charAt(i);
			if (isCTLs(checkChar, separators)) {
				throw new IllegalArgumentException("Control character in cookie value, consider BASE64 encoding your value");
			}
			if (separators.contains(checkChar)) {
				needQuote = true;
				if (checkChar == '"') {
					buf.append("\\\"");
				} else if (checkChar == '\\') {
					if (++i >= len) {
						throw new IllegalArgumentException("Invalid escape character in cookie value.");
					}
					buf.append(checkChar);
					char nextChar = sourceText.charAt(i);
					if (isCTLs(nextChar, separators)) {
						buf.append(' '); // Tomcat7
					} else {
						buf.append(nextChar);
					}
				} else {
					buf.append(checkChar);
				}
			} else {
				buf.append(checkChar);
			}
		}
		if (quoted || needQuote) {
			buf.append('"');
			return '"' + buf.toString();
		}
		return buf.toString();
	}

	/**
	 * Return true when the character is contained in CTLs.
	 * <p>
	 * 文字がコントロールキャラクターに含まれているか返します。
	 * </p>
	 * 
	 * @param chr
	 * @param allowedSeparator
	 * @return true if this character contains CTLs ant not contains allowedSeparator.
	 */
	protected boolean isCTLs(final char chr, final TokenSeparators allowedSeparator) {
		if (chr < SPACE || chr >= DEL) {
			return !allowedSeparator.contains(chr);
		}
		return false;
	}
}
