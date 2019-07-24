<#import "/macro/page.ftl" as p>
<@p.page "users","users">
<#if error??>
    <div class="alert alert-warning" role="alert">
        ${error}
    </div>
</#if>
    <div class="row text-center">
        <h2>Список пользователей</h2>
    </div>
    <div class="row text-center">
        <form action="/users" method="get">
            <div class="input-group col-md-6 col-md-offset-3">
                <span class="input-group-addon ">Найти пользователя</span>
                <input id="filter" name="filter" placeholder="Введите логин" class="form-control" value="${filter!''}" >
                <span class="input-group-btn">
                <button class="btn btn-success" type="submit">Go!</button>
                <input class="btn btn-danger" type="submit" onclick="document.getElementById('filter').value = '';" formaction="/users" formmethod="get" value="Сбросить">
            </span>
            </div>
        </form>
        <br>
        <a class="btn btn-success" href="/users/registration">Зарегистрировать пользователя</a>
    </div>
    <#if users??>
<br>
<div class="row">


            <#list users?sort_by("lastName") as user>
                        <a class="btn  ${user.isAccountNonLocked()?then((user.isAccountNonExpired()&&user.isCredentialsNonExpired()&&user.isEnabled())?then('btn-default','btn-warning'),'btn-danger')} " href="/users/${user.id}">${user.lastName} ${user.firstName}</a>
            </#list>
</div>
    <#elseif user??>
        <div class="row text-center">
            <h4>
                <a class="btn btn-default" href="/users/${user.id}">${user.lastName} ${user.firstName}</a>
            </h4>
        </div>
    <#else>
        <div class="row text-center">
            <h3>Пользователь не найден...</h3>
        </div>
    </#if>


</@p.page>

