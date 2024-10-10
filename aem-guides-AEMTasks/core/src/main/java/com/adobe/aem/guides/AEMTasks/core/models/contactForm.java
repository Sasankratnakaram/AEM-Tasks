package com.adobe.aem.guides.AEMTasks.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unused")
@Model(adaptables = Resource.class, adapters = { contactForm.class, ComponentExporter.class }, resourceType = "your-project/components/contactTable")
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class contactForm {

    @Self
    private Resource resource;

    @ValueMapValue @Optional
    private String name;

    @ValueMapValue @Optional
    private String email;

    @ValueMapValue @Optional
    private String mobile;

    private List<contactForm> contacts;

    @PostConstruct
    protected void init() {
        contacts = new ArrayList<>();
        Resource contactsResource = resource.getChild("contacts");
        if (contactsResource != null) {
            Iterator<Resource> contactResources = contactsResource.listChildren();
            while (contactResources.hasNext()) {
                Resource contactResource = contactResources.next();
                contacts.add(contactResource.adaptTo(contactForm.class));
            }
        }
    }

    public List<contactForm> getContacts() {
        return contacts;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
