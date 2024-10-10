package com.adobe.aem.guides.AEMTasks.core.services;

import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@Component(
    service = FirstNameService.class,
    immediate = true
)
public class FirstNameService {

    private static final Logger LOG = LoggerFactory.getLogger(FirstNameService.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private static final String DEFAULT_PATH = "/content/AEMTasks/us/Sasi/jcr:content";

    /**
     * Adds the first name property with the value 'Sasank' at the specified path in CRXDE.
     *
     * @param path the path where the property should be added (if null, uses default path)
     */
    public void addFirstName(String path) {
        ResourceResolver resolver = null;

        // Use default path if path is null or empty
        if (path == null || path.isEmpty()) {
            path = DEFAULT_PATH;
        }

        try {
            resolver = getResourceResolver();
            if (resolver != null) {
                Resource resource = resolver.getResource(path);
                if (resource != null) {
                    ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                    if (properties != null) {
                        properties.put("firstName", "Sasank");
                        resolver.commit();
                        LOG.info("First name 'Sasank' added at path '{}'", path);
                    } else {
                        LOG.warn("Failed to adapt resource to ModifiableValueMap for path: {}", path);
                    }
                } else {
                    LOG.error("Resource not found at path '{}'", path);
                }
            }
        } catch (PersistenceException e) {
            LOG.error("Error committing changes for path {}: {}", path, e.getMessage(), e);
        } finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }

    /**
     * Helper method to obtain a ResourceResolver using the service user 'sasankr'.
     *
     * @return ResourceResolver or null if it could not be obtained
     */
    private ResourceResolver getResourceResolver() {
        ResourceResolver resolver = null;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put(ResourceResolverFactory.SUBSERVICE, "sasankr");
            resolver = resourceResolverFactory.getServiceResourceResolver(param);

            if (resolver != null) {
                LOG.info("ResourceResolver obtained successfully for service user 'sasankr'");
            } else {
                LOG.error("ResourceResolver is null. Check if the service user mapping is correctly set.");
            }
        } catch (LoginException e) {
            LOG.error("Failed to obtain ResourceResolver for service user 'sasankr': {}", e.getMessage(), e);
        }
        return resolver;
    }
}