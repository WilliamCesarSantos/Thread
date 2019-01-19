package com.santos.will.search.engine.file;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

import com.santos.will.search.api.Search;
import com.santos.will.search.engine.ThreadFactory;

/**
 * Executa a busca em arquivos utilizando recursos de threads
 * 
 * @author William
 * @since 2016-10-25
 * @version 1.0.0
 */
public class FileSearch implements Search<String> {

	private static final String EXPECTED_EXTENSION = ".txt";

	private final File root;
	private final ThreadFactory threadFactory;
	private final Collection<String> response = new ConcurrentLinkedQueue<>();
	private final Collection<Callable<Void>> calls = new ConcurrentLinkedQueue<>();

	public FileSearch(final File root, final ThreadFactory threadFactory) {
		Objects.requireNonNull(root, "root cannot be null");
		Objects.requireNonNull(threadFactory, "threadFactory cannot be null");
		this.root = root;
		this.threadFactory = threadFactory;
	}

	@Override
	public Collection<String> search(String query) {
		if (!this.root.exists()) {
			throw new IllegalStateException("Not found root directory");
		}
		try {
			search(this.root, query);
			this.threadFactory.getPool().invokeAll(calls);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return this.response;
	}

	private void search(final File root, final String query) {
		Optional.ofNullable(root.listFiles()).map(Arrays::stream)
				.orElse(Stream.empty())
				.forEach(file -> {
					if (file.getName().endsWith(EXPECTED_EXTENSION)) {
						this.calls.add(() -> {
							final ReaderFile reader = new ReaderFile(file);
							if (reader.containsText(query)) {
								this.response.add(file.getAbsolutePath());
							}
							return null;
						});
					} else if (file.isDirectory()) {
						search(file, query);
					}
				});
	}

}
