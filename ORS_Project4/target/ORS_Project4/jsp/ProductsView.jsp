<%@page import="java.util.Map"%>
<%@page import="com.rays.pro4.controller.ProductsCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
    href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Products Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function() {
        $("#udatee").datepicker({
            changeMonth : true,
            changeYear : true,
            yearRange : '1980:2002',
        });
    });

    function limitInputLength(input, maxLength) {
        if (input.value.length > maxLength) {
            input.value = input.value.slice(0, maxLength);
        }
    }

    function limitWords(textarea, maxWords) {
        const words = textarea.value.split(/\s+/); // Split by whitespace
        if (words.length > maxWords) {
            textarea.value = words.slice(0, maxWords).join(' '); // Limit to maxWords
        }
    }
</script>
</head>
<body>
    <jsp:useBean id="bean" class="com.rays.pro4.Bean.ProductsBean"
        scope="request"></jsp:useBean>
    <%@ include file="Header.jsp"%>

    <center>
        <form action="<%=ORSView.PRODUCTS_CTL%>" method="post">

            <div align="center">
                <h1>
                    <%
                        if (bean != null && bean.getId() > 0) {
                    %>
                    <tr>
                        <th><font size="5px"> Update ProductDetails </font></th>
                    </tr>
                    <%
                        } else {
                    %>
                    <tr>
                        <th><font size="5px"> Add ProductDetails </font></th>
                    </tr>
                    <%
                        }
                    %>
                    
                    <%Map map = (Map) request.getAttribute("prolist");
 %>
                </h1>

                <h3>
                    <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
                </h3>

            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>">

            <table>
                <tr>
                    <th align="left"> Name <span style="color: red">*</span> :</th>
                    <td><input type="text" name="Name"
                        placeholder="Enter Name" size="25"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td>
                    <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Name", request)%></font></td>
                </tr>

                <tr>
                    <th style="padding: 1px"></th>
                </tr>
                <tr>
                    <th align="left"> Description <span style="color: red">*</span> :</th>
                    <td>
                        <textarea name="Description" placeholder="Enter Description" rows="5" cols="25"
                            oninput="limitWords(this, 200)"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
                    </td>
                    <td style="position: fixed">
                        <font color="red"><%=ServletUtility.getErrorMessage("Description", request)%></font>
                    </td>
                </tr>

                <tr>
                    <th style="padding: 1px"></th>
                </tr>
                <tr>
                    <th align="left">DateOfpurchase <span style="color: red">*</span> :</th>
                    <td><input type="text" name="DateOfpurchase"
                        placeholder="Enter DateOfpurchase " size="25" id="udatee"
                        readonly="readonly"
                        value="<%=DataUtility.getDateString(bean.getDateOfpurchase())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("DateOfpurchase", request)%></font></td>
                </tr>
                <tr>
                    <th style="padding: 1px"></th>
                </tr>
                <tr>
                    <th align="left">Price<span style="color: red">*</span> :</th>
                    <td><input type="number" name="Price" id="quantityInput"
                        placeholder="Enter Price" style="width: 198px"
                        value="<%=DataUtility.getStringData(bean.getPrice())%>"
                        oninput="limitInputLength(this, 9)"></td>
                    <td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Price", request)%></font></td>
                </tr>
                <tr>
                    <th style="padding: 1px"></th>
                </tr>
<tr>
					<th style="padding: 1px"></th>
				</tr>

				 <tr>
					<th align="left">Category<span style="color: red">*</span> :
					</th>
					<td>
										</label> <%=HTMLUtility.getList1("Category", String.valueOf(bean.getCategory()), map)%>

					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Category", request)%></font></td>
				</tr>
                    <th></th>
                    <%
                        if (bean.getId() > 0) {
                    %>
                    <td colspan="2">&nbsp; &emsp; <input type="submit"
                        name="operation" value="<%=ProductsCtl.OP_UPDATE%>"> &nbsp;
                        &nbsp; <input type="submit" name="operation"
                        value="<%=ProductsCtl.OP_CANCEL%>"></td>

                    <%
                        } else {
                    %>

                    <td colspan="2">&nbsp; &emsp; <input type="submit"
                        name="operation" value="<%=ProductsCtl.OP_SAVE%>"> &nbsp;
                        &nbsp; <input type="submit" name="operation"
                        value="<%=ProductsCtl.OP_RESET%>"></td>

                    <%
                        }
                    %>
                </tr>
            </table>
        </form>
    </center>

    <%@ include file="Footer.jsp"%>
</body>
</html>