package com.adobe.aem.guides.AEMTasks.core.models;

import com.day.cq.dam.api.Asset;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageDropdownModel {

    @SlingObject
    private ResourceResolver resourceResolver;

    private List<String> imagePaths;
    
    // Hardcoded folder path (Modify this path as per your requirement)
    private static final String FOLDER_PATH = "/content/dam/AEMTasks/images"; 

    @PostConstruct
    protected void init() {
        imagePaths = new ArrayList<>();
        Resource folderResource = resourceResolver.getResource(FOLDER_PATH);
        
        if (folderResource != null) {
            Iterator<Resource> assets = folderResource.listChildren();
            while (assets.hasNext()) {
                Resource assetResource = assets.next();
                Asset asset = assetResource.adaptTo(Asset.class);
                if (asset != null && asset.getMimeType().startsWith("image/")) {
                    imagePaths.add(asset.getPath());  // Add image path to the list
                }
            }
        }
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }
}
