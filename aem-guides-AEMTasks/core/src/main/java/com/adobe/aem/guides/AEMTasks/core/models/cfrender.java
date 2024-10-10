package com.adobe.aem.guides.AEMTasks.core.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class cfrender {

    private static final Logger logger = LoggerFactory.getLogger(cfrender.class);

    @Self
    private Resource resource;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String cfPath;
    private Optional<ContentFragment> contentFragment;

    private String fname;
    private String jd;
    private String cbox;
    

    @PostConstruct
    public void init() {
        cfPath = "/content/dam/task/taskccf";
        Resource fragmentResource = resourceResolver.getResource(cfPath);
        contentFragment = Optional.ofNullable(fragmentResource.adaptTo(ContentFragment.class));

        if (contentFragment.isPresent()) {
            ContentFragment cf = contentFragment.get();

            fname = getElementContent("fname");
            jd   = getElementContent("jd");
            cbox = getElementContent("cbox");
            
        } else {
            logger.warn("Content Fragment not found at path: {}", cfPath);
        }
    }

    private String getElementContent(String elementName) {
        return contentFragment.map(cf -> cf.getElement(elementName))
                .map(ContentElement::getContent)
                .orElse(StringUtils.EMPTY);
    }

    public String getCfPath() {
        return cfPath;
    }

    public String getFname() {
        return fname;
    }

    public String getJd() {
        return jd;
    }

    public String getCbox() {
        return cbox;
    }
   
}
