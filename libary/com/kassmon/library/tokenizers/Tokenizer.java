package com.kassmon.library.tokenizers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	
	private String input = "";
	private ArrayList<TokenPattern> patterns;
	
	public Tokenizer() {
		patterns = new ArrayList<>();
	}
	
	public void addPattern(Pattern pattern, String type){
		patterns.add(new TokenPattern(pattern, type));
	}
	
	public boolean hasNextToken(){
		return !input.isEmpty();
	}
	
	public Token getNextToken(){
		if (hasNextToken()){
			input = input.trim();
			for (TokenPattern pattern: patterns){
				Matcher matcher = pattern.getPattern().matcher(input);
				if (matcher.find()){
					String obj = matcher.group().trim();
					input = matcher.replaceFirst("");
					return (new Token(obj, pattern.getType()));
				}
			}
		}
		return new Token("null", "null");
	}
	
	public Token getNextToken(boolean peek){
		if (!peek) {
			if (hasNextToken()){
				input = input.trim();
				for (TokenPattern pattern: patterns){
					Matcher matcher = pattern.getPattern().matcher(input);
					if (matcher.find()){
						String obj = matcher.group().trim();
						input = matcher.replaceFirst("");
						return (new Token(obj, pattern.getType()));
					}
				}
			}
			return new Token("null", "null");
		}else {
			if (hasNextToken()){
				input = input.trim();
				for (TokenPattern pattern: patterns){
					Matcher matcher = pattern.getPattern().matcher(input);
					if (matcher.find()){
						String obj = matcher.group().trim();
						return (new Token(obj, pattern.getType()));
					}
				}
			}
			return new Token("null", "null");
		}
	}
	
	public void setInput(String input) {
		this.input = input;
		}
	
	public String getInput() {
		return input;
		}
	
}