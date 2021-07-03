<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
</head>
  <body>
    <h1>Hello to Java contest</h1>
    <div>
      <form action="/logout" method="post">
        <input type="submit" value="Sign out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
      </form>
    </div>
    <div>

    </div>
    <div>list of users:</div>
    <table class="styled-table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Level</th>
      </tr>
      </thead>
      <tbody>
      <#list users as user>
      <tr>
        <td>${user.username}</td>
        <td>${user.level}</td>
      </tr>
      </#list>
      </tbody>
    </table>

  </body>
</html>
