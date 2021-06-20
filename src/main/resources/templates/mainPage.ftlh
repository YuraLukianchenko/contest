<!DOCTYPE html>
<html>
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
    <#list users as user>
      <b>${user.username}</b><br/>
    </#list>
  </body>
</html>
