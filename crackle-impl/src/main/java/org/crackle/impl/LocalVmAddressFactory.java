package org.crackle.impl;

import org.crackle.Address;

/**
 * Creates addresses suitable for use within a single VM.  They are unforgeable
 * due to the protections offered by the Java VM.  However, they are not globally
 * unique and are not suitable for distributed processing.
 * @author Chad
 */
public class LocalVmAddressFactory implements AddressFactory {

    @Override
    public Address createAddress() {
        return new LocalVmAddress();
    }

    private static final class LocalVmAddress implements Address {

    }

}
