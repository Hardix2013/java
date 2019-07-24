<#import "/macro/page.ftl" as p>
<#import "/macro/inputs.ftl" as i>
<@p.page "login","login">

<div class="row text-center">
    <h2>Вы уверены?</h2>
    <br>
</div>
<div class="row text-center">
    <div class="col-md-4 col-md-offset-4">
        <form action="/logout" method="post">
            <input type="submit" value="Выйти" class="btn btn-primary">
            <a href="/index"  class="btn btn-success">На главную</a>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</div>

</@p.page>