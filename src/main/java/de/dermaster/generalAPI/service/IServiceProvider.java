package de.dermaster.generalAPI.service;

public interface IServiceProvider {

    default String getName() {
        return getClass().getSimpleName();
    }

}