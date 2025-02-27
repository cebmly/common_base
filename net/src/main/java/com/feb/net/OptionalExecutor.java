package com.feb.net;

import java.util.concurrent.Executor;

public class OptionalExecutor implements Executor {
    private static final OptionalExecutor EXECUTOR = new OptionalExecutor();

    public OptionalExecutor() {
    }

    public static OptionalExecutor get() {
        return EXECUTOR;
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}