package com.adobe.aem.guides.AEMTasks.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,adapters = HeaderSlingModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderSlingModel {
    
    @ValueMapValue
    private String pathField;
    
    @ValueMapValue
    private String textField;
    
    @ValueMapValue
    private String checkbox;
    
    @ChildResource
    private List<HeaderChild> multiField;

    public List<HeaderChild> getMultiField() {
        return multiField;
    }

    public String getPathField() {
        return pathField;
    }

    public String getTextField() {
        return textField;
    }

    public String getCheckBox() {
        return checkbox;
    }
}