<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="/js/jquery-1.6.2"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.14.custom.min"></script>
    <link href="/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <@display entry/>
  </body>
</html>

<#macro display entry >
    <div style="border: 1px solid #dadada">
        <span>Entry ${entry.id?c} <#if entry.parent??> - parent = ${entry.parent?c}</#if></span>
        <p>${entry.text}</p>
        <#if entry.childs?? && entry.childs?has_content>
            <div style="margin-left: 10px">
                <#list entry.childs as child>
                    <@display child/>
                </#list>
            </div>
        </#if> 
    </div>
</#macro>