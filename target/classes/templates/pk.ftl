<#import "/macro/page.ftl" as p>
<@p.page "user", "user">
<div class="row text-center">
    <div class="col-md-3">
        <#if users.photo??>
            <img src="/img/${users.photo}" alt="img" width="100%" height="100%">
        </#if>
    </div>
    <div class="col-md-5">
        <div class="text-center">
            <h2>${users.firstName} ${users.lastName}</h2>
        </div>
        <div class="text-left">
            <h3>email: ${users.email}</h3>
            <h3>login: ${users.username}</h3>
            <h3>birthday: ${users.birthday}</h3>
            <h3>balance: ${users.balance!"0"} points</h3>
            <div class="col-sm-6 text-left">
            <#list allRoles as role>
                <div>
                    <label for="${role.name}">
                        <input type="checkbox" name="${role.name}" disabled value="${role.id}"
                        <#list users.authorities as urole>
                            <#if urole.name=role.name>
                                checked
                            </#if>
                         </#list>
                        >${role.name}
                    </label>
                </div>
            </#list>
    </div>
    <div class="col-sm-6 text-right">
        <div>
            <label> Не просрочен:
                <input type="checkbox" disabled name="accountNonExpired" <#if users.isAccountNonExpired()>value="true" checked</#if>>
            </label>
            <label> Не заблокирован:
                <input type="checkbox" disabled name="accountNonLocked" <#if users.isAccountNonLocked()>value="true" checked</#if>>
            </label>
            <label> Активен:
                <input type="checkbox" disabled name="enabled" <#if users.isEnabled()>value="true" checked</#if>>
            </label>
            <label>Полномочия активны:
            <input type="checkbox" disabled  name="credentialsNonExpired" <#if users.isCredentialsNonExpired()>value="true" checked</#if>>
            </label>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
        </div>
        <div class="col-md-4">

        </div>
    </div>
</div>

<#if auth.isAdmin("creator") || auth.isAdmin("admin") || auth.username=users.username>
<div class="row text-center">
    <form action="/users/remove/${users.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <a class="btn btn-success" href="/users/edit/${users.id}">Редактировать</a>
        <input type="submit" class="btn btn-danger" value="Удалить">
        <a href="/showPurchases/${users.id}" class="btn btn-success">Смотреть покупки</a>
    </form>
</div>
<#else>
<div class="row text-center">
    <a  class="btn btn-success" href="/index">На главную</a>
</div>
</#if>


</@p.page>