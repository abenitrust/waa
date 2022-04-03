package com.waa.lab.helper;

import com.waa.lab.domain.User;

public class Factory {
    private static User principle;
    static {
        principle = new User();
        principle.setName("Static Principle");
        principle.setId(1000);  // initialized in db manually
    }
    public static User getPrinciple() {
        return principle;
    }
}
