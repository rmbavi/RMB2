package com.example.rmb.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	@SuppressWarnings("unused")
	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}

			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}

}

