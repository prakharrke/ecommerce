package com.prakhar.web;

import java.util.HashMap;
import java.util.Map;

public class SignUpView extends AbstractView {

    private static final Map<String, String> ERROR_MAP = new HashMap<>();

    static {
        ERROR_MAP.put("already.registered", "You are already registered with us. Please Log In.");
    }

    private String message;
    private SignUpView view;

    public SignUpView(String templateName, String message) {
        super(templateName);
        this.message = message;
        this.view = this;
    }

    public String getMessage() {
        if(message == null) {
            return null;
        }
        if(ERROR_MAP.containsKey(message)){
            return ERROR_MAP.get(message);
        }else{
            return null;
        }
    }

    public SignUpView getView() {
        return view;
    }
}
