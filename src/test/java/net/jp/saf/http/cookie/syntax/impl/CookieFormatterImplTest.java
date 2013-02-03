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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class CookieFormatterImplTest extends CookieFormatterImpl {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFormatString() {
		assertEquals("", format(null));
		assertEquals("", format(""));
		assertEquals("abcdefghijklmnopqrstuvwxyz", format("abcdefghijklmnopqrstuvwxyz"));
		assertEquals("\"a b c d e\"", format("a b c d e"));
		assertEquals("\"a b \\ c d\"", format("a b \\\rc d"));
		assertEquals("\"ab\tcd\"", format("ab	cd"));
		// CTLs char replace ' ' after '\' (Tomcat7)
		for (char i = 0; i < 0x20; i++) {
			if (i == '\t') {
				continue; // HT not replaced.
			}
			assertEquals("\"ab\\ cd\"", format("ab\\" + i + "cd"));
		}
		for (char i = 0x7f; i <= 0xff; i++) {
			assertEquals("\"ab\\ cd\"", format("ab\\" + i + "cd"));
		}
	}

	@Test
	public void testFormatStringException() {
		// CTLs char replace ' ' after '\' (Tomcat7)
		for (char i = 0; i < 0x20; i++) {
			if (i == '\t') {
				continue; // HT not replaced.
			}
			try {
				format("ab" + i + "cd");
				fail("Not allowed CTLs char");
			} catch (IllegalArgumentException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		for (char i = 0x7f; i <= 0xff; i++) {
			try {
				format("ab" + i + "cd");
				fail("Not allowed CTLs char");
			} catch (IllegalArgumentException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		// not allowed last '\'
		try {
			format("abcd\\");
			format("\"abcd\\\"");
			fail("Not allowed end '\'");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
