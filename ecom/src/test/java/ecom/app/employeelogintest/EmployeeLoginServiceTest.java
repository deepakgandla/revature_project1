package ecom.app.employeelogintest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.login.EmployeeLoginService;
import com.app.service.login.impl.EmployeeLoginServiceImpl;

class EmployeeLoginServiceTest {
    EmployeeLoginService employeeLoginService = new EmployeeLoginServiceImpl();
	@Test
	void employeeLoginTest() throws BusinessException {
		assertEquals("deepak", employeeLoginService.employeeLogin("admin@gmail.com", "admin12").getFirst_name());
	}

}
