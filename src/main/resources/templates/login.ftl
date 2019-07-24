<#import "/macro/page.ftl" as p>
<#import "/macro/inputs.ftl" as i>
<@p.page "login","login">

<div class="row text-center">
    <h2>login page</h2>
</div>

<div class="row text-center">
    <div class="col-md-4 col-md-offset-4">
        <form action="/login" method="post">
            <@i.input 'type="text" id="username" name="username"','Логин:'></@i.input>
        <br>
            <@i.input 'type="password" id="password" name="password"','Пароль:'></@i.input>
            <br>
            <input type="submit" value="Войти" class="btn btn-primary">
            <a href="/users/registration"  class="btn btn-success">Зарегистрироваться</a>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</div>

</@p.page>