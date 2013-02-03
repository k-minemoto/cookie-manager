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

import net.jp.saf.http.cookie.syntax.enums.TokenSeparators;

/**
 * CookieFormatter is an interface for formatting text such as cookie-value.
 * <p>
 * Cookie用に文字列を整形するインタフェースです。
 * </p>
 * 
 * @author k-minemoto
 */
public interface CookieFormatter {

	/**
	 * Return current TokenSeparators.
	 * 
	 * @return tokenSeparators
	 */
	TokenSeparators getCurrentTokenSeparator();

	/**
	 * Formats the given text.
	 * 
	 * @param sourceText
	 * @return Formatted string.
	 */
	String format(String sourceText);

	/**
	 * Formats the given text.
	 * 
	 * @param sourceText
	 * @param separator
	 * @return Formatted string.
	 */
	String format(String sourceText, TokenSeparators separator);
}
