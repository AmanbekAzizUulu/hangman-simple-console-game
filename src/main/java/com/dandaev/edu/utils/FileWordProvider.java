package com.dandaev.edu.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.dandaev.edu.entities.Category;
import com.dandaev.edu.entities.Difficulty;
import com.dandaev.edu.entities.Word;

public final class FileWordProvider implements WordProvider {
	private final List<Word> words;
	private final Random random = new Random();

	public FileWordProvider(String resourcePath) {
		this.words = loadFromResource(resourcePath);
	}

	@Override
	public Optional<Word> getRandom(Category category, Difficulty difficulty) {
		List<Word> filtered = words.stream()
				.filter(w -> w.getCategory() == category)
				.filter(w -> w.getDifficulty() == difficulty)
				.collect(Collectors.toList());
		if (filtered.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(filtered.get(random.nextInt(filtered.size())));
	}

	private List<Word> loadFromResource(String resourcePath) {
		var is = FileWordProvider.class.getResourceAsStream(resourcePath);
		if (is == null) {
			throw new IllegalArgumentException("Resource not found: " + resourcePath);
		}

		try (var br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			List<Word> result = new ArrayList<Word>();

			String line;
			boolean firstLine = true;

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) {
					continue;
				}
				if (firstLine && line.toLowerCase().startsWith("text;")) {
					firstLine = false;
					continue;
				}
				firstLine = false;

				Word w = parseLine(line);
				result.add(w);
			}

			return result;
		} catch (Exception e) {
			throw new RuntimeException("Failed to load words from: " + resourcePath, e);
		}
	}

	private Word parseLine(String line) {
		var parts = line.split(";", -1);
		if (parts.length < 4) {
			throw new IllegalArgumentException("Bad line: " + line);
		}

		var w = new Word();
		w.setText(parts[0].trim().toLowerCase()); 
		w.setHint(parts[1].trim());
		w.setCategory(Category.valueOf(parts[2].trim().toUpperCase()));
		w.setDifficulty(Difficulty.valueOf(parts[3].trim().toUpperCase()));

		return w;
	}

}
