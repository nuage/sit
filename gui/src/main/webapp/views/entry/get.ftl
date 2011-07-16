<#import "macros.ftl" as macro/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.14.custom.min.js"></script>
    <script type="text/javascript" src="/js/gui.js"></script>
    <link href="/css/ui-lightness/jquery-ui-1.8.14.custom.css" rel="stylesheet" type="text/css"/>
    <style>
	.ui-resizable-se {
		bottom: 17px;
	}
    </style>
  </head>
  <body>
    <div class="ui-widget entry">
        <@macro.display entry/>
    </div>
  </body>
</html>