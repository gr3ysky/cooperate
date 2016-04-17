var Packaging = Packaging || {};
(function (p) {
    p.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    p.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Paketlemeyi aktif yapmak istiyor musunuz?" : "Paketlemeyi pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#packaging-grid table");
                });

            }
        });
    };

    p.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").packagingId;
    };

})(Packaging);
