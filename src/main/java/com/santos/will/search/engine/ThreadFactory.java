package com.santos.will.search.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFactory {

	private static final ThreadFactory FACTORY = new ThreadFactory();
	private final ExecutorService pool = Executors.newFixedThreadPool(10);

	private ThreadFactory() {
	}

	public static ThreadFactory instance() {
		return FACTORY;
	}

	public ExecutorService getPool() {
		return pool;
	}
}
