package org.concourplus.controller.utils;

public interface ErrorUrlMapping {

	String ACCESS_DENIED = "access-denied";

	String ACCESS_DENIED_ROOT = "/concourplus-controller/ws/access-denied";

	String NO_TOKEN = ACCESS_DENIED_ROOT + "/no-token";

	String NO_AUTHORITY = ACCESS_DENIED_ROOT + "/no-authority";

}
