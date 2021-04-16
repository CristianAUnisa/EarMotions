package it.unisa.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<PurchaseBean> products;

	public Cart() {
		products = new ArrayList<PurchaseBean>();
	}

	public synchronized void addProduct(ProductBean product) {
		PurchaseBean purchase;
		for (int i = 0; i < products.size(); i++) {
			purchase = (PurchaseBean) products.get(i);
			if (purchase.getCode() == product.getCode()) {
				if (purchase.getNumItems() < product.getQuantity()) {
					purchase.incrementNumItems();
					return;
				}
				else return;
			}
		}
		PurchaseBean newOrder = new PurchaseBean(product);
		products.add(newOrder);
	}

	public void deleteProduct(ProductBean product) {
		for (PurchaseBean prod : products) {
			if (prod.getCode() == product.getCode()) {
				products.remove(prod);
				break;
			}
		}
	}

	public List<PurchaseBean> getProducts() {
		return products;
	}

	public void emptyCart() {
		products = new ArrayList<PurchaseBean>();
	}
}
