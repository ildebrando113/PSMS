<%@ page  language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c"%>

<!doctype html>
<html lang ="en">
    <%@include file="../jsp/head.jsp" %>
    <body>
        <div class ="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <%@include file="../jsp/menu.jsp" %>
            <main class ="mdl-layout__content">
                <div class="page-content">
                    <div class="mdl-grid center-items"> 
                        <div class="mdl-cell mdl-cell--4-col">
                            <div class="mdl-card mdl-shadow--6dp">
                                <div 
                                    class="mdl-card__title mdl-color--primary mdl-color-text--white">
                                    <h2 class="mdl-card__title-text">
                                       <c:if test="${user!=null }">Edit User</c:if>
                                       <c:if test="${user==null }">Add New User</c:if>

                                        </h2>
                                    </div>
                                    <div class ="mdl-card__supporting-text">
                                        <c:if test="${user !=null}">
                                            <form name="myForm" action="/PSMS_ver1/UserController?op=update" method="post" onsubmit ="return validateForm()">
                                            </c:if>
                                            <c:if test="${user ==null}">
                                                <form name="myForm" action="/PSMS_ver1/UserController?op=insert" method="post" onsubmit = "return validateForm()">
                                                </c:if>
                                                <c:if test="${user !=null}">
                                                    <input type="hidden" name="id"
                                                           value="<c:out value ='${user.id}'/>"/>
                                                </c:if>

                                                <div class="mdl-textfield mdl-js-textfield">
                                                    <input class="mdl-textfield__input" type="text" name="name"
                                                           value="<c:out value='${user.name}'/>" id="name"/> 
                                                           <label class ="mdl-textfield__label" for ="name"> Name</label>
                                                </div>

                                                <div class="mdl-textfield mdl-js-textfield">
                                                    <input class=" mdl-textfield__input" type="text"
                                                           name="surname"
                                                           value="<c:out value='${user.surname}'/>" id="surname"/>
                                                    <label class="mdl-textfield__label" for="surname">Surname</label>
                                                </div>
												
												<div class="mdl-textfield mdl-js-textfield">
                                                    <input class=" mdl-textfield__input" type="date"
                                                           name="birthdate"
                                                           value="<c:out value='${user.birthdate}'/>" id="birthdate"/>
                                                    <label class="mdl-textfield__label" for="birthdate">Birthdate</label>
                                                 </div>
                                                 
                                                 <div class="mdl-textfield mdl-js-textfield">
                                                    <input class=" mdl-textfield__input" type="time"
                                                           name="creationtime"
                                                           value="<c:out value='${user.creationtime}'/>" id="Creation Timestamp"/>
                                                    <label class="mdl-textfield__label" for="creationtime">Creation Timestamp</label>
                                                 </div>
                                                 
                                                <div class="mdl-textfield mdl-js-textfield">
                                                    <input class="mdl-textfield__input" type="text" name="age"
                                                           value="<c:out value='${user.age}'/>" id="age"/> 
                                                           <label class ="mdl-textfield__label" for ="age"> Age</label>
                                                </div>
                                                
                                                <div class="mdl-textfield mdl-js-textfield">
                                                    <input class="mdl-textfield__input" type="text" name="type"
                                                           value="<c:out value='${user.type}'/>" id="type"/> 
                                                           <label class ="mdl-textfield__label" for ="type"> Type</label>
                                                </div>
                                                
<%--                                                 <div class="mdl.textfield mdl-js-textfield">
                                                    <c:choose>
                                                        <c:when test= "${user!=null}">
                                                            <input class  ="mdl-textfield__input" type="text" name="quantity" value="<c:out value='${user.quantity}'/>" id="quantity"/>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <input class  ="mdl-textfield__input" type="text" name="quantity" value="<c:out value='1'/>" id="quantity"/>  
                                                        </c:otherwise> 
                                                    </c:choose>  
                                                    <label 
                                                        class="mdl-textfield__label" for="quantity">Quantity</label>
     --%>                                            </div>
<%-- 

                                                <div class="mdl-textfield mdl-js-textfield">
                                                    <input class="mdl-textfield__input" type="text" name="location"
                                                           value="<c:out value ='${user.location}'/>" id="location" /> <label
                                                           class ="mdl-textfield__label" for="location" >Location</label>
                                                </div>
                                                 --%>
                                                <input type="submit"
                                                       class ="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                                                       value="save">
                                            </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <script type="text/javascript">
                function validateForm() {
                    var x = document.forms["myForm"]["quantity"].value;
                    if (x =="") {
                        alter("Quantity must be filled out");
                        return false;
                    }
                }
            </script>
        </body>
    </html>