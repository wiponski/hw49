<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Vote for us!</title>
    <link rel="stylesheet" href="css/candidates.css">
</head>

<body>
    <div class="container flex flex-col align-center">
    <h1>Vote for us</h1>

    <main class="flex flex-wrap align-evenly">

     <#list cds as candidate>

    <div class="card">
      <form action="/vote" method="post" class="flex flex-col align-center" href="#">
        <img src="images/${candidate.photo}">
        <p>${candidate.name}</p>
        <input name="test2" type="hidden" value="${candidate.id}">

        <button type="submit" class="vote flex align-center">Vote</button>

      </form>

    </div>


    </#list>
    </main>

    </div>
</body>

</html>