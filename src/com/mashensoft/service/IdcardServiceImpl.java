package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mashensoft.main.AllKnowMain;
import com.mashensoft.model.Idcard;

public class IdcardServiceImpl implements IIdcardService {
	Scanner scanner;
	PrintWriter pw;

	public IdcardServiceImpl(Scanner scanner, PrintWriter pw) {
		super();
		this.scanner = scanner;
		this.pw = pw;
	}

	public Idcard getIdcard(String idcardNumber) {
		Idcard idcard = new Idcard();
		String url = "http://qq.ip138.com/idsearch/index.asp?userid=" + idcardNumber
				+ "&action=idcard&B1=%B2%E9+%D1%AF";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "tdc2");
			idcard.setIdcard(idcardNumber);
			if (elements.size() >0) {
				idcard.setSex(elements.get(0).text());
				idcard.setBirthday(elements.get(1).text());
				idcard.setAddress(elements.get(2).text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return idcard;
	}

	public IdcardServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent(String idcardNumber) {
		Idcard idcard = this.getIdcard(idcardNumber);
		String content = idcard.getIdcard()+"\r\n";
		content += idcard.getSex()+"\r\n";
		content += idcard.getBirthday()+"\r\n";
		content += idcard.getAddress()+"\r\n";
		return content;
	}

	@Override
	public void mainControl(PrintWriter pw) {
		pw.println("请输入身份证号：\r\n");
		pw.println("输入q返回到上一级：\r\n");
		pw.flush();
		String msg = scanner.nextLine();
		if (msg.equalsIgnoreCase("q")) {
			pw.println("退出到主菜单：\r\n");
			pw.flush();
			AllKnowMain.printMainMenu(pw);
			return;
		}
		//检查身份证号
		boolean sign = checkIdcard(msg);
		if(!sign){
			pw.println("身份证号不正确\r\n");
			pw.flush();
			mainControl(pw);
		}
		String content = getContent(msg);
		pw.println(content + "\r\n");
		mainControl(pw);
	}
	public boolean checkIdcard(String idcard){
		boolean sign = true;
		if(idcard.length()!=18){
			sign = false;
			return sign;
		}
		String subString = idcard.substring(0, 17);
		try {
			Long.parseLong(subString);
		} catch (Exception e) {
			e.printStackTrace();
			sign = false;
		}
		return sign;
	}

}
