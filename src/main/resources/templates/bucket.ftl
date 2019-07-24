<#import "/macro/page.ftl" as p>
<@p.page "bucket","bucket">

<#if  auth.bucket?size!=0>
<div class="row text-center">
    <h2>Корзина ${auth.username}</h2>
</div>
<div class="row">
<#list auth.bucket as key, value>
<div class="col-sm-6 col-md-3">
    <div class="thumbnail">
        <img src="/img/${key.imageName}" alt="${key.name} image" >
        <div class="caption">
            <h3>${key.name}</h3>
            <p>${key.description}</p>
            <h2>price: ${key.price}</h2>
            <p><a class="btn btn-success" href="/shop/showProduct/${key.id}">Посмотреть товар</a>
            <form action="/shop/addProduct/${key.id}" method="post">
                <input type="number" value="${value}" name="count">
                <input class="btn btn-success"  value="add" type="submit">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
            </p>

            <form action="/bucket/deleteProduct/${key.id}" method="post">
                <p><input class="btn btn-danger"  type="submit" value="del"/></p>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
    </div>
</div>

</#list>
</div>
<div class="row text-center">
    <form action="/bucket/by" method="post">
        <p><input class="btn btn-danger"  type="submit" value="Купить"/></p>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<#else>
<div class="row text-center">
    <h2>Корзина пуста</h2>
    <h3><a class="btn btn-success" href="/shop">Магазин</a></h3>
    <h3><a class="btn btn-success" href="/showPurchases/${auth.id}">История покупок</a></h3>
</div>
</#if>


</@p.page>