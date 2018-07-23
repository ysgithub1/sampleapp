<!DOCTYPE html>
<%@page import="com.Bootstrap_container"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<html>
<!-- 	<head> -->
<!--  		<meta charset="utf-8"> -->
<!--  		<title>表示1</title> -->
<!-- 	</head> -->
	${script}
	<p>${head}</p>
	
 	<body>
	<p>${containerstart}</p>
	<p>${titleheader}</p>
	<p>${menu}</p>
	<p>${contentsstart}</p>
<!--       表示したいコンテンツを記載	 -->


    <div id="right-box">
    <CENTER>
      <FONT color="blue" size="+3"><B>受注注文入力</B></FONT> 
    </CENTER>
    <HR>
    <div id="right2-box">
      <TR valign="top">
      <TD>
      <TABLE border="0">
        <TBODY> 
          <TR>
            <TD width="75"><font size="-1">営業所</font>
            </TD>
            <TD width="240"> 
              <INPUT name="TXT_EIGYOSHO" MaxLength=2 Size=3 value="">
              <INPUT type="button" name="BTN" value="参照">
              <font size="-1">ＧＧＧ</font>
            </TD>
            <TD width="60"><font size="-1">工場</font></TD>
            <TD width="260"> 
              <INPUT name="TXT_KOJO" MaxLength=7 Size=8 value="" >
              <INPUT type="button" name="BTN" value="参照">
              <font size="-1">ＧＧＧＧＧＧＧ</font>
            </TD>
            <TD width="45"><FONT size=-1>ＭＣ</FONT></TD>
            <TD width="160"><INPUT name="TXT_MC" MaxLength=2 Size=3 value="12">
            </TD>
          </TR>
        </TBODY> 
      </TABLE>
      <TABLE border="0">
          <TBODY> 
          <TR>
            <TD width="75"><FONT size="-1">銘柄</FONT></TD>
            <TD width="240">
              <INPUT name="TXT_MEIGARA" MaxLength=6 Size=7 value="" >
              <INPUT type="button" name="BTN" value="参照" >
            </TD>
            </TD>
            <TD width="60"><FONT size="-1">級</FONT></TD>
            <TD width="160"> 
              <INPUT name="TXT_KYU_B" MaxLength=1 Size=2 value="" >
            </TD>
          </tr>
          </tbody> 
      </TABLE>
      <TABLE border="0">
        <TBODY> 
          <TR>
            <TD width="75"><FONT size=-1>米坪</FONT></TD>
            <TD width="240"> 
              <INPUT name="TXT_BEITSUBO" MaxLength=4 Size=5 value="" >
            </TD>
            <TD width="60"><FONT size=-1>巾</FONT></TD>
            <TD width="150"> 
              <INPUT name="TXT_HABA" MaxLength=4 Size=5 value="" >
            </TD>
            <TD width="60"><FONT size=-1>流れ</FONT></TD>
            <TD width="60"> 
              <INPUT name="TXT_NAGARE" MaxLength=4 Size=5 value="" >
            </TD>
            <TD width="90"><FONT size=-1>連量</FONT></TD>
            <TD width="60"> 
              <OUTPUT name="TXT_RENRYO" MaxLength=4 Size=5 value="" >
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size=-1>荷姿</FONT></TD>
            <td width="240"><select name="TXT_SUGATA" MaxLength=1>
            <option selected>R</option>
            <option>S</option>
            </select>
            </TD>

            <TD width="60"><font size=-1>入数</font></TD>
            <TD width="100"> 
              <INPUT name="TXT_IRISU" MaxLength=5 Size=8 value="" >
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size=-1>連本数</FONT></TD>
            <td width="240"><INPUT name="TXT_HONDAI" MaxLength=4 Size=5 value="" >
            </TD>
            <TD width="60"><font size=-1>数量</font></TD>
            <TD width="100"> 
              <output name="TXT_SRY" MaxLength=5 Size=8 value="" >
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size=-1>包装形態</FONT></TD>
            <TD width="80"><INPUT name="TXT_HOSO_KEITAI" MaxLength=1 Size=2 value="" >
            </TD>
            <TD width="45"><FONT size=-1>積方</FONT></TD>
            <TD width="85"><INPUT name="TXT_TSUMIKATA" MaxLength=1 Size=2 value="" >
            </TD>
            <TD width="80"><FONT size=-1>パレット紙管</FONT></TD>
            <TD width="90"><INPUT name="TXT_P_SHIKAN_S" MaxLength=2 Size=3 value="" >
            </TD>
            <TD width="60"><FONT size=-1>アルミ敷</FONT></TD>
            <TD width="60"><INPUT name="TXT_ALMI_F" MaxLength=1 Size=2 value="" >
            </TD>
            <TD width="60"><FONT size=-1>束連数</FONT></TD>
            <TD width="60"><INPUT name="TXT_SOKUREN_S" MaxLength=1 Size=2 value="" >
            </TD>
            <TD width="60"><FONT size=-1>仕上方</FONT></TD>
            <TD width="60"><INPUT name="TXT_SHIAGEKATA" MaxLength=1 Size=2 value="" >
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size="-1">代理店</FONT></TD>
            <TD width="260"> 
              <INPUT name="TXT_MEIGARA" MaxLength=7 Size=8 value="" >
              <INPUT type="button" name="BTN" value="参照" >
              <font size="-1">ＧＧＧＧＧＧＧＧＧＧＧＧＧ</font>
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size="-1">優先</FONT></TD>
            <TD width="240">
              <INPUT name="TXT_YUSEN_F" MaxLength=1 Size=2 value="" >
              <font size="-1">１：工場在庫　２：消費地在庫</font>
            </TD>
            <TD width="60"><FONT size="-1">納期</FONT></TD>
            <TD width="60">
              <INPUT name="TXT_NOUKI" MaxLength=6 Size=7 value="" >
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border="0">
          <TBODY>
          <TR>
            <TD width="75"><FONT size="-1">受注注文№</FONT></TD>
            <TD width="150">
              <!--<OUTPUT name="TXT_ZYUCHU_CHUMON_NO" MaxLength=10 Size=11 value="" > -->
              <font size="-1">XXXXXXXXXX</font>
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE border=0>
        <TBODY> 
        <TR> 
          <TD> 
            <input type="reset" name="BTN" value="リセット" >
          </TD>
        </TR>
        </TBODY> 
      </TABLE>
      <BR>
</FORM>
<FORM NAME="ErrForm">
    <TEXTAREA  NAME="ERRAREA" COLS=130 ROWS=10 wrap="hard" STYLE="color: blue">必須項目に入力を行ってください。</TEXTAREA>
</FORM>
</div>
	<p>${contentsend}</p>
	<p>${containerend}</p>

<%--  	ユーザー：<c:out value="${sessionScope.session2}" /> --%>
  	</body>
</html>