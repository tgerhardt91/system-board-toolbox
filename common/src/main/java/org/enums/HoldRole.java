package org.enums;

public enum HoldRole {
    START(5),
    FINISH(7),
    MIDDLE(6),
    FOOT(8);

    private Integer id;

    private HoldRole(Integer id) {
        this.id = id;
    }
}
