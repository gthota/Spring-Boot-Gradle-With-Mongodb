
<style>
#headerIconsDiv{
display: none;
}
input[type="button"] {
margin-left: 60% !important;
}
</style>
<div data-ng-init="">

<form  name="loginForm" >

<table>
<tr>
<td>Username</td>
<td><input type="text" data-ng-model="username" id="username" title="Enter Username" required/>
</td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" data-ng-model="password" id="password" required/>
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="button" value="Sign In" data-ng-click="authenticate();"/></td>
</tr>

</table>

</form>
</div>
