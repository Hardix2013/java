<#macro navbar>
<nav class="navbar navbar-inverse">
    <div class="col-sm-6">
        <a class="navbar-brand active" href="/index">Главная</a>
        <ul class="nav navbar-nav">
            <#if auth?? && auth.isAdmin("ADMIN")|| auth?? && auth.isAdmin("MODERATOR")>
                <li><a href="/users">Пользователи</a></li>
            </#if>
            <li><a href="/shop">Магазин</a></li>
        </ul></div>
    <div class="col-sm-4 col-sm-offset-2">
        <#if auth??>
        <ul class="nav navbar-nav">
            <li><a href="/bucket">Баланс: <span class="badge">${auth.balance!"0"}</span></a></li>
            <li><a href="/bucket">Корзина <span class="badge">${(auth.bucket?size)!"0"}</span></a></li>
            <li><a href="/users/${auth.id}">${auth.firstName} ${auth.lastName}</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
        <#else>
        <ul class="nav navbar-nav">
            <li><a href="/login">Войти</a></li>
            <li><a href="/users/registration">Зарегистрироваться</a></li>
        </ul>
        </#if>
    </div>
</nav>
</#macro>