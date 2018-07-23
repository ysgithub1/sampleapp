package com;



public class Bootstrap_container {
	private String containerStart;
	private String containerEnd;
	private String header;
	private String menu;
	private String contentsStart;
	private String contentsEnd;
	public Bootstrap_container(String title ,String userName) {
			// TODO 自動生成されたコンストラクター・スタブ
		this.containerStart ="";
		this.containerEnd="";
		this.header="";
		this.menu="";
		this.contentsStart="";
		this.contentsEnd="";
	
		this.containerStart = "<div class=\"container\">" +"\n";
		this.containerStart += "<div class=\"row  top\">" + "\n";
//		this.header += "<div class=\"hedder col-md-12\">"+ "\n";
//		this.header +="<h1>" + h1 + "</h1>" + "\n";
//		this.header +="ようこそ" + userName + "さん" + "\n";
//		this.header += "</div>"+ "\n";
		
		this.menu += "<div class=\"menu col-md-2\">"+ "\n";
		this.menu += "<h2>ロゴ</h2>"+ "\n";
		this.menu += "<form name=\"display1\" action=\"display1\" method=\"get\">" + "\n";
		this.menu += "<a href=\"javascript:document.display1.submit();\">注文入力</a>" + "\n";
		this.menu += "</form>" + "\n";
//		this.menu += "<br>" + "\n";
		this.menu += "<form name=\"display2\" action=\"display2\" method=\"get\">" + "\n";
		this.menu += "<a href=\"javascript:document.display2.submit();\">注文調整</a>" + "\n";
	 	this.menu += "</form>";
//		this.menu += "<br>";
		this.menu += "<form name=\"adjustmentresult\" action=\"adjustmentresult\" method=\"get\">" + "\n";
		this.menu += "<a href=\"javascript:document.adjustmentresult.submit();\">注文調整結果</a>" + "\n";
	 	this.menu += "</form>";
		this.menu += "<form name=\"logout\" action=\"logout\" method=\"get\">" + "\n";
		this.menu += "<a href=\"javascript:document.logout.submit();\">ログアウト</a>" + "\n";
	 	this.menu += "</form>"+ "\n";
		this.menu += "<br>"+ "\n";
		this.menu += "</div>"+ "\n";
				
		this.contentsStart += "<div class=\"contents col-md-10\">" + "\n";	
		this.contentsStart += "<div class=\"hedder col-md-12\">"+ "\n";
		this.contentsStart +="<h1>" + title + "</h1>" + "\n";
		this.contentsStart +="ようこそ" + userName + "さん" + "\n";
		this.contentsStart += "</div>"+ "\n";
		this.contentsStart += "<div class=\"main col-md-12\">"+ "\n";
		this.contentsEnd += "</div>" + "\n";
		this.contentsEnd += "</div>" + "\n";
		this.containerEnd = "</div>" + "\n";
		this.containerEnd += "</div>";

			
		}
	public String getCotainerStart() {
			return this.containerStart;
	}
	public String getCotainerEnd() {
		return this.containerEnd;
	}
	public String getHeader() {
		return this.header;
	}
	public String getMenu() {
		return this.menu;
	}
	public String getContetntsStart() {
		return this.contentsStart;
	}
	public String getContentsEnd() {
		return this.contentsEnd;
	}
}
