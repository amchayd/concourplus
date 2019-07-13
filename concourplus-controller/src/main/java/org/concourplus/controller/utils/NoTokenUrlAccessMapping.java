package org.concourplus.controller.utils;

public interface NoTokenUrlAccessMapping {

	String REST_URL_ROOT = "/concourplus-controller/rest";

	String[] ACCESS = new String[] { REST_URL_ROOT + "/auth/login"};

}
