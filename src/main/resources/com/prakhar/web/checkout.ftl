<#include "header.ftl">
<div class="page-section">
    <div class="row">
        <#list view.getPerson().getBillingAddressList() as address >
            <div class="col-lg-4">
                <div class="card-body">

                </div>
            </div>
        </#list>
    </div>
</div>

<#include "footer.ftl">