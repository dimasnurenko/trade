package com.trade.core.domain.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product_image")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column
	private String url = "";
	@Column(name = "product_id")
	private Long productId;

	@Column
	private boolean main = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
}
