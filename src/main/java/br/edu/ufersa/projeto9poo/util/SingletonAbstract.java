package br.edu.ufersa.projeto9poo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class SingletonAbstract {
    private static final Map<Class<?>, Object> INSTANCES = new ConcurrentHashMap<>();

    protected SingletonAbstract() {

    }

    protected static <T> T pegarInstancia(Class<T> classes, Supplier<T> criarInstancia) {
        Object instance = INSTANCES.computeIfAbsent(classes, k -> {
            System.out.println("Criando o " + k.getName());
            return criarInstancia.get();
        });
        return classes.cast(instance);
    }
}
