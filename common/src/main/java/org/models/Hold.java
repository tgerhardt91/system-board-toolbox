package org.models;

import lombok.Builder;
import lombok.Data;
import org.enums.HoldRole;
import org.enums.Layout;

import java.util.UUID;

@Data
@Builder
public class Hold {
    private Integer id;
    private HoldRole defaultRole;
    private String readableLocation;
    private Integer x;
    private Integer y;
    private Integer mirroredHoldId;
}