<#include "header.ftl">
<div class="page-section">
    <div class="row">
        <div class="col-lg-4">
            <div class="card card-fluid">
                <h6 class="card-header">Details</h6>
                <nav class="nav nav-tabs flex-column border-0">
                    <a href="/app/admin" class="nav-link active">Products</a>
                    <a href="/app/admin/create-product" class="nav-link">Create Product</a>
                </nav>
            </div>
        </div>
        <div class="col-lg-8">

            <legend>Refurbished Laptops</legend>
            <#list view.getProducts() as product>
            <div class="card card-fluid">
                <h6 class="card-header"><a href="/app/admin/product/${product.getId()}" class="nav-link">${product.toString()}</a></h6>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-4">
                        </div>
                        <div class="col-lg-8">

                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${product.getOperatingSystem().toString()}</li>
                                <li class="list-group-item">${product.getProcessorDetails().toString()}</li>
                                <li class="list-group-item">${product.getGraphicDetails().toString()}</li>
                                <li class="list-group-item">${product.getScreenSpecifications().toString()}</li>
                            </ul>


                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
</div>

<#include "footer.ftl">