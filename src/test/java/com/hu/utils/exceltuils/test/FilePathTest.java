package com.hu.utils.exceltuils.test;

import java.io.File;

import org.junit.Test;

public class FilePathTest {
	
	@Test
	public void filePathTest() {
		
		System.out.println(new File("").getAbsolutePath());
		System.out.println(new File("aa").getAbsolutePath());
		
		System.out.println(new File("/").getAbsolutePath());
		System.out.println(new File("/aa").getAbsolutePath());
		
		System.out.println(new File(".").getAbsolutePath());
		
		System.out.println(this.getClass().getClassLoader().getResource(""));
		System.out.println(this.getClass().getClassLoader().getResource("config/excelutils-config.xml"));
		
		System.out.println(this.getClass().getClassLoader().getResource("/"));
		System.out.println(this.getClass().getClassLoader().getResource("/bb"));
		
		System.out.println(this.getClass().getClassLoader().getResource("."));
		System.out.println(this.getClass().getClassLoader().getResource("./config"));
	}
}
