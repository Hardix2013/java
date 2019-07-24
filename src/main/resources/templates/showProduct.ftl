<#import "/macro/page.ftl" as p>
<@p.page "showProduct","showProduct">
<#if auth.isAdmin("creator") || auth.isAdmin("admin")>
<div class="row text-center">
    <form action="/shop/deleteProduct/${products.id}" method="post">
        <a class="btn btn-warning" href="/shop/editProduct/${products.id}">Редактировать</a>
        <input class="btn btn-danger"  type="submit" value="del"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>

</div>
</#if>
    <div class="row text-center">
        <h2>${products.name}</h2>
        <img src="/img/${products.imageName}" alt="" width="50%" height="50%">
        <p>Описание: ${products.description}</p>
        <p>Цена: ${products.price}</p>
    </div>



<div class="row text-center">
    <form action="/shop/addProduct/${products.id}" method="post">
        <input type="number" value="1" name="count">
        <input class="btn btn-success"  value="add" type="submit">
        <a class="btn btn-warning" href="/shop">Назад</a>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>

</@p.page>