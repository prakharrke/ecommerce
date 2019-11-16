package com.prakhar.web;

import com.prakhar.model.Product;
import com.prakhar.model.TypeProduct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminProductDetailsView extends AbstractView {
    private AdminProductDetailsView view;
    private  Product product;
    private static final Map<String, String> ERROR_MAP = new HashMap<>();
    private String message;
    static {
        ERROR_MAP.put("error", "Failed to updated product. Please make sure that that model number is unique.");
        ERROR_MAP.put("success", "Product successfully updated.");
    }
    public AdminProductDetailsView(String templateName, Product product, String message) {
        super(templateName);
        view = this;
        this.product = product;
        this.message = message;
    }

    public List<String> getProductTypes() {
        return Arrays.stream(TypeProduct.values())
                .map(TypeProduct::getDisplayName)
                .collect(Collectors.toList());
    }

    public AdminProductDetailsView getView() {
        return view;
    }

    public Product getProduct() {
        return product;
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
