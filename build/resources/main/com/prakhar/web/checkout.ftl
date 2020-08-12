<#include "header.ftl">
<div class="page-section">
    <div class="row">
        <#list view.getPerson().getBillingAddressList() as address >

        </#list>
    </div>
</div>

<#include "footer.ftl">