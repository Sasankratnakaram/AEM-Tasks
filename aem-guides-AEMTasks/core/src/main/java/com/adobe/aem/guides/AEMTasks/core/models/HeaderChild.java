package com.adobe.aem.guides.AEMTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderChild {
    
    @ValueMapValue
    private String datePicker;
    @ValueMapValue
    private String multiText;

    public String getMultiText() {
        return multiText;
    }

    public String getDatePicker() {
        return datePicker;
    }
}