package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.mashensoft.main.AllKnowMain;

public class IPServiceImpl implements IIPService {
	Scanner scanner;
	PrintWriter pw;

	public IPServiceImpl(Scanner scanner, PrintWriter pw) {
		this.scanner = scanner;
		this.pw = pw;
	}

	public IPServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSpName(String ip) {
		String url = "http://ip138.com/ips138.asp?ip="+ip+"&action=2";
		String content = getContentFromUrl(url);
		String spName = getSpNameFromContent(content);
		return spName;
	}
	public String getContentFromUrl(String webUrl){
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(webUrl);
			Scanner s = new Scanner(url.openStream(),"gb2312");
			while(s.hasNextLine()){
				sb.append(s.nextLine());
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static String getSpNameFromContent(String content){
		String spName = "";
		int beginIndex = content.indexOf("本站数据：");
		int endIndex = content.indexOf("</li>");
		spName = content.substring(beginIndex+5, endIndex);
		return spName;
	}
	//http://ip138.com/ips138.asp?ip=14.146.93.146&action=2
	public boolean checkIPIsLegal(String ip){
		boolean sign = false;
		String[] arr = ip.split("\\.");
		if(arr.length!=4){
			sign = false;
		}else{
			sign = true;
		}
		for (int i = 0; i < arr.length; i++) {
			String item = arr[i];
			try {
				Integer itemNum =  Integer.parseInt(item);
				if(itemNum>255||itemNum<0){
					sign = false;
				}
			} catch (Exception e) {
				sign = false;
			}
		}
		return sign;
	}
	@Override
	public void mainControl(PrintWriter pw) {
		pw.println("请输入IP地址：");
		pw.println("回到主菜单，请按q：");
		String ip = scanner.nextLine();
		if(ip.equalsIgnoreCase("q")){
			pw.println("退出到主菜单：");
			AllKnowMain.printMainMenu(pw);
		}
		boolean sign = checkIPIsLegal(ip);
		if(!sign){
			pw.println("ip不合法，请重新输入IP：");
			mainControl(pw);
		}
		String spName = getSpName(ip);
		pw.println(spName);
		mainControl(pw);
	}

}
