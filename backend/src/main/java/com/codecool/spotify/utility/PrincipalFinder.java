package com.codecool.spotify.utility;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class PrincipalFinder {

    public static String getCurrentlyLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static boolean principleIsPresent() {
        boolean isPresent = false;
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!name.equals("anonymousUser")) isPresent = true;
        return isPresent;
    }
}
