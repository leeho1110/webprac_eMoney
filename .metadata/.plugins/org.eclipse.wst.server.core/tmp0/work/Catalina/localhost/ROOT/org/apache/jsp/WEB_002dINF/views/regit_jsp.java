/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.60
 * Generated at: 2020-12-10 01:34:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class regit_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_classes = null;
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

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
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

      out.write("\r\n");
      out.write("\t<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\t<html lang=\"en\">\r\n");
      out.write("\t\r\n");
      out.write("\t<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t<title>eMoney Web 실습</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"resources/css/webPrac.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"resources/css/regit.css\">\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- RSA 암호화 JavaScript -->\r\n");
      out.write("\t<script src=\"resources/js/jquery-1.12.4.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"resources/js/rsa/jsbn.js\"></script>\r\n");
      out.write("\t<script src=\"resources/js/rsa/prng4.js\"></script>\r\n");
      out.write("\t<script src=\"resources/js/rsa/rng.js\"></script>\r\n");
      out.write("\t<script src=\"resources/js/rsa/rsa.js\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<!-- wrapper -->\r\n");
      out.write("\t\t<div id=\"wrap\">\r\n");
      out.write("\t\t\t<!-- header -->\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "inc/header.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t<!-- 여기부터 본문 복사 -->\r\n");
      out.write("\t\t\t<div id=\"container\">\r\n");
      out.write("\t\t\t\t<div id=\"cont_inner\">\r\n");
      out.write("\t\t\t\t\t<h2>회원가입</h2>\r\n");
      out.write("\t\t\t\t\t<div id=\"agreeAllMent\">\r\n");
      out.write("\t\t\t\t\t\t<strong>“아래의 '서비스 약관, 개인정보 보호정책 및 수집 이용 안내'에 모두 동의합니다.”</strong>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"agreeAlldiv\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"agreeAll\" class=\"agreeEachCbox\" onclick=\"agreementAllClick();\">\r\n");
      out.write("\t\t\t\t\t\t\t<label id=\"agreeAllLabel\" for=\"agreeAll\">모든 약관에 동의합니다.</label>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"termsAndConditions\">\r\n");
      out.write("\t\t\t\t\t\t<div class=TACdiv>\r\n");
      out.write("\t\t\t\t\t\t<h2>Terms & Conditions 1.</h2>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"TACcontent\">\r\n");
      out.write("\t\t\t\t\t\t\t<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique totam, aspernatur ipsam, soluta vitae inventore odit, sint nemo praesentium minus eaque veniam temporibus consequatur minima veritatis. Nostrum eligendi architecto enim?\r\n");
      out.write("\t\t\t\t\t\t\t\tAb ut perferendis officia, cum ullam quisquam at illum eum, aspernatur perspiciatis doloremque vero dolores voluptate voluptatibus cumque quia, pariatur fugiat recusandae numquam est facilis exercitationem nam. Quos, eaque ratione?\r\n");
      out.write("\t\t\t\t\t\t\t\tAssumenda magnam sed laborum veniam doloribus, harum modi quasi sapiente tempora earum debitis dolores dicta eos consectetur perspiciatis repellat omnis porro! Fugiat, voluptate dignissimos quia vero alias est omnis maxime.\r\n");
      out.write("\t\t\t\t\t\t\t\tQuos quam aut provident? Explicabo labore, ad earum veritatis nisi doloremque corporis pariatur ab vero, quod cumque deleniti iure illum tempore, dolore possimus molestiae dolorem modi. Deleniti non neque magnam.\r\n");
      out.write("\t\t\t\t\t\t\t\tNon commodi dicta cum incidunt iste repellat molestias officia nisi placeat amet repellendus sit iusto, magnam animi! Modi beatae temporibus ipsa nisi quia nostrum, aspernatur rerum, recusandae, debitis facilis autem?\r\n");
      out.write("\t\t\t\t\t\t\t\tMinima ullam modi voluptatibus reprehenderit dolor, est, dolore et ratione odit aliquid consequuntur? Assumenda, molestiae nobis! Dicta modi nam iure et numquam, provident earum sunt eveniet voluptates repudiandae ullam delectus?\r\n");
      out.write("\t\t\t\t\t\t\t\tIste fuga, ducimus maiores deleniti iure quos quia ab asperiores. Voluptate tempora obcaecati inventore nesciunt, veniam molestiae maiores vitae ad quisquam fugit, dolor enim deleniti, incidunt laborum numquam temporibus exercitationem.\r\n");
      out.write("\t\t\t\t\t\t\t\tUnde reiciendis non tempora, quam aliquid aperiam rem alias officia veniam? Mollitia ipsum, reiciendis, quas qui non eos sapiente expedita nesciunt harum, ipsam esse praesentium enim ut vero recusandae error!\r\n");
      out.write("\t\t\t\t\t\t\t\tEa autem tenetur totam cumque fugit voluptatibus, explicabo sunt deleniti! Laudantium dolor officiis incidunt voluptatem, eum obcaecati fuga ex. Nihil architecto, exercitationem veritatis dolor voluptatem delectus necessitatibus eveniet fuga consequuntur!\r\n");
      out.write("\t\t\t\t\t\t\t\tCupiditate odio fuga, quaerat illum dolor sit unde fugiat numquam aliquam ipsum quo optio officia obcaecati? Earum, eligendi! Corrupti laudantium minus, odit sed temporibus sit id explicabo sapiente aspernatur quaerat.\r\n");
      out.write("\t\t\t\t\t\t\t\tExplicabo voluptas incidunt ex dolor consequuntur, eum fugit molestias rerum nulla laborum! Tempore necessitatibus doloremque tempora aut provident porro velit inventore, possimus earum officia sequi ex? Maiores tenetur deleniti blanditiis.\r\n");
      out.write("\t\t\t\t\t\t\t\tCum, labore doloremque vitae voluptate eaque, explicabo provident necessitatibus est dolore veritatis molestias. Ab est illum officia fugit eaque omnis, velit natus commodi aut enim nobis, perspiciatis laudantium sed aspernatur.\r\n");
      out.write("\t\t\t\t\t\t\t\tSoluta placeat, sit fugiat sequi cum accusamus in eos nulla amet facere, voluptas aliquam, vero molestias beatae pariatur neque tenetur! Nobis ab, odit praesentium quidem culpa neque repellat similique molestiae.\r\n");
      out.write("\t\t\t\t\t\t\t\tEt a mollitia possimus facilis recusandae quasi aut assumenda qui, autem nisi impedit unde sunt dolore animi esse fuga iusto nobis quod, nostrum ducimus ullam? Ullam eligendi nisi obcaecati quod?\r\n");
      out.write("\t\t\t\t\t\t\t\tEst beatae, impedit unde accusantium eaque aliquam similique voluptas officia iure doloremque odit vitae quasi corrupti dolor debitis culpa laborum molestias, illo animi ducimus ea repellendus nam distinctio tenetur? Impedit.\r\n");
      out.write("\t\t\t\t\t\t\t\tMolestias, debitis? Mollitia fugit nobis tempora modi laboriosam facilis veniam delectus! Quod quis hic qui expedita esse, minima ullam, assumenda laboriosam distinctio facilis amet eius corrupti aut, modi atque odit?\r\n");
      out.write("\t\t\t\t\t\t\t\tTempore alias maiores accusamus asperiores distinctio rerum ratione explicabo. Fuga, repellat eius officiis veritatis recusandae odit ad eum nam veniam ea, dolorum dolores culpa, ullam ipsum facere. Aspernatur, cum officiis!\r\n");
      out.write("\t\t\t\t\t\t\t\tDeserunt, maxime quas? Ex, ullam itaque minus pariatur eligendi officiis. Provident laborum dolor sed sunt quasi porro assumenda animi explicabo tenetur minima saepe fugiat error, nihil qui cupiditate unde delectus.\r\n");
      out.write("\t\t\t\t\t\t\t\tDolorum ipsum odio quo, ducimus nostrum excepturi. Suscipit at, voluptatem nisi tempore voluptas magni, nemo saepe debitis, eligendi soluta omnis accusamus rerum officia iure fugit aperiam. Quidem magni odit laudantium!\r\n");
      out.write("\t\t\t\t\t\t\t\tQuo asperiores doloremque fuga deserunt iste repellat at culpa, optio excepturi id? Recusandae rerum nesciunt, accusantium porro fuga minima repellat ipsam autem libero similique totam odio vero omnis delectus dolores.\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"agreeEachdiv\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"agreeEach1\" class=\"agreeEachCbox\" onchange=\"agreementEachClick(this);\">\r\n");
      out.write("\t\t\t\t\t\t\t<label id=\"each1Label\" for=\"agreeEach1\">약관에 동의합니다.</label>\r\n");
      out.write("\t\t\t\t   \r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=TACdiv>\r\n");
      out.write("\t\t\t\t\t\t<h2>Terms & Conditions 2.</h2>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"TACcontent\">\r\n");
      out.write("\t\t\t\t\t\t\t<p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Amet, adipisci, quaerat molestias, laborum dolores sed eum tenetur non ex mollitia facilis assumenda. Tempore ipsam ipsa officiis explicabo laborum neque quia.\r\n");
      out.write("\t\t\t\t\t\t\t\tPorro exercitationem, in eligendi dolores repellendus magnam sed, enim alias dicta libero earum voluptates consequatur amet neque inventore quo. Maiores quos provident possimus sapiente similique aperiam expedita? Voluptas, nobis. Eos!\r\n");
      out.write("\t\t\t\t\t\t\tVoluptas deserunt, quibusdam iste a odit est in et molestiae quisquam? Nihil et quia minima id inventore soluta totam, incidunt, beatae, molestias aliquid saepe illo sequi. Vel necessitatibus adipisci assumenda.\r\n");
      out.write("\t\t\t\t\t\t\tIllum vel inventore vero sapiente officiis doloribus odit architecto, commodi, beatae voluptatibus blanditiis culpa ab earum neque. Delectus esse, repellat, cupiditate reiciendis aut ad a pariatur, illo cumque porro fugit?\r\n");
      out.write("\t\t\t\t\t\t\tQuae quisquam animi repellendus cumque delectus sint doloremque assumenda. Ducimus debitis mollitia perspiciatis nobis fugit unde asperiores sapiente odio culpa impedit? Natus, minus? Quas voluptatibus reiciendis itaque aut iste tenetur!\r\n");
      out.write("\t\t\t\t\t\t\tEos ut voluptas commodi neque quas, doloremque voluptatem omnis quaerat necessitatibus nam dolorem, nulla possimus. Inventore ut pariatur fugiat exercitationem, rem doloribus consequuntur non molestiae! Doloribus dicta quo quibusdam voluptatum.\r\n");
      out.write("\t\t\t\t\t\t\tCumque rerum voluptates quam inventore quae cupiditate ex veniam quaerat tenetur consequatur earum delectus quasi, rem atque eveniet debitis voluptas aspernatur dolore nobis reprehenderit molestias. Sapiente dolor deserunt asperiores ea!\r\n");
      out.write("\t\t\t\t\t\t\tLaudantium dolore iure, architecto fugiat, dolorum quia earum nostrum molestiae ea amet perspiciatis fuga ducimus magnam ratione alias dicta, eligendi voluptatum dolor id? Nulla dicta inventore voluptatum, ullam aspernatur unde?\r\n");
      out.write("\t\t\t\t\t\t\tQuis fuga quidem et ab ad sapiente porro omnis quaerat, sequi officiis voluptate cumque vitae obcaecati assumenda laborum ut magnam iste totam amet? Ipsa nulla, minus sint tenetur voluptas quibusdam!\r\n");
      out.write("\t\t\t\t\t\t\tNulla beatae quidem impedit odit itaque, dolorum, consequuntur tempora temporibus necessitatibus eum architecto neque voluptas aut vero odio doloremque recusandae. Esse voluptatum dolore dolores aperiam eum est placeat vitae nostrum.\r\n");
      out.write("\t\t\t\t\t\t\tMolestias, fugit vel repellat eveniet facere aperiam corrupti excepturi, nostrum blanditiis quo accusantium ea similique hic quaerat pariatur qui incidunt vitae, sequi inventore. Et, repudiandae! Laboriosam libero et a nulla.\r\n");
      out.write("\t\t\t\t\t\t\tIste non veniam cum, consectetur voluptas unde voluptatum veritatis omnis suscipit repellat corrupti totam facere? Illo deserunt quod nulla dicta inventore eius placeat praesentium cupiditate minus neque, dolor omnis quo!\r\n");
      out.write("\t\t\t\t\t\t\tEx, cum? Voluptate alias, nemo magni architecto harum ab placeat in at eum ipsa commodi hic, omnis voluptatem corporis deserunt. Voluptates, corporis magnam in sint fugiat soluta asperiores accusamus iure?\r\n");
      out.write("\t\t\t\t\t\t\tNihil harum magni quis corporis esse, mollitia aperiam vero in, sunt iusto vitae ex porro incidunt amet rem earum, vel asperiores? Vel temporibus, impedit sunt assumenda commodi voluptate reiciendis dolor!\r\n");
      out.write("\t\t\t\t\t\t\tEos, doloribus nisi sit, adipisci obcaecati temporibus vitae id, quaerat repudiandae numquam expedita? Saepe expedita explicabo eaque reprehenderit cupiditate quae blanditiis assumenda maiores laudantium, eos ratione voluptate autem enim eum.\r\n");
      out.write("\t\t\t\t\t\t\tQuaerat possimus, iure quas deserunt odio asperiores nesciunt accusantium natus placeat quo excepturi hic corrupti eveniet aperiam debitis adipisci, ullam consectetur a harum laudantium necessitatibus. Veritatis optio exercitationem accusantium eius!\r\n");
      out.write("\t\t\t\t\t\t\tRecusandae dolore labore quaerat eveniet quidem similique placeat illo pariatur veniam reprehenderit qui ipsam, eum itaque animi delectus dolorum ex libero quisquam. Laudantium asperiores porro doloribus autem ratione esse ut?\r\n");
      out.write("\t\t\t\t\t\t\tQuas cupiditate obcaecati sequi iusto nesciunt recusandae totam commodi repellat quisquam, tempore doloremque facere pariatur voluptates ut consequatur suscipit porro. Animi veniam saepe necessitatibus molestiae explicabo. Illo molestiae esse ex.\r\n");
      out.write("\t\t\t\t\t\t\tIste consectetur doloribus sint repellat non veritatis dolorum quod hic voluptatibus cupiditate, praesentium, dolores suscipit numquam saepe reiciendis, optio maxime sequi laborum error nam ut porro! Magnam quo nobis pariatur.\r\n");
      out.write("\t\t\t\t\t\t\tExplicabo sint illo, magni dolorum aut corrupti corporis sunt dignissimos, nam nostrum architecto alias ex neque, et veritatis? Dicta, error non? Aspernatur perspiciatis, modi culpa consequuntur beatae suscipit eum dolor?\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"agreeEachdiv\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" id=\"agreeEach2\" class=\"agreeEachCbox\" onchange=\"agreementEachClick(this);\">\r\n");
      out.write("\t\t\t\t\t\t\t<label id=\"each2Label\" for=\"agreeEach2\">약관에 동의합니다.</label>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"infobox\">\r\n");
      out.write("\t\t\t\t\t\t<form id=\"accntRegit\" action=\"/register.submit.do\" method=\"POST\">\r\n");
      out.write("\t\t\t\t\t\t\t<table id=\"infoTbl\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>아이디 <em>*</em></th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"duplicateboxdiv\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" id=\"idinput\" onkeyup=\"idFormCheck();\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"idhiddeninput\" name=\"id\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"//img.x1.co.kr/x1/images/btn/btn_duplication.gif\" id=\"idduplicheck\" onclick=\"duplicheckAjax(this);\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"duplimentFormMent\" id=\"idduplino\">4자이상 12자이하 영문,숫자(띄어쓰기, 특수문자 불가)<span style=\"color: red;\"> 중복확인을 해주세요</span></span> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"duplimentFormMent\" id=\"iddupliok\">사용가능합니다.</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>비밀번호 <em>*</em></th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><input type=\"password\" id=\"pwinput\" onkeyup=\"pwFormCheck();\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"pwhiddeninput\" name=\"s_passwd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<span class=\"pwdment\" id=\"pwduplino\">영어 대/소문자, 숫자, 특수문자 중 2가지 이상 조합 6자~12자(띄어쓰기 불가)</span> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<span class=\"pwdment\" id=\"pwdupliok\">사용가능합니다.</span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<th>비밀번호 확인 <em>*</em></th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input type=\"password\" id=\"pwreinput\" onkeyup=\"pwCorrectcheck();\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<th>성명 <em>*</em></th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input type=\"text\" id=\"nameinput\" name=\"name\" onkeyup=\"nameFormCheck();\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>닉네임(필명) <em>*</em></th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"duplicateboxdiv\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" id=\"nicknameinput\" name=\"nickname\" onkeyup=\"nicknameFormCheck();\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"//img.x1.co.kr/x1/images/btn/btn_duplication.gif\" id=\"nicknameduplicheck\" onclick=\"duplicheckAjax(this);\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"duplimentFormMent\" id=\"nicknameduplino\">영문 4자~12자,한글 2자~6자(띄어쓰기, 특수문자 불가)<span style=\"color: red;\"> 중복확인을 해주세요</span></span> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"duplimentFormMent\" id=\"nicknamedupliok\">사용가능합니다.</span></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("                                    \r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                    <th>휴대폰 번호 <em>*</em></th>\r\n");
      out.write("                                    <td>\r\n");
      out.write("                                        <select id=\"phoneinput1\" class=\"phoneinput\">\r\n");
      out.write("                                            <option>010</option>\r\n");
      out.write("                                            <option>011</option>\r\n");
      out.write("                                            <option>016</option>\r\n");
      out.write("                                            <option>017</option>\r\n");
      out.write("                                            <option>018</option>\r\n");
      out.write("                                            <option>018</option>\r\n");
      out.write("                                            <option>070</option>\r\n");
      out.write("                                        </select> -\r\n");
      out.write("                                            <input type=\"text\" id=\"phoneinput2\" class=\"phoneinput\" maxlength=\"4\"> -\r\n");
      out.write("                                            <input type=\"text\" id=\"phoneinput3\" class=\"phoneinput\" maxlength=\"4\">\r\n");
      out.write("                                            <input type=\"hidden\" id=\"phoneHiddenInput\" name=\"phone\">\r\n");
      out.write("                                    </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t<!-- submit btn -->\r\n");
      out.write("\t\t\t\t\t\t<div id=\"submitdiv\">\r\n");
      out.write("\t\t\t\t\t\t\t<img id=\"cancelbtn\"\r\n");
      out.write("\t\t\t\t\t\t\t\tsrc=\"//img.x1.co.kr/x1/images/btn/btn_cancel.gif\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t<img\r\n");
      out.write("\t\t\t\t\t\t\t\tid=\"submitbtn\" src=\"//img.x1.co.kr/x1/images/btn/btn_confirm.gif\" onclick=\"regitCtgSubmit();\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- JS load -->\r\n");
      out.write("\t\t<script src=\"resources/js/regit.js\"></script>\r\n");
      out.write("\t</body>\r\n");
      out.write("\t\r\n");
      out.write("\t</html>");
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
