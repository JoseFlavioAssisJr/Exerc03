<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Registros</title>
    </head>
    <body>
        <h1>Listagem de Registros</h1>
        <table>
            <tr>
                <th>Id:</th>
                <th>Nome:</th>
                <th>Idade:</th>
                <th>Entrada:</th>
                <th>Saida:</th>
            </tr>
            <c:forEach var="visitante" items="${visitantes}">
                <tr>
                    <td><a href="edita.html?id=${visitante.id}">${visitante.id}</a></td>
                    <td>${visitante.nome}</td>
                    <td>${visitante.idade}</td>
                    <td>${visitante.entrada}</td>
                    <td>
                        <c:if test="${visitante.saida==null}">
                            <a href="saida.html?id=${visitante.id}">Registrar saida</a>
                        </c:if>
                        <c:if test="${visitante.saida!=null}">
                            ${visitante.saida}
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br /><br />
        <a href="novo-registro.html">Registrar novo visitante</a>
    </body>
</html>
