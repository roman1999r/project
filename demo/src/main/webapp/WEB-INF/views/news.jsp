<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Spring Boot WebSocket</title>
    <link rel="stylesheet" th:href="@{/css/main.css}" />

    <!-- https://cdnjs.com/libraries/sockjs-client -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <!-- https://cdnjs.com/libraries/stomp.js/ -->
    <script  src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

</head>
<body>
<div id="chat-container">
    <div class="chat-header">
        <div class="user-container">
            <span id="username" th:utext="${username}"></span>
        </div>
        <h3>Spring WebSocket Chat Demo</h3>
    </div>

    <hr/>

    <div id="connecting">Connecting...</div>
    <ul id="messageArea">
    </ul>
    <form:form action="/news/${user.id}" method="post" >
        <div class="input-message">
            <input type="text" id="message" autocomplete="off"
                   name="text"
                   placeholder="Type a message..."/>
            <button type="submit">Send</button>
        </div>
    </form:form>

    <div>
        <c:forEach var="message" items="${message}">
            <p>${message.text}</p>
            <p>${message.date}</p>
            <H5>${message.user}</H5>
            <%--<p>${message1.text}</p>--%>
            <%--<p>${message1.date}</p>--%>
            <%--<H5>${message1.user}</H5>--%>
        </c:forEach>
    </div>

</div>

<script th:src="@{/js/main.js}"></script>

</body>
</html>