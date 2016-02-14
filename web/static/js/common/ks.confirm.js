/// <reference path="../jquery-1.11.2.js" />
/// <reference path="../bootstrap.js" />
/// <reference path="ks.base.js" />


(function (modal) {
    modal.Confirm = function (options) {
        var params = {
            title: options.title || "Onay",
            message: options.message || 'Emin misiniz?',
            confirmText: options.confirmText || 'Evet',
            cancelText: options.cancelText || 'Hayır',
            confirmFunc: options.confirmFunc || function () {
                console.log("confirmFunc not implemented");
            },
            cancelFunc: options.cancelFunc || function () {
                console.log("cancelFunc not implemented");
            }
        };

        if (!$("#confirmModal").length) {
            $("body").append('<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true"></div>');
        }


        var template = GetTemplate(params);
        var $modalElem = $("#confirmModal");
        $modalElem.html(template).modal({
            show: false
        });
        $modalElem.find("#confirmButton").on("click", function () {
            if (params.confirmFunc) {
                params.confirmFunc();
            }
            $modalElem.modal("hide");
        });

        $modalElem.find("#cancelButton").on("click", function () {
            if (params.cancelFunc) {
                params.cancelFunc();
            }
            $modalElem.modal("hide");
        });

        $modalElem.modal("show");

    }



    //Private fonksiyonlar
    function GetTemplate(params) {
        return '<div class="modal-dialog modal-sm">'
                + '<div class="modal-content">'
                    + '<div class="modal-header">'
                        + '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
                        + '<h4 class="modal-title" id="myModalLabel">' + params.title + '</h4>'
                    + '</div>'
              + '<div class="modal-body">'
              + params.message
              + '</div>'
              + '<div class="modal-footer">'
              + '<button type="button" id="cancelButton" class="btn btn-default" data-dismiss="modal">' + params.cancelText + '</button>'
              + '<button type="button" id="confirmButton" class="btn btn-primary">' + params.confirmText + '</button>'
              + '</div>'
          + '</div>'
         + '</div>';
    }
}(KS));

