var Product = Product || {};
(function (p) {
    p.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    p.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Ürünü aktif yapmak istiyor musunuz?" : "Ürünü pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#product-grid table");
                });

            }
        });
    };

    p.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").productId;
    };

    p.openImages = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").productId;
    };
})(Product);


