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
package net.jp.saf.http.cookie.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import net.jp.saf.http.cookie.CookieManager;
import org.junit.Before;
import org.junit.Test;

public class CookieManagerImplTest extends CookieManagerImpl {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test the {@link CookieManager#cookie(String)} is thread safe?
	 */
	@Test
	public void testCookie() {
		int instance = 64;
		final CookieManager manager = this;
		ExecutorService service = Executors.newFixedThreadPool(instance);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>(instance + 1);
		for (int i = 0; i < instance; i++) {
			list.add(new ConcurrentTester(manager, "" + i));
		}
		List<Future<Integer>> invokeAll = null;
		try {
			invokeAll = service.invokeAll(list);
		} catch (InterruptedException e1) {
			fail(e1.getMessage());
		} finally {
			service.shutdown();
		}
		for (Future<Integer> invoke : invokeAll) {
			try {
				assertEquals(0, invoke.get().intValue());
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
	}

	private static class ConcurrentTester implements Callable<Integer> {

		private final CookieManager manager;

		private final String name;

		private final String value;

		private final String expected;

		private int count = 10000;

		private int version = 0;

		private int err = 0;

		public ConcurrentTester(CookieManager manager, String name) {
			this.manager = manager;
			this.name = "instance" + name;
			this.value = name + '_' + UUID.randomUUID().toString();
			this.version = Integer.parseInt(name) % 2;
			this.expected = manager.cookie(this.name).value(this.value).domain("example.com").path("/path/to/" + this.name).httpOnly().secure()
				.version(this.version).toHeaderString();
		}

		public Integer call() throws Exception {
			while (count > 0) {
				count--;
				String actual = manager.cookie(this.name).value(this.value).domain("example.com").path("/path/to/" + this.name).httpOnly().secure()
					.version(this.version).toHeaderString();
				if (!actual.equals(this.expected)) {
					err++;
					// System.out.printf("Fail :: expected:[%s], actual:[%s]%n", this.expected, actual);
				}
			}
			// System.out.println("Finish::" + this.name + ", err=" + this.err + ", expected:" + this.expected);
			return this.err;
		}
	}
}
