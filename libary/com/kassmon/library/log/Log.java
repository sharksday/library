package com.kassmon.library.log;

import java.util.ArrayList;

public class Log {
	
	private static boolean report = false;
	private static EntryType reportLevel = EntryType.INFO;
	
	private static ArrayList<LogEntry> logEntries = new ArrayList<>();
	public static ArrayList<NewLogEntryEvent> eventLisners = new ArrayList<>();
	
	public static void newLogEntry(EntryType entryType, String path, String error) {
		String html = makeHTMLforLog(entryType, path, error);
		LogEntry entry = new LogEntry(entryType,path,error, html);
		logEntries.add(entry);
		newEvent(entry);
		if (report) System.out.print(reportSystem(entryType, path, error));
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
	
	public static void setReport(boolean report) {
		Log.report = report;
	}
	
	public static void setReportLevel(EntryType type) {
		reportLevel = type;
	}
	
	private static String reportSystem(EntryType entryType, String path, String error) {
		
		if (reportLevel == EntryType.INFO) {
			return (entryType.toString() + " " + path + " " + error + System.lineSeparator());
		}else if (reportLevel == EntryType.WARNING) {
			if (entryType != EntryType.INFO) {
				return (entryType.toString() + " " + path + " " + error+ System.lineSeparator());
			}else {
				return "";
			}
		}else if (reportLevel == EntryType.ERROR) {
			if (entryType == EntryType.ERROR) {
				return (entryType.toString() + " " + path + " " + error+ System.lineSeparator());
			}else {
				return "";
			}
		}else {
			return "";
		}
	}
	
}