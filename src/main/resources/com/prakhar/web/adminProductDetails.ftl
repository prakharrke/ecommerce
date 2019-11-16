<#include "header.ftl">
<div class="page-section">

    <div class="row d-flex justify-content-center">
        <div class="col-lg-4">
            <div class="card card-fluid">
                <h6 class="card-header">Details</h6>
                <nav class="nav nav-tabs flex-column border-0">
                    <a href="/app/admin" class="nav-link">Products</a>
                    <a href="/app/admin/create-product" class="nav-link">Create Product</a>
                </nav>
            </div>
        </div>
        <div class="col-lg-8">
            <form method="post" enctype="application/x-www-form-urlencoded" action="/app/admin/updateProduct/${view.getProduct().getId()}">
                <div class="card-body border-top">
                    <legend>Product Details</legend>
                    <div class="form-row">

                        <label for="manufacturer" class="col-md-3">Manufacturer</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="manufacturer" type="text" class="form-control" id="manufacturer" value="${view.getProduct().getManufacturer()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="modelNumber" class="col-md-3">Model Number</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="modelNumber" type="text" class="form-control" id="modelNumber" value="${view.getProduct().getModelNumber()}" disabled required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="modelSeries" class="col-md-3">Model Series</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="modelSeries" type="text" class="form-control" id="modelSeries" value="${view.getProduct().getModelSeries()}" required="">
                        </div>
                    </div>

                    <div class="form-row">
                        <!-- form column -->
                        <label for="productType" class="col-md-3">Product Type</label> <!-- /form column -->
                        <!-- form column -->
                        <div class="col-md-9 mb-3">
                            <select name="productType" id="productType" class="custom-select" disabled>
                                <#list view.getProductTypes() as type>
                                <option value="${type}">${type}</option>
                            </#list>
                            </select>
                        </div><!-- /form column -->
                    </div>
                    <div class="form-row">

                        <label for="price" class="col-md-3">Price</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="price" type="number" class="form-control" id="price" value=${view.getProduct().getPrice()?c} lang="en" required="">
                        </div>
                    </div>
                    <div class="form-row">

                        <label for="quantity" class="col-md-3">Quantity</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="quantity" type="number" class="form-control" id="quantity" value="${view.getProduct().getQuantity()}" required="">
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Operating System</legend>

                    <div class="form-row">

                        <label for="osType" class="col-md-3">Type</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="osType" type="text" class="form-control" id="osType" value="${view.getProduct().getOperatingSystem().getType()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="osVersion" class="col-md-3">Version</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="osVersion" type="text" class="form-control" id="osVersion" value="${view.getProduct().getOperatingSystem().getVersion()}" required="">
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Graphics Details</legend>
                    <div class="form-row">

                        <label for="graphicBrand" class="col-md-3">Brand</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="graphicBrand" type="text" class="form-control" id="graphicBrand" value="${view.getProduct().getGraphicDetails().getBrand()}" required="">
                        </div>
                    </div>
                    <div class="form-row">

                        <label for="graphicType" class="col-md-3">Type</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="graphicType" type="text" class="form-control" id="graphicType" value="${view.getProduct().getGraphicDetails().getType()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="graphicModel" class="col-md-3">Model</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="graphicModel" type="text" class="form-control" id="graphicModel" value="${view.getProduct().getGraphicDetails().getModel()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="graphicMemory" class="col-md-3">Memory</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="graphicMemory" type="text" class="form-control" id="graphicMemory" value="${view.getProduct().getGraphicDetails().getMemory()}" required="">
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Internal Memory</legend>

                    <div class="form-row">

                        <label for="internalMemoryType" class="col-md-3">Type</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="internalMemoryType" type="text" class="form-control" id="internalMemoryType" value="${view.getProduct().getInternalMemory().getType()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="internalMemoryRam" class="col-md-3">Memory</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="internalMemoryRam" type="text" class="form-control" id="internalMemoryRam" value="${view.getProduct().getInternalMemory().getRam()}" required="">
                        </div>
                    </div>

                </div>

                <div class="card-body border-top">
                    <legend>Screen Details</legend>
                    <div class="form-row">

                        <label for="screenSize" class="col-md-3">Size</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="screenSize" type="text" class="form-control" id="screenSize" value="${view.getProduct().getScreenSpecifications().getSize()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="screenType" class="col-md-3">Type</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="screenType" type="text" class="form-control" id="screenType" value="${view.getProduct().getScreenSpecifications().getType()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="screenRatio" class="col-md-3">Ratio</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="screenRatio" type="text" class="form-control" id="screenRatio" value="${view.getProduct().getScreenSpecifications().getRatio()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="screenResolution" class="col-md-3">Resolution</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="screenResolution" type="text" class="form-control" id="screenResolution" value="${view.getProduct().getScreenSpecifications().getResolution()}" required="">
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Processor Details</legend>

                    <div class="form-row">

                        <label for="processorBrand" class="col-md-3">Brand</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorBrand" type="text" class="form-control" id="processorBrand" value="${view.getProduct().getProcessorDetails().getBrand()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorName" class="col-md-3">Name</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorName" type="text" class="form-control" id="processorName" value="${view.getProduct().getProcessorDetails().getName()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorGeneration" class="col-md-3">Generation</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorGeneration" type="text" class="form-control" id="processorGeneration" value="${view.getProduct().getProcessorDetails().getGeneration()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorVariant" class="col-md-3">Variant</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorVariant" type="text" class="form-control" id="processorVariant" value="${view.getProduct().getProcessorDetails().getVariant()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorNumberOfCores" class="col-md-3">Number of Cores</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorNumberOfCores" type="number" class="form-control" id="processorNumberOfCores" value=${view.getProduct().getProcessorDetails().getNumberOfCores()} required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorspeed" class="col-md-3">Speed</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorspeed" type="text" class="form-control" id="processorspeed" value="${view.getProduct().getProcessorDetails().getProcessorSpeed()}" required="">
                        </div>
                    </div>

                    <div class="form-row">

                        <label for="processorCache" class="col-md-3">Cache</label> <!-- /form column -->

                        <div class="col-md-9 mb-3">
                            <input name="processorCache" type="text" class="form-control" id="processorCache" value="${view.getProduct().getProcessorDetails().getCache()}" required="">
                        </div>
                    </div>


                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary ml-auto">Update</button>
                </div>
            </form>
            <form method="post" enctype="application/x-www-form-urlencoded" action="/app/admin/deleteProduct/${view.getProduct().getId()}">
                <div class="form-actions">
                    <button type="submit" class="btn btn-danger ml-auto">Delete</button>
                </div>
            </form>
        </div>
    </div>

</div>

<#if view.getMessage()??>
<script>
    alert("${view.getMessage()}")
</script>
</#if>

<#include "footer.ftl">