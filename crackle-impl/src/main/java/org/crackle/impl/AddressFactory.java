package org.crackle.impl;

import org.crackle.Address;

/**
 * Creates addresses.
 * @author Chad
 */
public interface AddressFactory {
    /**
     * Create a new address.
     * @return A new address, never null.
     */
    Address createAddress();
}
