<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css"
          rel="stylesheet" type="text/css"/>
    <script language="javascript"
            src="${pageContext.request.contextPath}/js/public.js"></script>
    <script src="/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        function addProduct() {
            window.location.href = "${pageContext.request.contextPath}/adminAddProductUI";
        }

        function delProduct(pid) {
            var isDel = confirm("您确认要删除吗?");
            if (isDel == true) {
                //跳转到servlet；
                location.href = "${pageContext.request.contextPath}/adminDelProduct?pid=" + pid;
            }
        }


    </script>
</HEAD>
<body>
<br>
    <form method="post" action="${pageContext.request.contextPath}/adminSearchProductList">
        &nbsp;商品名称:<input type="text" name="pname" value="${condition.pname}"> &nbsp;&nbsp;
        是否热门:&nbsp;<select name="is_hot">
        <option value="">不限类别</option>
        <option value="0" ${condition.is_hot=='0'?'selected':''}>否</option>
        <option value="1"  ${condition.is_hot=='1'?'selected':''}>是</option>
    </select>&nbsp;&nbsp;
        商品类别:&nbsp;<select name="cid">
        <option value="">不限类别</option>
        <c:forEach items="${categoryList}" var="item">
            <option  ${condition.cid==item.cid?'selected':''} value="${item.cid}">${item.cname}</option>
        </c:forEach>
    </select>&nbsp;&nbsp;
        <input type="submit" value="搜索">
    </form>
    <form id="Form1" name="Form1" action="${pageContext.request.contextPath}/adminDelProductByCheckBox" method="post">


        <table cellSpacing="1" cellPadding="0" width="100%" align="center"
           bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="">
                <input type="submit" id="del" name="del" value="删除"
                       class="button_del">
                </input>

            </td>

            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="添加"
                        class="button_add" onclick="addProduct()">
                    &#28155;&#21152;
                </button>

            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="18%">勾选</td>
                        <td align="center" width="18%">序号</td>
                        <td align="center" width="17%">商品图片</td>
                        <td align="center" width="17%">商品名称</td>
                        <td align="center" width="17%">商品价格</td>
                        <td align="center" width="17%">是否热门</td>
                        <td width="7%" align="center">编辑</td>
                        <td width="7%" align="center">删除</td>
                    </tr>
                    <c:forEach var="item" items="${requestScope.productlist}" varStatus="index">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%"><input type="checkbox" name="check" value="${item.pid}"></td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">${index.count}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%"><img width="40" height="45"
                                                 src="${pageContext.request.contextPath}/${item.pimage}"></td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">${item.pname}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">${item.shop_price}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">${item.is_hot==1?"是":"否"}</td>
                            <td align="center" style="HEIGHT: 22px">
                                <a href="${ pageContext.request.contextPath }/adminUpdateProductUI?pid=${item.pid}">
                                    <img
                                            src="${pageContext.request.contextPath}/images/i_edit.gif"
                                            border="0" style="CURSOR: hand">
                                </a></td>

                            <td align="center" style="HEIGHT: 22px">
                                <a href="javascript:void(0)" onclick="delProduct('${item.pid}')">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                         height="16" border="0" style="CURSOR: hand"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>

        </TBODY>
    </table>
</form>
</body>
</HTML>

