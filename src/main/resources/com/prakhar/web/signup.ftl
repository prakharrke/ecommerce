<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>E-commerce</title>
    <link rel="stylesheet" href="/css/theme.min.css">

</head>
<body>
<main class="auth">
    <header id="auth-header" class="auth-header">
        <p> Already have an account? please <a href="/app/accountServices/login">Sign In</a>
        </p>
    </header>
    <form class="auth-form" enctype="application/x-www-form-urlencoded" method="post" action="/app/accountServices/v1/api/signup">
        <div class="form-group">
            <div class="form-label-group">
                <input name="email" type="email" id="inputEmail" class="form-control " placeholder="Email" required="" autofocus=""> <label for="inputEmail">Email</label>
            </div>
        </div>

        <div class="form-group">
            <div class="form-label-group">
                <input type="" name="firstName" id="firstName" class="form-control " placeholder="Email" required="" autofocus=""> <label for="firstName">First Name</label>
            </div>
        </div>

        <div class="form-group">
            <div class="form-label-group">
                <input type="" name="lastName" id="lastName" class="form-control " placeholder="Email" required="" autofocus=""> <label for="lastName">Last name</label>
            </div>
        </div>

        <div class="form-group">
            <div class="form-label-group">
                <input name="password" type="password" id="inputPassword" class="form-control " placeholder="Password" required=""> <label for="inputPassword">Password</label>
            </div>
        </div>

        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        </div>
    </form>

    <#if view.getMessage()??>

    <div class="alert alert-danger mt-2 w100" role="alert">
        ${view.getMessage()}
    </div>
</#if>

</main>
</body>
</html>