package ecom.app.productcreatetest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.service.create.ProductCreateService;
import com.app.service.create.impl.ProductCreateServiceImpl;

class ProductCreateServiceTest {
    ProductCreateService productCreateService = new ProductCreateServiceImpl();
    
	@Test
	void test() throws BusinessException {
		assertEquals(1, productCreateService.createProduct(new Product("table", "kitchen", 550.00f)));
	}

}
