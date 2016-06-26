/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.myLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 代码行数统计
 * @author ThinkGem
 * @version 2014-7-22
 */

public class CodeCounter {
	/**
	 * 代码行数统计
	 * 
	 * 	统计文件：1276-2016-06-15
		代码行数：88502
		注释行数：18133
		空白行数：25656
		////////////////
		统计文件：1279-2016-06-20
		代码行数：88540
		注释行数：18419
		空白行数：25609
		统计文件：1280
		////////////////
		代码行数：88531
		注释行数：18424
		空白行数：25568
	 */
	public static void main(String[] args) {
		int i=9;
		Myif<String> temif=new Myif<String>();
		ListIf listIf=new ListIf();
		listIf.addCondition(i>9, new Mytest(), "kkk");
		listIf.addCondition(i>1, new Mytest(), "ddd");
		String ll= temif.myElseIf(listIf);
		System.out.println(ll);
		String file = CodeCounter.class.getResource("/").getFile();
		String path = file.replace("webapp/WEB-INF/classes", "src");

		ArrayList<File> al = getFile(new File(path));
		for (File f : al) {
			if (f.getName().matches(".*\\.java$")){ // 匹配java格式的文件
				count(f);
				System.out.println(f);
			}
		}
		System.out.println("统计文件：" + files);
		System.out.println("代码行数：" + codeLines);
		System.out.println("注释行数：" + commentLines);
		System.out.println("空白行数：" + blankLines);
	}
	
	static long files = 0;
	static long codeLines = 0;
	static long commentLines = 0;
	static long blankLines = 0;
	static ArrayList<File> fileArray = new ArrayList<File>();
	
	/**
	 * 获得目录下的文件和子目录下的文件
	 * @param f
	 * @return
	 */
	public static ArrayList<File> getFile(File f) {
		File[] ff = f.listFiles();
		for (File child : ff) {
			if (child.isDirectory()) {
				getFile(child);
			} else
				fileArray.add(child);
		}
		return fileArray;

	}

	/**
	 * 统计方法
	 * @param f
	 */
	private static void count(File f) {
		BufferedReader br = null;
		boolean flag = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.trim(); // 除去注释前的空格
				if (line.matches("^[ ]*$")) { // 匹配空行
					blankLines++;
				} else if (line.startsWith("//")) {
					commentLines++;
				} else if (line.startsWith("/*") && !line.endsWith("*/")) {
					commentLines++;
					flag = true;
				} else if (line.startsWith("/*") && line.endsWith("*/")) {
					commentLines++;
				} else if (flag == true) {
					commentLines++;
					if (line.endsWith("*/")) {
						flag = false;
					}
				} else {
					codeLines++;
				}
			}
			files++;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}