/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.85
 * Generated at: 2024-09-15 07:20:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import data.CategoriesDao;
import data.CategoriesDto;
import data.ExpensesDto;
import java.util.ArrayList;

public final class listPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("data.CategoriesDao");
    _jspx_imports_classes.add("data.ExpensesDto");
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ja\">\n");
      out.write("<head>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></meta>\n");
      out.write("<title>データ一覧</title>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/style.css\"></link>\n");
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
      out.write("		<article class=\"products\">\n");
      out.write("			<h1>データ一覧</h1>\n");
      out.write("			");

			// 失敗メッセージがあれば表示
			String failureMessage = (String) request.getAttribute("failureMessage");
			if (failureMessage != null && !failureMessage.isEmpty()) {
				out.println("<p class='failure'>" + failureMessage + "</p>");
			}
			
      out.write("\n");
      out.write("			<table class=\"products-table\">\n");
      out.write("				<tr>\n");
      out.write("					<th class=\"hidden-id\">ID</th>\n");
      out.write("					<th>購入品</th>\n");
      out.write("					<th>値段</th>\n");
      out.write("					<th>カテゴリー</th>\n");
      out.write("					<th>日付</th>\n");
      out.write("					<th>メモ</th>\n");
      out.write("				</tr>\n");
      out.write("				");

				ArrayList<ExpensesDto> expensesDataList = (ArrayList<ExpensesDto>) request.getAttribute("expensesDataList");
				if (expensesDataList != null) {
					// カテゴリーDAOのインスタンスを生成
					CategoriesDao categoriesData = new CategoriesDao();
					for (ExpensesDto expensesData : expensesDataList) {
						// カテゴリーID・カテゴリー名用の変数を定義
						int id = expensesData.getCategory_id();
						String categoryName = "";
						// カテゴリーデータの取得
						ArrayList<CategoriesDto> categoriesDataList = categoriesData.select(id);
						if (!categoriesDataList.isEmpty()) {
							// カテゴリー名を取得して代入
							categoryName = categoriesDataList.get(0).getCategory_name();
						}
						out.println("<tr>");
						out.println("<td class='hidden-id'>" + expensesData.getId() + "</td>");
						out.println("<td>" + expensesData.getName() + "</td>");
						out.println("<td>" + expensesData.getPrice() + "</td>");
						out.println("<td>" + categoryName + "</td>");
						out.println("<td>" + expensesData.getDate() + "</td>");
						out.println("<td>" + expensesData.getMemo() + "</td>");
						out.println("</tr>");
					}
				}
				
      out.write("\n");
      out.write("			</table>\n");
      out.write("		</article>\n");
      out.write("	</main>\n");
      out.write("	<footer>\n");
      out.write("        <p class=\"copyright\">&copy; 家計簿アプリ All rights reserved.</p>\n");
      out.write("    </footer>\n");
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
