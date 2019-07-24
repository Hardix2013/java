<#import "/macro/page.ftl" as p>
<#import "/macro/inputs.ftl" as i>
<@p.page "editUser","Edit user: ${users.username}">
<div class="col-md-3">
    <#if users.photo??>
    <img src="/img/${users.photo}" alt="img" width="100%" height="100%">
</#if>
</div>
<div class="col-md-5 text-center">
    <h3>Редактирование профиля</h3>

    <form action="/users/edit/${users.id}" method="post" enctype="multipart/form-data">
        <@i.input 'type="text" name="firstName" value="${users.firstName}"',"Имя:"></@i.input>
<@i.input 'type="text" name="lastName" value="${users.lastName}" required',"Фамилия:"></@i.input>
<@i.input 'type="email" name="email" value="${users.email}" required',"Email:"></@i.input>
<@i.input 'type="date" name="birthday" value="${users.birthday}" required pattern="[0-9]{2}.[0-9]{2}.[0-9]{4}"',"Дата рождения:"></@i.input>
<@i.input 'type="number" name="balance" value="#{users.balance!"0"}"

${auth.isAdmin("MODERATOR")?then("enabled",auth.isAdmin("ADMIN")?then("enabled",auth.isAdmin("CREATOR")?then("disable","enable")))}'





,"Баланс:"></@i.input>
<h2>Смена пароля</h2>
<@i.input 'type="password" name="password" placeholder="********" ',"Пароль:"></@i.input>
<@i.input 'type="password" name="passwordConfirm" placeholder="********" ',"Ещё раз пароль:"></@i.input>
<input type="hidden" name="_csrf" value="${_csrf.token}">

<div class="col-sm-6 text-left">
    <#list roles as role>
    <div>
        <input type="checkbox" name="authorities" value="${role.id}"
        <#list users.authorities as urole>
            ${((role.name)==(urole.name))?then("checked","")}  ${auth.isAdmin("creator")?then("",auth.isAdmin("ADMIN")?then("","disabled"))}
        </#list>
><label for="${role.name}">${role.name}
</label>

</div>
</#list>
</div>
<div class="col-sm-6 text-right">
    <div>
        <label> Не просрочен:
            <input type="checkbox" name="accountNonExpired" <#if users.isAccountNonExpired()>value="true" checked</#if> <#if !auth.isAdmin("creator") || !auth.isAdmin("admin") >onclick="return false"</#if>>
    </label>
    <label> Не заблокирован:
        <input type="checkbox" name="accountNonLocked" <#if users.isAccountNonLocked()>value="true" checked</#if><#if !auth.isAdmin("creator") || !auth.isAdmin("admin") > onclick="return false"</#if>>
</label>
<label> Активен:
    <input type="checkbox" name="enabled" <#if users.isEnabled()>value="true" checked</#if><#if !auth.isAdmin("creator") || !auth.isAdmin("admin") > onclick="return false"</#if>>
</label>
<label>Полномочия активны:
    <input type="checkbox" name="credentialsNonExpired" <#if users.isCredentialsNonExpired()>value="true" checked</#if><#if !auth.isAdmin("creator") || !auth.isAdmin("admin") > onclick="return false"</#if>>
</label>
    </div>
</div>
<label for="profileImage">Фото профиля:
    <input type="file" name="imageProfile">
</label>
        <div class="row text-center">
            <input class="btn btn-success" type="submit" value="Сохранить">
            <a href="/users/${users.id}" class="btn btn-danger">Отмена</a>
        </div>
    </form>
</div>



</@p.page>