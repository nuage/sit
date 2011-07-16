<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <#if success>
        <h2>New Entry Added !</h2>
        <table>
            <tr><td>Id</td><td>${entry.id}</td>
            <tr><td>Text</td><td>${entry.text}</td>
            <tr><td>Type</td><td>${entry.type}</td>
        </table>
    </#if>
  </body>
</html>
