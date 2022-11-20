package com.managersystem.listener;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.repository.impl.CompanyRepository;
import com.managersystem.service.impl.CompanyService;
import com.managersystem.service.impl.CustomerService;
import com.managersystem.service.impl.DeveloperService;
import com.managersystem.service.impl.ProjectService;
import com.managersystem.service.impl.SkillService;
import com.managersystem.util.constants.Attribute;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final DatabaseManagerConnector managerConnector = new DatabaseManagerConnector();
        createServices(sce, managerConnector);
    }

    private void createServices(ServletContextEvent sce, DatabaseManagerConnector managerConnector) {
        sce.getServletContext().setAttribute(Attribute.COMPANY_SERVICE, new CompanyService(managerConnector));
        sce.getServletContext().setAttribute(Attribute.CUSTOMER_SERVICE, new CustomerService(managerConnector));
        sce.getServletContext().setAttribute(Attribute.DEVELOPER_SERVICE, new DeveloperService(managerConnector));
        sce.getServletContext().setAttribute(Attribute.SKILL_SERVICE, new SkillService(managerConnector));
        sce.getServletContext().setAttribute(Attribute.PROJECT_SERVICE, new ProjectService(managerConnector));
    }
}
