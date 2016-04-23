var Producer = Producer || {};
(function (p) {
    p.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    p.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Üreticiyi aktif yapmak istiyor musunuz?" : "Üreticiyi pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#producer-grid table");
                });

            }
        });
    };

    p.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").producerId;
    };

    p.openImages = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").producerId;
    };
})(Producer);

