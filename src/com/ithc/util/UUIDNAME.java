package com.ithc.util;

import java.util.UUID;

public class UUIDNAME {
	
	public static String getUUIDNAME(String name){
		return UUID.randomUUID().toString().replaceAll("-", "")+name.substring(name.indexOf("."), name.length());
	}

}
