<#macro display entry >
    <span class="ui-widget-header">
        <#switch entry.type>
            <#case 'Project'>
                Project
                <#break>
            <#case 'Note'>
                Comment
                <#break>
            <#case 'Issue'>
                Issue
                <#break>
        </#switch>
        ${entry.id?c}
    </span>
    <div class="ui-widget-content">
        <p>${entry.text}</p>
        <form method="POST" action="add" class="answer">
            <input type="hidden" name="parent" value="${entry.id?c}"/>
            <table>
                <tr>
                    <td><textarea name="text" class="resizable" rows="1" cols="80"></textarea></td>
                    <td><input type="submit" value="Answer"/></td>
                </tr>
            </table>
        </form>
        <div class="ui-widget childs" style="margin-left: 20px">
            <#if entry.childs?? && entry.childs?has_content>
                <#list entry.childs as child>
                    <@display child/>
                </#list>
            </#if> 
        </div>
    </div>
</#macro>