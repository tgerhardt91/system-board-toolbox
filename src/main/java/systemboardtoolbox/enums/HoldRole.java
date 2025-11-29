package systemboardtoolbox.enums;

public enum HoldRole {
    START(5),
    FINISH(7),
    MIDDLE(6),
    FOOT(8),
    UNKNOWN(0);

    private Integer id;

    private HoldRole(Integer id) {
        this.id = id;
    }

    public static HoldRole fromId(Integer id) {
        for(var holdRole : HoldRole.values()) {
            if(holdRole.id.equals(id)) {
                return holdRole;
            }
        }

        return UNKNOWN;
    }
}
