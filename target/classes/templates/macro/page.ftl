<#macro page sectionName title>
<#import "/macro/navbar.ftl" as n>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.4.1/css/bootstrap.min.css" />
    <title>${title}</title>
</head>
<body>
    <@n.navbar></@n.navbar>
    <section id="${sectionName}">
        <div class="container">
         <#if errors??>
                    <div class="row alert alert-danger text-center">
                        ${errors}
                    </div>
                </#if>
              <#nested>
        </div>
    </section>


    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
</#macro>