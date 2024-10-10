package com.adobe.aem.guides.AEMTasks.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.FragmentData;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class)
public class contentfragmentmodel {

    @Self
    private Resource resource;

    public String getFirstName() {
        return getFragmentFieldValue("firstName"); // 'firstName' is the name you gave for the field in the model
    }

    public String getJobDescription() {
        return getFragmentFieldValue("jobDescription"); // 'jobDescription' is the name of the multiline text field
    }

    public boolean isCBoxChecked() {
        return getFragmentFieldValue("cBox").equalsIgnoreCase("true"); // Assuming checkbox stores "true" or "false"
    }

    // Helper method to retrieve a specific fragment field
    private String getFragmentFieldValue(String fieldName) {
        ContentFragment fragment = resource.adaptTo(ContentFragment.class);
        if (fragment != null) {
            ContentElement element = fragment.getElement(fieldName);
            if (element != null) {
                FragmentData data = element.getValue();
                return data != null ? data.getContentType() : "No content found";
            }
        }
        return "Field not found";
    }
}
