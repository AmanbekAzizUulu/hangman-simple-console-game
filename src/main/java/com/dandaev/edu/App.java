package com.dandaev.edu;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try (var scanner = new Scanner(in)) {
			var words = new HashSet<String>();
			var guessedCharacters = new HashSet<Character>();
			var view = new StringBuilder();

			boolean isFound = false;

			words.add("test");
			var word = words.iterator().next();

			while (true) {
				isFound = false;
				view.setLength(0);
				
				out.print("Type in your guess [single letter]: ");
				var userStringInput = scanner.nextLine().toLowerCase();

				if (userStringInput.length() != 1) {
					out.print("Pls, type in single letter: ");
					continue;
				}

				char userSingleCharacterInput = userStringInput.charAt(0);

				for (int i = 0; i < word.length(); i++) {
					if (word.charAt(i) == userSingleCharacterInput) {
						isFound = true;
						guessedCharacters.add(word.charAt(i));
					}
				}
				for (int i = 0; i < word.length(); i++) {
					char c = word.charAt(i);
					if (guessedCharacters.contains(c)) {
						view.append(c);
					} else {
						view.append('_');
					}
				}
				if (isFound) {
					if (view.indexOf("_") == -1) {
						out.println("You Won!");
						out.println(view);
						break;
					} else {
						out.println("Correct letter!");
						out.println(view);
					}
				} else {
					out.println("Incorrect letter!");
				}
			}
		}
	}
}
