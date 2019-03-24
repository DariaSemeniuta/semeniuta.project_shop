package com.semeniuta.validators;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidationServiceImpl implements ValidationService {


    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public ValidationServiceImpl(ClientService clientService, ProductService productService, OrderService orderService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void validateAge(int age) throws BusinessExceptions {
        if (age < 0 || age > 200) {
            throw new BusinessExceptions("Incorrect age!");
        }
    }

    @Override
    public boolean readInt(String inputInt) {
        try {
            new Integer(inputInt);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void readPhone(String inputPhone) throws BusinessExceptions {
        String[] regex = {"^(\\+38067)\\d{7}", "^(\\+38097)\\d{7}", "^(\\+38050)\\d{7}", "^(067)\\d{7}", "^(097)\\d{7}", "^(050)\\d{7}"};
        for (int i = 0; i < regex.length; ++i) {
            if (inputPhone.matches(regex[i])) {
                return;
            }
        }
        throw new BusinessExceptions("Invalid phone number!");
    }

    @Override
    public void readEmail(String inputEmail) throws BusinessExceptions {

        String regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        if (!(inputEmail.matches(regex))) {
            throw new BusinessExceptions("Invalid email address");
        }

    }

    @Override
    public void validateClient(String phone) throws BusinessExceptions {
        if (clientService.isClientExist(phone)) {
            throw new BusinessExceptions("Client is already exist");
        }

    }

    @Override
    public void validateProductId(long id) throws BusinessExceptions {
        if (!(productService.isProductExist(id))) {
            throw new BusinessExceptions("There is no such product!");
        }
    }

    @Override
    public void validateClientId(long id) throws BusinessExceptions {
        if (!clientService.isIdExist(id)) {
            throw new BusinessExceptions("There is no client with entered ID!");
        }
    }

    @Override
    public void validatePrice(String input) throws BusinessExceptions {
        try {
            new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new BusinessExceptions("Price is incorrect!");
        }
    }

    @Override
    public void validateOrderId(long id) throws BusinessExceptions {
        if (!orderService.isOrderExist(id)) {
            throw new BusinessExceptions("There is no order with entered id!");
        }
    }
}
