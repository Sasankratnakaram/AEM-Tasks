package com.adobe.aem.guides.AEMTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ActivityCenterHero {
    
    @ValueMapValue
    private String heroTitle ;
    
    @ValueMapValue
    private String heroText;
    
    @ValueMapValue
    private String fileReference;

    public String getHeroTitle() {
        return heroTitle;
    }

    public String getHeroText() {
        return heroText;
    }

    public String getHeroImage() {
        return fileReference;
    }
}
    