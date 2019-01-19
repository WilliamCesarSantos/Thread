package com.santos.will.search.engine.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

class ReaderFile {

	private final File file;

	public ReaderFile(final File file) {
		Objects.requireNonNull(file, "file cannot be null");
		this.file = file;
	}

	public boolean containsText(final String text) throws IOException {
		Objects.requireNonNull(text, "containsText cannot be null");
		try (final Stream<String> stream = Files.lines(Paths.get(this.file.getAbsolutePath()))){
			return stream.map(String::toLowerCase)
				.anyMatch(line -> line.contains(text.toLowerCase()));
		}
	}

}
