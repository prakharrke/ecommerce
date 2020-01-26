<#include "header.ftl">
<div class="page-section">
    <div class="row">
        <div class="col-lg-4">
            <div class="card card-fluid">
                <h6 class="card-header">Navigate</h6>
                <nav class="nav nav-tabs flex-column border-0">
                    <a href="/app/billing" class="nav-link">Billing</a>
                    <a href="/app/home" class="nav-link active">Products</a>
                    <a href="/app/cart" class="nav-link">My Cart</a>
                    <a href="/app/admin" class="nav-link">Admin Console</a>
                </nav>
            </div>
        </div>
        <div class="col-lg-8">
            <#if view.getCart().getCartItems()?size < 1>
            <div class="card card-fluid">
                <div class="card-body d-flex-justify-content-center">
                    <div class="text-center">
                        <h4 class="text-secondary">You cart is empty.</h4>
                    </div>
                </div>
            </div>
            <#else>
                <#list view.getCart().getCartItems() as cartItem>
            <div class="card-body border">
                <a href="/app/product/${cartItem.getProduct().getId()}" style="text-decoration : none" target="_blank">
                    <legend class="text-secondary">${cartItem.getProduct().toString()}</legend>
                </a>
                <div class="row">
                    <div class="col-lg-3 d-flex justify-content-start">
                        <h4 class="dark mt-4">&#8377;${cartItem.getProduct().getPrice()}/-</h4>
                    </div>
                    <div class="col-lg-5"></div>
                    <div class="col-lg-1 d-flex justify-content-center">
                        <#if cartItem.quantity == 1>
                            <button class="btn btn-primary mt-3 disabled" style="radius : 50%">-</button>
                        <#else>
                        <form method="get" enctype="application/x-www-form-urlencoded" action="/app/cart/decreaseCartItem/${cartItem.getId()}">
                            <button class="btn btn-primary mt-3" style="radius : 100%" type="submit">-</button>
                        </form>
                        </#if>
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">

                        <input disabled type="number" class="form-control mt-3"  value="${cartItem.getQuantity()}" required="">
                    </div>
                    <div class="col-lg-1 d-flex justify-content-center">
                        <form method="get" enctype="application/x-www-form-urlencoded" action="/app/cart/increaseCartItem/${cartItem.getId()}">
                            <button class="btn btn-primary mt-3" style="radius : 100%" type="submit">+</button>
                        </form>
                    </div>

                </div>
            <div class="row">
                <div class="col-lg-12 d-flex justify-content-end mt-2">
                    <form method="get" enctype="application/x-www-form-urlencoded" action="/app/cart/removeCartItem/${cartItem.getId()}">
                        <button class="btn btn-danger" type="submit">Remove</button>
                    </form>
                </div>
            </div>

            </div>
                </#list>

        </#if>

    </div>
</div>
</div>

<#include "footer.ftl">