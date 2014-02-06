package org.motechproject.mds.enhancer;

import org.datanucleus.api.jdo.JDOEnhancer;
import org.motechproject.mds.builder.EnhancedClassData;
import org.motechproject.mds.builder.EntityMetadataBuilder;
import org.motechproject.mds.domain.EntityMapping;
import org.motechproject.server.config.SettingsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jdo.metadata.JDOMetadata;
import java.io.IOException;

import static org.motechproject.mds.constants.Constants.Config;

/**
 * The <code>MdsJDOEnhancer</code> class is a wrapper for
 * {@link org.datanucleus.api.jdo.JDOEnhancer} class. Its task is to add the missing information
 * into created entity class.
 */
@Component
public class MdsJDOEnhancer extends JDOEnhancer {

    @Autowired
    private EntityMetadataBuilder metadataBuilder;

    @Autowired
    public MdsJDOEnhancer(SettingsFacade settingsFacade) {
        super(settingsFacade.getProperties(Config.DATANUCLEUS_FILE));

        setVerbose(true);
    }

    public EnhancedClassData enhance(EntityMapping mapping, byte[] originalBytes, ClassLoader tmpClassLoader)
            throws IOException {
        String className = mapping.getClassName();

        setClassLoader(tmpClassLoader);

        JDOMetadata metadata = metadataBuilder.createBaseEntity(newMetadata(), mapping);

        registerMetadata(metadata);
        addClass(className, originalBytes);
        enhance();

        return new EnhancedClassData(className, getEnhancedBytes(className), metadata);
    }
}