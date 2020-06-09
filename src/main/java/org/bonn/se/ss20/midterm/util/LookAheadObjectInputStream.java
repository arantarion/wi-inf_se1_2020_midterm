package org.bonn.se.ss20.midterm.util;

import java.io.*;
import java.util.Set;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class LookAheadObjectInputStream extends ObjectInputStream {

    private final Set whitelist;

    public LookAheadObjectInputStream(InputStream inputStream, Set wl) throws IOException {
        super(inputStream);
        whitelist = wl;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass cls) throws IOException, ClassNotFoundException {
        if (!whitelist.contains(cls.getName())) {
            throw new InvalidClassException("Unexpected serialized class", cls.getName());
        }
        return super.resolveClass(cls);
    }

}



