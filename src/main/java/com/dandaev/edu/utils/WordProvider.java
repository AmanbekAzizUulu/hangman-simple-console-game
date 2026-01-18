package com.dandaev.edu.utils;

import java.util.Optional;

import com.dandaev.edu.entities.Category;
import com.dandaev.edu.entities.Difficulty;
import com.dandaev.edu.entities.Word;

public interface WordProvider {
	Optional<Word> getRandom(Category category, Difficulty difficulty);
}
