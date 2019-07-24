<#import "/macro/page.ftl" as p>
<@p.page "shop","shop">
<div class="row text-center">
    <h1>Добро пожаловать в магазин</h1>
</div>
<#if auth.isAdmin("MODERATOR")||auth.isAdmin("ADMIN")||auth.isAdmin("CREATOR")>
<div class="row text-center">
    <a href="/shop/createProduct" class="btn btn-success">Создать товар</a>
</div>
</#if>


<div class="row">
    <#if products?size!=0>
    <div class="row">
        <#list products as product>
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <img src="/img/${product.imageName}" alt="${product.name} image">
                    <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}.</p>
                        <p>Цена: <h2>${product.price}</h2></p>
                        <p><a class="btn btn-success" href="/shop/showProduct/${product.id}">Посмотреть товар</a>
                        <form action="/shop/addProduct/${product.id}" method="post">
                            <input type="number" value="1" name="count">
                            <input class="btn btn-success"  value="add" type="submit">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                        </p>

                        <form action="/shop/deleteProduct/${product.id}" method="post">
                            <p><input class="btn btn-danger"  type="submit" value="del"/></p>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
            </div>
        </#list>
</div>
    <#else>
        <div class="row  text-center">
            <h3>Товары не найдены..</h3>
        </div>
    </#if>
</div>



</@p.page>