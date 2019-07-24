<#import "/macro/page.ftl" as p>
<#import "/macro/inputs.ftl" as i>
<@p.page "registration","registration">
<div class="row text-center">
    <h2>Регистрация нового пользователя</h2>
</div>
<div class="row">
    <div class="col-md-offset-4 col-md-4">
        <form action="/users/registration" method="post">
            <@i.input 'type="text" name="firstName"  placeholder="Уася" required value="${users.getFirstName()!""}"',"Имя:"></@i.input>
            <@i.input 'type="text" name="lastName" placeholder="Пупкин" required value="${users.getLastName()!""}"',"Фамилия:"></@i.input>
            <@i.input 'type="text" name="username" placeholder="superuser" required value="${users.username!""}"',"Никнейм:"></@i.input>
            <@i.input 'type="email" name="email" placeholder="vasya@yandex.ru" required value="${users.email!""}"',"Email:"></@i.input>
            <@i.input 'type="email" name="emailConfirm" placeholder="vasya@yandex.ru" required value="${users.getEmail()!""}"',"Еще раз Email:"></@i.input>
            <@i.input 'type="date" name="birthday"  required pattern="[0-9]{2}.[0-9]{2}.[0-9]{4}" value="${users.birthday!""}"',"Дата рождения:"></@i.input>
            <@i.input 'type="password" name="password" placeholder="********" required',"Пароль:"></@i.input>
            <@i.input 'type="password" name="passwordConfirm" placeholder="********" required',"Ещё раз пароль:"></@i.input>

<input type="hidden" name="_csrf" value="${_csrf.token}">
<br>
            <div class="row text-center">
                <input class="btn btn-success" type="submit" value="Зарегистрировать">
                <a href="/users}" class="btn btn-danger">Отмена</a>
            </div>
        </form>
    </div>
</div>
</@p.page>