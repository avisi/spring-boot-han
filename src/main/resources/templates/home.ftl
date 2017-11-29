<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hallo ${name}!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Hello ${name}!</h2>
        <h2>Students:</h2>
        <table class="table">
            <thead>
                <th>Nummer</th>
                <th>Naam</th>
            </thead>
            <tbody>
            <#list students as s>
            <tr>
                <td>${s.nummer?c}</td>
                <td>${s.naam}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        <h4>Nieuwe student:</h4>
        <form action="/add" method="post">
            <div class="form-group">
                <label for="nummer">Nummer:</label>
                <@spring.formInput 'studentForm.nummer' 'class="form-control" name="nummer"' />
                <@spring.showErrors "<br/>" />
            </div>
            <div class="form-group">
                <label for="naam">Naam:</label>
                <@spring.formInput 'studentForm.naam' 'class="form-control" name="naam"' />
                <@spring.showErrors "<br/>"/>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</body>
</html>