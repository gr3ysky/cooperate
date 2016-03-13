<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Super User</title>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<div id="test-grid">
    <table style="width:100%" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>İşlem</th>
            <th>Fullname</th>
            <th class="hidden-xs">Rol</th>
        </tr>
        </thead>
    </table>
</div>
</body>

<content tag="local_script">
    <script>
        $(function () {
            KS.Message.Success("", "this is a test message");
            KS.Grid.Render("#test-grid table", "/su/test", {
                columns: [
                    {data: ""},
                    {data: "Fullname"},
                    {data: "RoleName"}
                ],
                customButtons: [
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Create, {"UserId": 1}, "su/user/create", null, "Yeni")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Enable, {"UserId": 1}, "su/user/enable", ["Fullname"], "Aktifleştir")},
                    {button: new KS.Grid.Button(KS.Grid.ButtonTypes.Update, {"UserId": 1}, "su/user/update", ["Fullname"], "Güncelle")},
                ]
            });

        });
    </script>
</content>

