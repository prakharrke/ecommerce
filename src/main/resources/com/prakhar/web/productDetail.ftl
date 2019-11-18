<#include "header.ftl">
    <div class="row d-flex justify-content-center">
        <div class="col-lg-4">
            <div class="card card-fluid">
                <h6 class="card-header">Navigate</h6>
                <nav class="nav nav-tabs flex-column border-0">
                    <a href="/app/billing" class="nav-link">Billing</a>
                    <a href="/app/home" class="nav-link active">Products</a>
                    <a href="/app/admin" class="nav-link">Admin Console</a>
                </nav>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="card card-fluid">
                <h6 class="card-header">
                    <div class="text-center">${view.getProduct().toString()}</div>
                </h6>
                <div class="card-body border-top">
                    <legend>Manufacturer Details</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">Manufacturer</span><br>
                            <span class="mt-1">Model Series</span><br>
                            <span class="mt-1">Model Number</span><br>
                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getManufacturer()}</span><br>
                            <span class="mt-1">${view.getProduct().getModelSeries()}</span><br>
                            <span class="mt-1">${view.getProduct().getModelNumber()}</span><br>
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Operating System</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">OS Type</span><br>
                            <span class="mt-1">OS Name and Version</span><br>

                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getOperatingSystem().getType()}</span><br>
                            <span class="mt-1">${view.getProduct().getOperatingSystem().getVersion()}</span><br>

                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Screen Specifications</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">Size</span><br>
                            <span class="mt-1">Type</span><br>
                            <span class="mt-1">Resolution</span><br>
                            <span class="mt-1">Ratio</span><br>

                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getScreenSpecifications().getSize()}</span><br>
                            <span class="mt-1">${view.getProduct().getScreenSpecifications().getType()}</span><br>
                            <span class="mt-1">${view.getProduct().getScreenSpecifications().getResolution()}</span><br>
                            <span class="mt-1">${view.getProduct().getScreenSpecifications().getRatio()}</span><br>

                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Processor Details</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">Brand</span><br>
                            <span class="mt-1">Name</span><br>
                            <span class="mt-1">Generation</span><br>
                            <span class="mt-1">Variant</span><br>
                            <span class="mt-1">Number of cores</span><br>
                            <span class="mt-1">Processor Speed</span><br>
                            <span class="mt-1">Cache</span><br>

                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getBrand()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getName()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getGeneration()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getVariant()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getNumberOfCores()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getProcessorSpeed()}</span><br>
                            <span class="mt-1">${view.getProduct().getProcessorDetails().getCache()}</span><br>
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Internal Memory</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">RAM</span><br>
                            <span class="mt-1">Type of RAM</span><br>
                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getInternalMemory().getRam()}</span><br>
                            <span class="mt-1">${view.getProduct().getInternalMemory().getType()}</span><br>
                        </div>
                    </div>
                </div>

                <div class="card-body border-top">
                    <legend>Graphic Processor</legend>
                    <div class="row mt-2">
                        <div class="col-lg-4">
                            <span class="mt-1">Brand</span><br>
                            <span class="mt-1">Model</span><br>
                            <span class="mt-1">Type</span><br>
                            <span class="mt-1">Memory</span><br>
                        </div>
                        <div class="col-lg-8">
                            <span class="mt-1">${view.getProduct().getGraphicDetails().getBrand()}</span><br>
                            <span class="mt-1">${view.getProduct().getGraphicDetails().getModel()}</span><br>
                            <span class="mt-1">${view.getProduct().getGraphicDetails().getType()}</span><br>
                            <span class="mt-1">${view.getProduct().getGraphicDetails().getMemory()}</span><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#include "footer.ftl">