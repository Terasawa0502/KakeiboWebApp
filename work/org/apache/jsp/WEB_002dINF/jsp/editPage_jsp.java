/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.85
 * Generated at: 2024-09-16 22:19:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.time.LocalDate;
import data.ExpensesDto;
import data.CategoriesDto;
import java.util.ArrayList;
import java.util.Objects;

public final class editPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Objects");
    _jspx_imports_classes.add("data.ExpensesDto");
    _jspx_imports_classes.add("java.time.LocalDate");
    _jspx_imports_classes.add("data.CategoriesDto");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPではGET、POST、またはHEADのみが許可されます。 JasperはOPTIONSも許可しています。");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ja\">\n");
      out.write("<head>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("<title>データ編集</title>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/style.css\">\n");
      out.write("\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<header>\n");
      out.write("		<nav>\n");
      out.write("			<a href=\"");
      out.print(request.getContextPath());
      out.write("/\">家計簿アプリ</a>\n");
      out.write("		</nav>\n");
      out.write("	</header>\n");
      out.write("	<main>\n");
      out.write("		");

		// 最後に送信されたデータを取得
		String id = request.getParameter("id");
	    String name = request.getParameter("name");
	    String date = request.getParameter("date");
	    String price = request.getParameter("price");
	    String memo = request.getParameter("memo");
	    String categoryId = request.getParameter("category_id");
	    // NULLは空文字に置き換え
	    id = Objects.toString(id, "");
	    name = Objects.toString(name, "");
	    date = Objects.toString(date, "");
	    price = Objects.toString(price, "");
	    memo = Objects.toString(memo, "");
	    categoryId = Objects.toString(categoryId, "");
	    // 経費データリストを取得
	    ArrayList<ExpensesDto> expensesDataList = (ArrayList<ExpensesDto>) request.getAttribute("expensesDataList");
	    if (expensesDataList != null && !expensesDataList.isEmpty()) {
	    	// 先頭の要素を取得
	    	ExpensesDto expensesData = expensesDataList.get(0);
	    	// 編集対象のデータを各変数に設定
	    	id = Integer.toString(expensesData.getId());
	    	name = expensesData.getName();
	    	date = expensesData.getDate().toString();
	    	price = Integer.toString(expensesData.getPrice());
	    	memo = expensesData.getMemo();
	    	categoryId = Integer.toString(expensesData.getCategory_id());
	    }
		
      out.write("\n");
      out.write("		<article class=\"registration\">\n");
      out.write("			<h1>データ編集</h1>\n");
      out.write("			");

            // 失敗メッセージがあれば表示
            String failureMessage = (String) request.getAttribute("failureMessage");
            if( failureMessage != null && !failureMessage.isEmpty() ) {
                out.println("<p class='failure'>" + failureMessage + "</p>");
            }
            
      out.write("\n");
      out.write("	        <div class=\"back\">\n");
      out.write("	            <a href=\"");
      out.print(request.getContextPath());
      out.write("/list\" class=\"btn\">&lt; 戻る</a>\n");
      out.write("	        </div>\n");
      out.write("	        <form action=\"");
      out.print(request.getContextPath());
      out.write("/update?id=");
      out.print( id );
      out.write("\" method=\"post\" class=\"registration-form\">\n");
      out.write("	        	<div>\n");
      out.write("	        		<label for=\"name\">購入品名</label>\n");
      out.write("	        		<input type=\"text\" id=\"name\" name=\"name\" maxlength=\"50\" value=\"");
      out.print( name );
      out.write("\" required>\n");
      out.write("	        		<label for=\"price\">値段</label>\n");
      out.write("	        		<input type=\"number\" id=\"price\" name=\"price\" min=\"0\" max=\"100000000\" value=\"");
      out.print( price );
      out.write("\" required>\n");
      out.write("	        		<label for=\"date\">日付</label>\n");
      out.write("	        		<input type=\"date\" id=\"date\" name=\"date\" value=\"");
      out.print( date );
      out.write("\" required>\n");
      out.write("	        		<label for=\"memo\">メモ</label>\n");
      out.write("	        		<input type=\"text\" id=\"memo\" name=\"memo\" maxlength=\"50\" value=\"");
      out.print( memo );
      out.write("\">\n");
      out.write("	        		<label for=\"category_id\">カテゴリー</label>\n");
      out.write("	        		<select id=\"category_id\" name=\"category_id\" required>\n");
      out.write("	        			<option disabled selected value>カテゴリーを選択してください</option>\n");
      out.write("	        			");

	        			// カテゴリーデータリストを取得
	        			ArrayList<CategoriesDto> categoriesDataList = (ArrayList<CategoriesDto>) request.getAttribute("categoriesDataList");
	        			if (categoriesDataList != null) {
	        				for (CategoriesDto categoriesData : categoriesDataList) {
	        					// カテゴリーIDをint型をStringに変換
	        					String tempCategoryId = Integer.toString(categoriesData.getCategory_id());
	        					// 直前のカテゴリー名と一致した場合は選択状態にする
	        					String selected = Objects.equals(categoryId, tempCategoryId) ? "selected" : "";
	        					out.println("<option value='" + categoriesData.getCategory_id() + "' " + selected + ">"
	        							+ categoriesData.getCategory_name() + "</option>");
	        				}
	        			}
	        			
      out.write("\n");
      out.write("	        		</select>\n");
      out.write("	        	</div>\n");
      out.write("	        	<button type=\"submit\" class=\"submit-btn\" name=\"submit\" value=\"update\">更新</button>\n");
      out.write("	        </form>\n");
      out.write("		</article>\n");
      out.write("	</main>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
