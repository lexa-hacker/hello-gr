<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user_.label', default: 'User_')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user_" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
                <th>id</th><th>firstname</th><th>lastname</th>
            </tr>
            <g:each in="${users_list}">
                <tr>
                    <td>${it.id}</td><td>${it.firstname}</td><td>${it.lastname}</td>
                </tr>
            </g:each>
        </table>

    </body>
</html>