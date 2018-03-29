<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userMan.label', default: 'UserMan')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user_" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li>
                    <g:form controller="logout">
                        <g:submitButton name="logout" value="Logout" style="border: none; background-color: inherit"/>
                    </g:form>
                </li>
            </ul>
        </div>
        <div id="list-user_" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${user_List}" />

            <div class="pagination">
                <g:paginate total="${user_Count ?: 0}" />
            </div>
        </div>

        <table>
            <tr>
                <th>id</th>
                <th>username</th>
                <th>firstname</th>
                <sec:access expression="hasRole('ROLE_ADMIN')">
                    <th>lastname</th>
                    <th>password</th>
                    <th>enabled</th>
                    <th>accountExpired</th>
                    <th>accountLocked</th>
                    <th>passwordExpired</th>
                </sec:access>
            </tr>
            <g:each in="${usersList}">
                <tr>
                    <td>${it.id}</td>
                    <td>${it.username}</td>
                    <td>${it.firstname}</td>
                    <sec:access expression="hasRole('ROLE_ADMIN')">
                        <td>${it.lastname}</td>
                        <td>${it.password}</td>
                        <td>${it.enabled}</td>
                        <td>${it.accountExpired}</td>
                        <td>${it.accountLocked}</td>
                        <td>${it.passwordExpired}</td>
                    </sec:access>
                </tr>
            </g:each>
        </table>

    </body>
</html>