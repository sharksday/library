package com.kassmon.library.config;

import java.util.regex.Pattern;

public class Tokenizer extends com.kassmon.library.tokenizers.Tokenizer {
	
	public Tokenizer () {
		//super.addPattern(Pattern.compile("^()") , "");
		super.addPattern(Pattern.compile("^(@[a-zA-z][a-zA-z]+)") , "key");
		super.addPattern(Pattern.compile("^(\"([a-zA-z][a-zA-z0-1]*)\")") , "string");
		super.addPattern(Pattern.compile("^([0-9]+)") , "int");
		
		super.addPattern(Pattern.compile("^(true)") , "boolean");
		super.addPattern(Pattern.compile("^(false)") , "boolean");
		super.addPattern(Pattern.compile("^(t)") , "boolean");
		super.addPattern(Pattern.compile("^(f)") , "boolean");
		
		
	}
	
}
