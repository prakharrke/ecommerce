package com.prakhar.model;


import com.prakhar.utils.EnumUtils;

public enum TypeProduct {

    LAPTOP ("laptop");

    private String displayName;

    TypeProduct(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static TypeProduct fromDisplayName(String s) {
        return (TypeProduct) EnumUtils.lookupEnum(s, values(), "getDisplayName");
    }
}
