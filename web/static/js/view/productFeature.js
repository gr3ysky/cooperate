var ProductFeature = ProductFeature || {};
(function (p) {
    p.openCreate = function (btn) {
        window.location = $(btn).data("url");
    };
    p.openEnable = function (btn) {
        var data = $(btn).data("params");
        var message = data.isActive == "false" ? "Ürün özelliğini aktif yapmak istiyor musunuz?" : "Ürün özelliğini pasif yapmak istiyor musunuz?";
        KS.Confirm({
            title: "Onay", message: message, confirmFunc: function () {
                KS.Ajax.Post($(btn).data("url"), $(btn).data("params"), function (json) {
                    KS.Grid.Search("#productFeature-grid table");
                });

            }
        });
    };

    p.openUpdate = function (btn) {
        window.location = $(btn).data("url") + "/" + $(btn).data("params").productFeatureId;
    };

    p.openPreview = function (btn, defaultMessage) {
        if ($(btn).data("params").imageUrl != "null") {
            var html = '<img style="width:100%" src="' + $(btn).data("params").imageUrl + '" />'
            $("#imagePreviewModal .modal-body").html(html);
        }
        else {
            var html = '<p>' + defaultMessage + '</p>';
            $("#imagePreviewModal .modal-body").html(html);
        }
        $("#imagePreviewModal").modal();
    }

})(ProductFeature);
