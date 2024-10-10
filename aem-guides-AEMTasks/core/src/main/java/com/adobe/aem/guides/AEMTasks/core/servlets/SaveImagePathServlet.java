package com.adobe.aem.guides.AEMTasks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.paths=/bin/saveImagePath",
                "sling.servlet.methods=POST"
        }
)
public class SaveImagePathServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String imagePath = request.getParameter("imagePath");  // The image path sent via AJAX
        String savePath = "/content/AEMTasks/selectedImagePath";  // Modify this to your desired path

        try {
            Resource resource = request.getResourceResolver().getResource(savePath);

            if (resource != null) {
                ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                properties.put("selectedImage", imagePath);  // Save the image path under 'selectedImage'
                request.getResourceResolver().commit();  // Save the changes to the repository
            }

            response.setContentType("application/json");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "success");
            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Failed to save image path\"}");
        }
    }
}
