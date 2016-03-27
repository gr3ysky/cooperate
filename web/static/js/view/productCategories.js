var ProductCategory = ProductCategory || {};
(function (pc) {
    pc.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    pc.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Ürün kategorisini aktif yapmak istiyor musunuz?" : "Ürün kategorisini pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#productCategory-grid table");
                });

            }
        });
    };

    pc.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").productCategoryId;
    };

})(ProductCategory);