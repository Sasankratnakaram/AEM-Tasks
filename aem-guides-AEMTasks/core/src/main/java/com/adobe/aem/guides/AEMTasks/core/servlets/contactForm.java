package com.adobe.aem.guides.AEMTasks.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;   
import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
           property = {
               "sling.servlet.methods=POST",
               "sling.servlet.paths=/bin/contactFormServlet"
           })
@ServiceDescription("Contact Form Servlet")
public class contactForm extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(contactForm.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            String payload = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);    
            JSONObject json = new JSONObject(payload);

            String name = json.getString("name");
            String email = json.getString("email");
            String mobile = json.getString("mobile"); 

            Session session = request.getResourceResolver().adaptTo(Session.class);
            Node rootNode = session.getRootNode();
            Node contactNode = rootNode.addNode("contacts", JcrConstants.NT_UNSTRUCTURED);

            Node newContact = contactNode.addNode(name, JcrConstants.NT_UNSTRUCTURED);
            newContact.setProperty("name", name);
            newContact.setProperty("email", email);
            newContact.setProperty("mobile", mobile);

            session.save();
            response.getWriter().write("Contact saved successfully!");

        } catch (Exception e) {
            LOG.error("Error while saving contact data", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}