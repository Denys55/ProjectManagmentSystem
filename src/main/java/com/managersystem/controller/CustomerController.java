package com.managersystem.controller;

import com.managersystem.model.dto.CustomerDto;
import com.managersystem.service.impl.CustomerService;
import com.managersystem.util.constants.Attribute;
import com.managersystem.util.constants.Page;
import com.managersystem.util.constants.Parameter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init(ServletConfig config) {
        customerService = (CustomerService) config.getServletContext().getAttribute(Attribute.CUSTOMER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDto> customers = customerService.findAll();
        req.setAttribute(Attribute.CUSTOMERS, customers);
        UtilController.forwardToPage(req, resp, Page.CUSTOMERS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = mapCustomerDto(req);
        String deleteOrNot = req.getParameter(Parameter.DELETE);
        String createOrNot = req.getParameter(Parameter.CREATE);
        if (Objects.nonNull(deleteOrNot)) {
            customerService.delete(customerDto);
        } else if (Objects.nonNull(createOrNot)) {
            customerService.save(mapCustomerDto(req));
        } else {
            customerService.update(customerDto);
        }

        doGet(req, resp);
    }

    private CustomerDto mapCustomerDto(HttpServletRequest request) {
        CustomerDto customerDto = new CustomerDto();
        String parameterId = request.getParameter(Parameter.ID);
        if (Objects.nonNull(parameterId)) {
            customerDto.setId(Integer.parseInt(parameterId));
        }
        customerDto.setName(request.getParameter(Parameter.NAME));
        customerDto.setBudget(Integer.parseInt(request.getParameter(Parameter.BUDGET)));
        return customerDto;
    }
}
