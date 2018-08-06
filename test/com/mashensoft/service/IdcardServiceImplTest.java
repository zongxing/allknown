package com.mashensoft.service;

import org.junit.Test;

import com.mashensoft.model.Idcard;

public class IdcardServiceImplTest {

	@Test
	public void test() {
		IdcardServiceImpl si = new IdcardServiceImpl();
		Idcard idcard = si.getIdcard("412724198302181577");
		System.out.println(idcard.getAddress());
	}

}
