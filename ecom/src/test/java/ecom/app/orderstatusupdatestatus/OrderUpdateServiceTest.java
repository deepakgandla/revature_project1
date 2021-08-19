package ecom.app.orderstatusupdatestatus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.update.OrderStatusUpdateService;
import com.app.service.update.impl.OrderStatusUpdateServiceImpl;

class OrderUpdateServiceTest {
    OrderStatusUpdateService orderStatusUpdateService = new OrderStatusUpdateServiceImpl();
	@Test
	void test() throws BusinessException {
		assertEquals(0, orderStatusUpdateService.orderStatusUpdateShipped(6));
	}

}
