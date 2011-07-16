<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <#list entries as entry>
        <table>
            <tr><td>Id</td><td>${entry.id?c}</td>
            <tr><td>Text</td><td>${entry.text}</td>
            <tr><td>Type</td><td>${entry.type}</td>
        </table>
    </#list>
  </body>
</html>
