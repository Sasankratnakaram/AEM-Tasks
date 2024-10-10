package com.adobe.aem.guides.AEMTasks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BmcFooter {

    // Multifield - A list of objects holding both linkLabel and navigationUrl
    @ChildResource(name = "multiField")
    private List<Resource> multiFieldItems;

    @ValueMapValue(name = "fileReference")
    private String fileReference; // Path to the selected logo from DAM

    @ValueMapValue(name = "logoLink")
    private String logoLink; // URL for logo redirection

    @ValueMapValue(name = "text")
    private String richText; // Rich text description

    public List<MultifieldItem> getMultiFieldItems() {
        if (multiFieldItems != null) {
            return StreamSupport.stream(multiFieldItems.spliterator(), false)
                    .map(item -> new MultifieldItem(
                            item.getValueMap().get("linkLabel", String.class),
                            item.getValueMap().get("navigationUrl", String.class)
                    )).collect(Collectors.toList());
        }
        return null;
    }

    public String getFileReference() {
        return fileReference;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public String getRichText() {
        return richText;
    }

    // Class to represent a single item in the multifield
    public static class MultifieldItem {
        private String linkLabel;
        private String navigationUrl;

        public MultifieldItem(String linkLabel, String navigationUrl) {
            this.linkLabel = linkLabel;
            this.navigationUrl = navigationUrl;
        }

        public String getLinkLabel() {
            return linkLabel;
        }

        public String getNavigationUrl() {
            return navigationUrl;
        }
    }
}
