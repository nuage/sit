<#import "macros.ftl" as macro/>

<#if success>
    <div class="ui-state-highlight entry" style="display: none">
        <@macro.display entry/>
    </div>
<#else>
    <div class="ui-state-error">
        An error happened while creating the entry.
    </div>
</#if>
