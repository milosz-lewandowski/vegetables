package com.miloszlewandowski.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import java.util.Collection;

import static io.micronaut.scheduling.TaskExecutors.IO;

@Controller("/vegetables")
class VegetableController {

    private final VegetableRepository vegetableRepository;

    VegetableController(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @Get
    Collection<Vegetable> getList() {
        return vegetableRepository.getList();
    }

    @ExecuteOn(IO)
    @Post
    @Status(HttpStatus.CREATED)
    Vegetable create(@NonNull @Valid @Body VegetableCommand vegetable) {
        return vegetableRepository.create(vegetable);
    }

    @Put
    Vegetable update(@NonNull @Valid @Body VegetableCommand vegetable) {
        return vegetableRepository.update(vegetable);
    }

    @Get("/{name}")
    Vegetable find(@PathVariable String name) {
        return vegetableRepository.find(name);
    }

    @ExecuteOn(IO)
    @Delete
    @Status(HttpStatus.NO_CONTENT)
    void delete(@NonNull @Valid @Body VegetableCommand vegetable) {
        vegetableRepository.delete(vegetable);
    }
}
