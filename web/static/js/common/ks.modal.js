(function (mdl) {
    mdl.Modal = {};

    mdl.Modal.Instance = {};
    mdl.Modal.Open = function (url, data, options) {
        var $modalWrapper = $("#modal-wrapper");
        $modalWrapper.removeClass("hide");
        var params = {
            width: options.width || 800,
            height: options.height || 450,
            beforeOpen: options.beforeOpen || function () {
            },
            onComplete: options.onComplete || function () {
            },
            onClose: function (modalId) {
                if (options.onClose)
                    options.onClose();
                if (modalId) {
                    var $modal = $("#" + modalId);
                    if ($modal)
                        mdl.Modal.Remove($modal);
                } else
                    mdl.Modal.RemoveLast();
                $("body").removeClass("modal-open");
            }
        };
        params.beforeOpen();

        KS.Ajax.Get(url, data, onSucceed, params.onComplete);

        function onSucceed(html) {
            if ($modalWrapper.html().length == 0)
                $modalWrapper.html(html);
            else {
                $($modalWrapper).append(html);
            }
            if (params.width && params.width != -1) {
                $("[id^=KSModal] .modal-dialog:first", $modalWrapper).last().width(params.width);
                $("[id^=KSModal] .modal-dialog:first .modal-content", $modalWrapper).last().css({"max-height": params.height});
                $("[id^=KSModal] .modal-dialog:first .modal-content:last .modal-body .modal-content-wrap", $modalWrapper).css({
                    "max-height": params.height - 151,
                    "overflow-y": "auto",
                    "overflow-x": "hidden"
                });
                /*65px header + 56px footer for the modal +30padding*/
            }


            $(mdl.Modal.Instance).on('hidden.bs.modal', function (e) {
                //NOTE: farklı ise arada popup açılmıştır . Herzman en üstteki ilk önce kapanmalı
                if (e.currentTarget == e.target) {
                    params.onClose();
                    mdl.Modal.RemoveLast();
                }
            });
        }


    };

    mdl.Modal.Close = function (modalId) {
        mdl.Modal.Instance.modal('hide');
    };
    ;

    mdl.Modal.Add = function (obj) {
        KS.Modals.push(obj);
    };
    ;
    mdl.Modal.RemoveLast = function () {
        var lastIndex = KS.Modals.length - 1;
        if (lastIndex == -1) return;
        $(mdl.Modal.Instance, "#modal-wrapper").remove();
        KS.Modals.pop();
        mdl.Modal.Instance = KS.Modals[lastIndex - 1];

    }

}(KS));
