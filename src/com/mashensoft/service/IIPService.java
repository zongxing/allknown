package com.mashensoft.service;

import java.io.PrintWriter;

public interface IIPService {
	public String getSpName(String ip);
	public void mainControl(PrintWriter pw);
}
