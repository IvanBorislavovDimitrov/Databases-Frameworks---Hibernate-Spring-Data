package app.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class SoldProducts {

    private int productsCount;
    private List<ProductShortInfo> products;

    public SoldProducts() {
        this.products = new ArrayList<>();
    }

    public SoldProducts(int productsCount, List<ProductShortInfo> products) {
        this.productsCount = productsCount;
        this.products = products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public List<ProductShortInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductShortInfo> products) {
        this.products = products;
    }
}
