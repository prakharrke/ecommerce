package com.prakhar.utils;

import javax.ws.rs.core.NewCookie;

public class WebUtils {
    public static NewCookie createNewCookie(String name, String value) {
        return new NewCookie(name, value, "/", null, null, NewCookie.DEFAULT_MAX_AGE, false);
    }
}
