<#include "header.ftl">

    <div class="page-section">
        <div class="row">
            <div class="col-lg-4">
                <div class="card card-fluid">
                    <h6 class="card-header">Details</h6>
                    <nav class="nav nav-tabs flex-column border-0">
                        <a href="/app/billing" class="nav-link active">Billing</a>
                        <a href="#" class="nav-link">Profile</a>
                    </nav>
                </div>

                <div class="card card-fluid mt-2">
                    <h6 class="card-header">Saved Addresses</h6>
                    <div class="card-body" id="savedAddresses" style="overflow-y:scroll; max-height : 48vh">
                        <#assign i = view.getPerson().getBillingAddressList()?size>
                        <#if i != 0>
                        <div class="list-group list-group-bordered mb-3">
                        <#list view.getPerson().getBillingAddressList() as address>
                            <a href="#" class="list-group-item list-group-item-action ">
                                ${address.getAddressLine1()}<br>
                                ${address.getAddressLine2()}
                            </a>
                        </#list>
                        </div>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card card-fluid">
                    <h6 class="card-header">Billing Details</h6>
                    <div class="card-body">
                        <form method="post" enctype="application/x-www-form-urlencoded" action="/app/billing-details/add-address">
                            <div class="form-row">
                                <!-- form column -->
                                <label for="firstName" class="col-md-3">First Name</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="firstName" type="text" class="form-control" id="firstName" value="" required="">
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="lastName" class="col-md-3">Last Name</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="lastName" type="text" class="form-control" id="lastName" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="country" class="col-md-3">Country</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <select name="country" id="country" class="custom-select">
                                        <option value="" disabled> Select your country </option>
                                        <option value="id" selected> India </option>
                                    </select>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="addressLine1" class="col-md-3">Address Line 1</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="addressLine1" type="text" class="form-control" id="addressLine1" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="addressLine2" class="col-md-3">Address Line 2</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="addressLine2" type="text" class="form-control" id="addressLine2" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="city" class="col-md-3">City</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="city" type="text" class="form-control" id="city" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="state" class="col-md-3">State</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="state" type="text" class="form-control" id="state" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="pinCode" class="col-md-3">Pin Code</label> <!-- /form column -->
                                <!-- form column -->
                                <div class="col-md-9 mb-3">
                                    <input name="pinCode" type="text" class="form-control" id="pinCode" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-row">
                                <!-- form column -->
                                <label for="phone" class="col-md-3">Phone Number</label> <!-- /form column -->
                                <!-- form column -->
                                <div name="phone" class="col-md-9 mb-3">
                                    <input type="text" class="form-control" id="phone" value="" required>
                                </div><!-- /form column -->
                            </div>

                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary ml-auto">Add Address</button>
                            </div>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

<#include "footer.ftl">