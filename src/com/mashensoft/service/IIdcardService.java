package com.mashensoft.service;

import java.io.PrintWriter;

public interface IIdcardService {
	public String getContent(String idcard);
	public void mainControl(PrintWriter pw);
}
