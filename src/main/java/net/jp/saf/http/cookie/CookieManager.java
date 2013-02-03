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

/**
 * This interface represents a builder that creates {@link CookieBuilder}.
 * <p>
 * {@link CookieBuilder}生成を管理するインタフェースです.
 * </p>
 * 
 * @author k-minemoto
 * 
 */
public interface CookieManager {
	/**
	 * {@link CookieBuilder}のインスタンスを生成します.
	 * 
	 * @param name Cookieのname値
	 * @return Cookieビルダーのインスタンス
	 */
	CookieBuilder cookie(final String name);
}
