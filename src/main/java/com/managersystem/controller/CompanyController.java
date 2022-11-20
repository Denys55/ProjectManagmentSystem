package com.managersystem.controller;

import com.managersystem.model.dto.CompanyDto;
import com.managersystem.service.impl.CompanyService;
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

@WebServlet(urlPatterns = "/company")
public class CompanyController extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init(ServletConfig config) {
        companyService = (CompanyService) config.getServletContext().getAttribute(Attribute.COMPANY_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDto> companies = companyService.findAll();
        req.setAttribute(Attribute.COMPANIES, companies);
        UtilController.forwardToPage(req, resp, Page.COMPANIES);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDto companyDto = mapCompanyDto(req);
        String deleteOrNot = req.getParameter(Parameter.DELETE);
        String createOrNot = req.getParameter(Parameter.CREATE);
        if (Objects.nonNull(deleteOrNot)) {
            companyService.delete(companyDto);
        } else if (Objects.nonNull(createOrNot)) {
            companyService.save(mapCompanyDto(req));
        } else {
            companyService.update(companyDto);
        }

        doGet(req, resp);
    }

    private CompanyDto mapCompanyDto(HttpServletRequest request) {
        CompanyDto companyDto = new CompanyDto();
        String parameterId = request.getParameter(Parameter.ID);
        if (Objects.nonNull(parameterId)) {
            companyDto.setId(Integer.parseInt(parameterId));
        }
        companyDto.setName(request.getParameter(Parameter.NAME));
        companyDto.setCountry(request.getParameter(Parameter.COUNTRY));
        return companyDto;
    }
}
