package com.prakhar.web;

import com.prakhar.model.Product;
import com.prakhar.model.TypeProduct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminCreateProductsView extends AbstractView {

    TypeProduct productType;
    AdminCreateProductsView view;
    private static final Map<String, String> ERROR_MAP = new HashMap<>();
    private String message;
    static {
        ERROR_MAP.put("error", "Failed to create product. Please make sure that that product number is unique.");
        ERROR_MAP.put("success", "Product successfully created.");
    }

    public AdminCreateProductsView(String templateName, String message) {
        super(templateName);
        view = this;
        this.message = message;
    }

    public List<String> getProductTypes() {
        return Arrays.stream(TypeProduct.values())
                .map(TypeProduct::getDisplayName)
                .collect(Collectors.toList());
    }

    public AdminCreateProductsView getView() {
        return view;
    }

    public String getMessage() {
        if (message == null) {
            return null;
        }
        if (ERROR_MAP.get(message) != null) {
            return ERROR_MAP.get(message);
        } else {
            return null;
        }

    }
}
