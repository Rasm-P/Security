/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

    private String username;
    private List<String> roles = new ArrayList<>();

    /* Create a UserPrincipal, given the Entity class User*/
    public UserPrincipal(User user) {
        this.username = user.getUserName();
        this.roles = user.getRolesAsStrings();
    }

    public UserPrincipal(String username, String... roles) {
        super();
        this.username = username;
        this.roles = Arrays.asList(roles);
    }

    @Override
    public String getName() {
        return username;
    }

    public boolean isUserInRole(String role) {
        return this.roles.contains(role);
    }
}
