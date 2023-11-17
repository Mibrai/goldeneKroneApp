package com.rest.goldenekrone.userManagement.entities;

public enum UserGroup {
    VISITOR(1), // acces = 1
    USER(2), // acces = 2
    ADMIN(4); // acces = 4

    public final int acces;

    UserGroup( int acces) {
        this.acces = acces;
    }

    public String matchGroup(int value){
        String group_name = null;

        for(UserGroup group : UserGroup.values()){
            group_name = ( value == group.acces) ? group.name() : UserGroup.VISITOR.name();
        }
        return group_name;
    }
}
