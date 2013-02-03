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
package net.jp.saf.http.cookie.syntax.enums;

/**
 * Defined special characters for header field values separator.
 * <p>
 * Cookieで規定されたヘッダーを区切る文字。
 * </p>
 *
 * @author k-minemoto
 */
public enum TokenSeparators {
	/** Netscape. */
	NETSCAPE(";, "),
	/** RFC2616 2.2. BASIC RULE. */
	RFC2616("()<>@,;:\\\"/[]?={} \t"),
	/** RFC2616 2.2. BASIC RULE without slash. */
	RFC2616_WITHOUT_SLASH("()<>@,;:\\\"[]?={} \t");

	private final String separator;

	private TokenSeparators(String separatorChars) {
		this.separator = separatorChars;
	}

	/**
	 * Returns true if this TokenSeparators contains the specified character.
	 * <p>
	 * 指定の文字が、このTokenSeparatorsに含まれている場合trueを返します。
	 * </p>
	 *
	 * @param chr test char
	 * @return true if contains.
	 */
	public boolean contains(char chr) {
		return this.separator.indexOf(chr) != -1;
	}

}
