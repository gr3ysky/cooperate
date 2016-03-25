var User = User || {};
(function (user) {
    User.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    User.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Kullanıcıyı aktif yapmak istiyor musunuz?" : "Kullanıcıyı pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#test-grid table");
                });

            }
        });
    };

    User.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").userId;
    };

})(User);