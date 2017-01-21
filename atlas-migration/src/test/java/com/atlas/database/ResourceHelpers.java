package com.atlas.database;

import com.google.common.io.Resources;

import java.io.File;

public class ResourceHelpers {

    private ResourceHelpers() {
    }

    public static String resourceFilePath(String resourceClassPathLocation) {
        try {
            return (new File(Resources.getResource(resourceClassPathLocation).toURI())).getAbsolutePath();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
