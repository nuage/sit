<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <#if success>
        <h2>New Projet Added !</h2>
        <table>
            <tr><td>Id</td><td>${project.id?c}</td>
            <tr><td>Text</td><td>${project.text}</td>
            <tr><td>Type</td><td>${project.type}</td>
        </table>
    </#if>
  </body>
</html>
