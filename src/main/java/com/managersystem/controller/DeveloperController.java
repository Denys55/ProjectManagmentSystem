package com.managersystem.controller;

import com.managersystem.model.dto.CustomerDto;
import com.managersystem.model.dto.DeveloperDto;
import com.managersystem.service.impl.CustomerService;
import com.managersystem.service.impl.DeveloperService;
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

@WebServlet(urlPatterns = "/developer")
public class DeveloperController extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init(ServletConfig config) {
        developerService = (DeveloperService) config.getServletContext().getAttribute(Attribute.DEVELOPER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDto> developers = developerService.findAll();
        req.setAttribute(Attribute.DEVELOPERS, developers);
        UtilController.forwardToPage(req, resp, Page.DEVELOPERS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto developerDto = mapDeveloperDto(req);
        String deleteOrNot = req.getParameter(Parameter.DELETE);
        String createOrNot = req.getParameter(Parameter.CREATE);
        if (Objects.nonNull(deleteOrNot)) {
            developerService.delete(developerDto);
        } else if (Objects.nonNull(createOrNot)) {
            developerService.save(mapDeveloperDto(req));
        } else {
            developerService.update(developerDto);
        }

        doGet(req, resp);
    }

    private DeveloperDto mapDeveloperDto(HttpServletRequest request) {
        DeveloperDto developerDto = new DeveloperDto();
        String parameterId = request.getParameter(Parameter.ID);
        if (Objects.nonNull(parameterId)) {
            developerDto.setId(Integer.parseInt(parameterId));
        }
        developerDto.setName(request.getParameter(Parameter.NAME));
        developerDto.setAge(Integer.parseInt(request.getParameter(Parameter.AGE)));
        developerDto.setSalary(Integer.parseInt(request.getParameter(Parameter.SALARY)));
        return developerDto;
    }
}
