package com.miloszlewandowski.micronaut;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public class VegetableCommand {

    @NonNull
    @NotBlank
    private final String name;

    @Nullable
    private final String description;

    public VegetableCommand(@NonNull String name) {
        this(name, null);
    }

    @Creator
    public VegetableCommand(@NonNull String name,
                            @Nullable String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}