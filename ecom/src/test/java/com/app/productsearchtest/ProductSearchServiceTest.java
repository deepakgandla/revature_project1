package com.app.productsearchtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.search.ProductSearchService;
import com.app.service.search.impl.ProductSearchServiceImpl;

class ProductSearchServiceTest {
    ProductSearchService productSearchService = new ProductSearchServiceImpl();
	@Test
	void productSearchTest() throws BusinessException {
		assertEquals(1, productSearchService.searchProduct("ball").size());
	}

}
