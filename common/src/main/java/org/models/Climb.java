package org.models;

import lombok.Builder;
import lombok.Data;
import org.enums.Layout;

import java.util.UUID;

@Data
@Builder
public class Climb {
    private UUID uuid;
    private String name;
    private Layout layout;
    private String setter;
    private String description;
    private String frames;
    private boolean noMatching;
}