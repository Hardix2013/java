<#import "/macro/page.ftl" as p>
<@p.page "createProduct","createProduct">
<#import "/macro/inputs.ftl" as i>

<div class="row">
    <h1 class="header text-center">
        Редактирование карточки товара
    </h1>
</div>
<div class="row">
    <div class="col-md-4">
        <img src="/img/${products.imageName}" alt="" width="100%" height="100%">
    </div>
    <div class="col-md-5">
        <form action="/shop/editProduct/${products.id}" method="POST" enctype="multipart/form-data" >
            <@i.input 'type="text" name="name" value="${products.name!""}" placeholder="крыло сметрокрыла" required','Название товара:'></@i.input>
        <@i.input 'type="number" name="price" value="#{products.price!""}" placeholder="100000" required','Цена:'></@i.input>
    <@i.input 'type="text" name="description" value="${products.description!""}" placeholder="крыло сметрокрыла вяленое. +50 к сытости" required','Описание товара:'></@i.input>
<@i.input 'type="file" name="image" placeholder="крыло сметрокрыла" ${products.imageName!"required"}','Фото товара:'></@i.input>
<input type="hidden" name="_csrf" value="${_csrf.token}">
<br>
<div class="row text-center">
    <input class="btn btn-success" type="submit" value="Сохранить">
    <a href="/shop" class="btn btn-danger"  >Отмена</a>
</div>
</form>
</div>
</div>
</@p.page>
