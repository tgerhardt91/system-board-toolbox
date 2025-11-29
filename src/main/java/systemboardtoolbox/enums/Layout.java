package systemboardtoolbox.enums;

public enum Layout {
    TENSION_2_MIRROR(10),
    TENSION_2_SPRAY(11),
    UNKNOWN(0);

    public Integer getId() {
        return layoutId;
    }

    private Integer layoutId;

    private Layout(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public static Layout fromId(Integer id) {
        for(var layout : Layout.values()) {
            if(layout.layoutId.equals(id)) {
                return layout;
            }
        }

        return UNKNOWN;
    }
}
