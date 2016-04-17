var Unit = Unit || {};
(function (p) {
    p.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    p.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Birimi aktif yapmak istiyor musunuz?" : "Birimi pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#unit-grid table");
                });

            }
        });
    };

    p.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").unitId;
    };

})(Unit);
