package com;

public class Script {
	private String script="";
	public  Script() {
		 this.script = "<script src=\"/sampleapp/resources/js/jquery-3.3.1.min.js\"></script>" + "\n";
		 this.script += "<link href=\"/sampleapp/resources/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">" + "\n";
		 this.script += "<script src=\"/sampleapp/resources/bootstrap/js/bootstrap.min.js\"></script>" +"\n";

	}
	public String getScript() {
		return script;
	}
}
