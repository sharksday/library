package com.kassmon.lib.log;

public class LogEntry {
	
	private EntryType entryType;
	private String path;
	private String error;
	private String HTMLtext;
	
	public LogEntry(EntryType entryType, String path, String error, String HTMLtext) {
		this.entryType = entryType;
		this.path = path;
		this.error = error;
		this.HTMLtext = HTMLtext;
	}

	public EntryType getErrorType() {
		return entryType;
	}

	public String getPath() {
		return path;
	}

	public String getError() {
		return error;
	}
	
	public String getHTMLtext() {
		return HTMLtext;
	}
	
	
	
}
