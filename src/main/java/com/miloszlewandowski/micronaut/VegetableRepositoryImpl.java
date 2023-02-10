package com.miloszlewandowski.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.microstream.RootProvider;
import io.micronaut.microstream.annotations.StoreParams;
import io.micronaut.microstream.annotations.StoreReturn;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Map;

@Singleton
public class VegetableRepositoryImpl implements VegetableRepository {

    private final RootProvider<VegetableContainer> rootProvider;

    VegetableRepositoryImpl(RootProvider<VegetableContainer> rootProvider) {
        this.rootProvider = rootProvider;
    }

    @Override
    @NonNull
    public Collection<Vegetable> getList() {
        return rootProvider.root().getVegetables().values();
    }

    @Override
    @NonNull
    public Vegetable create(@NonNull @NotNull @Valid VegetableCommand vegetable) throws VegetableDuplicateException {
        Map<String, Vegetable> vegetables = rootProvider.root().getVegetables();
        if (vegetables.containsKey(vegetable.getName())) {
            throw new VegetableDuplicateException(vegetable.getName());
        }
        return performCreate(vegetables, vegetable);
    }

    @StoreParams("vegetables")
    protected Vegetable performCreate(Map<String, Vegetable> vegetables, VegetableCommand vegetable) {
        Vegetable newVegetable = new Vegetable(vegetable.getName(), vegetable.getDescription());
        vegetables.put(vegetable.getName(), newVegetable);
        return newVegetable;
    }

    @Nullable
    public Vegetable update(@NonNull @NotNull @Valid VegetableCommand vegetable) {
        Map<String, Vegetable> vegetables = rootProvider.root().getVegetables();
        Vegetable foundVegetable = vegetables.get(vegetable.getName());
        if (foundVegetable != null) {
            return performUpdate(foundVegetable, vegetable);
        }
        return null;
    }

    @StoreReturn
    protected Vegetable performUpdate(@NonNull Vegetable foundVegetable, @NonNull VegetableCommand vegetable) {
        foundVegetable.setDescription(vegetable.getDescription());
        return foundVegetable;
    }

    @Override
    @Nullable
    public Vegetable find(@NonNull @NotBlank String name) {
        return rootProvider.root().getVegetables().get(name);
    }

    @Override
    public void delete(@NonNull @NotNull @Valid VegetableCommand vegetable) {
        performDelete(vegetable);
    }

    @StoreReturn
    protected Map<String, Vegetable> performDelete(VegetableCommand vegetable) {
        if (rootProvider.root().getVegetables().remove(vegetable.getName()) != null) {
            return rootProvider.root().getVegetables();
        }
        return null;
    }
}
