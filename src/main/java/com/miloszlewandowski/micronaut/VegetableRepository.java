package com.miloszlewandowski.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import javax.validation.Valid;

interface VegetableRepository {

    @NonNull
    Collection<Vegetable> getList();

    @NonNull
    Vegetable create(@NonNull @NotNull @Valid VegetableCommand vegetable)
            throws VegetableDuplicateException;

    @Nullable
    Vegetable update(@NonNull @NotNull @Valid VegetableCommand vegetable);

    @Nullable
    Vegetable find(@NonNull @NotBlank String name);

    void delete(@NonNull @NotNull @Valid VegetableCommand vegetable);
}