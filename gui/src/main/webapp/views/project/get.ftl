<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <#list projects as project>
        <div>
            <a href="/lys/entry/get?id=${project.id?c}">Project ${project.text}</a>
        </div>
    </#list>
  </body>
</html>
