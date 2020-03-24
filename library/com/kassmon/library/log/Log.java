package com.kassmon.lib.log;

import java.util.ArrayList;

public class Log {
	
	private static ArrayList<LogEntry> logEntries = new ArrayList<>();
	
	public static ArrayList<NewLogEntryEvent> eventLisners = new ArrayList<>();
	
	public static void newLogEntry(EntryType entryType, String path, String error) {
		String html = makeHTMLforLog(entryType, path, error);
		LogEntry entry = new LogEntry(entryType,path,error, html);
		logEntries.add(entry);
		newEvent(entry);
		//System.out.println(entry.getError());
	}
	
	public static String[] getLogAsString () {
		String error[] = new String[logEntries.size()];
		
		for (int i = 0; i < error.length; i++) {
			String errorline = null;
			EntryType entryType = logEntries.get(i).getErrorType();
			
			if (entryType == EntryType.INFO) errorline = "[INFO]   ";
			if (entryType == EntryType.WARNING) errorline = "[WARNING]   ";
			if (entryType == EntryType.ERROR) errorline = "[Error]   ";
			
			errorline = errorline + logEntries.get(i).getPath() + "   " + logEntries.get(i).getError();
			
			error[i] = errorline;
		}
		
		return error;
	}
	
	public static LogEntry[] getLog(){
		LogEntry obj[] = new LogEntry[logEntries.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = logEntries.get(i);
		}
		
		return obj;
	}
	
	public static void addEventListener(NewLogEntryEvent listen) {
		eventLisners.add(listen);
	}
	
	private static void newEvent(LogEntry logEntry) {
		for (NewLogEntryEvent obj: eventLisners) obj.newError(logEntry);
	}
	
	private static String makeHTMLforLog(EntryType entryType, String path, String error){
		String out = null;
		String bgColor = "white";
		switch (entryType) {
			case INFO:
				bgColor = "green";
			break;
			case WARNING:
				bgColor = "orange";
			break;
			case ERROR:
				bgColor = "red";
			break;
		}
		out = "<p><b style=\"background:" + bgColor +"\">" + entryType.toString() + "</b> " + path + " " + error + "</p>" + System.lineSeparator();
		return out;
	}
	
}