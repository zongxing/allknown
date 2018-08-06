package com.mashensoft.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class IPServiceImplTest {



	@Test
	public void testGetSpName() {
		IIPService ipService = new IPServiceImpl();
		System.out.println(ipService.getSpName("11.11.10.10"));;
	}

	@Test
	public void testGetSpNameFromContent() {
	}

}
